package webservices;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;
import org.openrdf.rio.RDFParseException;

import com.google.gson.Gson;

import metier.Erreur;
import metier.Infos;
import metier.Message;
import metier.Result;
/**
 * Servlet implementation class search
 */
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig cfg;
    private Gson gson = new Gson();

	  public void init(ServletConfig config) throws ServletException {
	     cfg = config;
	  }

	  public ServletConfig getServletConfig() {
	    return cfg;
	  }

	  

	  public void destroy() {
	  }

	  public void service (ServletRequest req,  ServletResponse res ) 
	  throws ServletException, IOException  {
		  PrintWriter out = res.getWriter();
		  res.setContentType("application/json");
		  res.setCharacterEncoding("UTF-8");
		  Set<String> orphaCodes = new HashSet<String>();
		  Set<String> Codes = null;
		  String[] orphaCodesA = req.getParameterValues("code");
		  if(orphaCodesA==null)
		  {
			  Erreur R= new Erreur();
			  Message M=new Message ("Parameter 'code'  is mandatory.");
              M.setCode("required Parameter.");
			  R.Add(M);
              String infos;
	          infos= this.gson.toJson(R);
       	      out.print( infos);
	          out.flush();
              return;
		  }
		  else
		  {
			  for(int i=0;i<orphaCodesA.length;i++)
			  {	  
			     String ValueTemp=orphaCodesA[i].toLowerCase();
				  ValueTemp=ValueTemp.replace("http://www.orpha.net/ordo/orphanet_", "");
				  orphaCodes.add(ValueTemp);
			  
			  }
		  }
		  
		  String[] typesA = req.getParameterValues("resourceType");
		  Set<String> types = new HashSet<String>();

		  if(typesA!=null)
		  {
			  for(int i=0;i<typesA.length;i++)
				  if(typesA[i].equals("PatientRegistryDataset")||typesA[i].equals("BiobankDataset"))
					  types.add(typesA[i]);
				  else
				  {
					  Erreur R= new Erreur();
		              R.Add(new Message ("The value of type of resource Parameter must be: PatientRegistryDataset or BiobankDataset ."));
		              String infos;
			          infos= this.gson.toJson(R);
		       	      out.print( infos);
			          out.flush();
		              return;  
				  }
					  
		  
		  }
		  
		  String[] countriesA = req.getParameterValues("country");
		  Set<String> countries = new HashSet<String>();

		  if(countriesA!=null)
		  {
			  for(int i=0;i<countriesA.length;i++)
				  countries.add(countriesA[i].toUpperCase());
		  }		  
		  
		  
		  String name=req.getParameter("name");
		  
		  String Skip = req.getParameter("skip");
		  int skip;
		  
		  if(Skip==null)skip=0;
		  else
		  if(tryParse(Skip)instanceof String)
		      {    
				  Erreur R= new Erreur();
	               R.Add(new Message ((String)tryParse(Skip)));
	               String infos;
		           infos= this.gson.toJson(R);
	        	   out.print( infos);
		           out.flush();
	               return; 
			  }else 	  
		  skip=(Integer) tryParse(Skip);
          if(skip<0)
          {
        	  Erreur R= new Erreur();
              R.Add(new Message ("Parameter skip cannot be negative."));
              String infos;
	           infos= this.gson.toJson(R);
       	   out.print( infos);
	           out.flush();
              return;
          }	  
		  
          String Limit = req.getParameter("limit");
		  int limit;
		  if(Limit==null)limit=10;
		  else
		  if(tryParse(Limit)instanceof String)
		      {    
				  Erreur R= new Erreur();
	               R.Add(new Message ((String)tryParse(Limit)));
	               String infos;
		           infos= this.gson.toJson(R);
	        	   out.print( infos);
		           out.flush();
	               return; 
			  }else 	  
		  limit=(Integer) tryParse(Limit);
          if(limit<=0)
          {
        	  Erreur R= new Erreur();
              R.Add(new Message ("Parameter limit cannot be negative or zero."));
              String infos;
	           infos= this.gson.toJson(R);
       	   out.print( infos);
	           out.flush();
              return;
          }
		
          
          try {
			
        	/*
        	 for(String s:orphaCodes)
        	if(!s.contains(""))
        		s.replace("", "");
        	*/
        	Codes =MapsToOrphanet(orphaCodes);
        	//System.out.println("OrphaCodes: "+orphaCodes);
            //System.out.println("Codes: "+Codes);
            Result R;
            if(!Codes.isEmpty())
            {
            R = new Result(Codes);
        	if(!countries.isEmpty())
			R.ApplyFilterCountries(countries);
			if(!types.isEmpty())
			R.ApplyFilterTypes(types);
			if(name!=null)
			R.ApplyFilterSearchByName(name);
            }
			else R=new Result();
            
            R.ApplyFilterPagination(skip,limit);
            String infos;
	      	infos= this.gson.toJson(R);
	      	out.print( infos);
	        out.flush();
          
          
          } catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RDFParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
          
          
          
          System.out.println("OrphaCodes: "+orphaCodes);
          System.out.println("Codes: "+Codes);

          
         /* System.out.println(orphaCodes);
		  System.out.println(types);
		  System.out.println(countries);
		  System.out.println("Name= "+ name+" Skip= "+ skip+" Limit= "+ limit);
         */


		  
		              
			
		  
	  } 
       
   
	
	  public Set<String> MapsToOrphanet(Set<String> orphaCodes) throws RepositoryException, RDFParseException, IOException {
		  Set<String> results = new HashSet<String>();
          for(String s:orphaCodes)
           if(!s.contains("http"))results.add(s);
           else
           {
        	String s1=s.substring(s.lastIndexOf("/")+1);
        	String s2=s.substring(0,s.lastIndexOf("/")); 
        	s=s2+"/"+s1.toLowerCase();
        	   results.addAll(Mapps(s2+"/"+s1.toLowerCase()));	   
           }
		  
		  
		  
		return results;
	}
	  public  Set<String> Mapps(String Code) throws RepositoryException, RDFParseException, IOException
	  {
		  Set<String> matchs =new HashSet<String>();
		 
	  	    Repository BlazeGraphServer = new HTTPRepository("http://155.133.131.171:8080/blazegraph/namespace/mapping/sparql");  
	  	    BlazeGraphServer.initialize();
	  	   //On ouvre une connexion au repository pour envoyer toute les requêtes
	  		 RepositoryConnection connection = BlazeGraphServer.getConnection();
	  		 //Création de la Query SELECT
	  		 //On initialise la query.	 
	  		 String requeteSPARQL = "select  * where {?s ?p <"+Code+"> }";

	  				 
	  		 System.out.println(requeteSPARQL);	
	  		 // Création de la Requête 	         
	  		 org.openrdf.query.TupleQuery selectQuery = null;
	  		try {
	  			selectQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL,requeteSPARQL);
	  		} catch (MalformedQueryException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}	
	  		 // on l'exécute			
	  		 TupleQueryResult selectQueryResult = null;
	  		try {
	  			selectQueryResult = selectQuery.evaluate();
	  		} catch (QueryEvaluationException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}	 
	  		 // Récupération Affichage des résultats
	  		 
	  		 try {
	  			while(selectQueryResult.hasNext()) 
	  				{  
	  				   // chaque ligne du résultat est un BindingSet  
	  				
	  				BindingSet aBinding = selectQueryResult.next();				    
	  				String p=aBinding.getBinding("p").getValue().stringValue();
	  				String o=aBinding.getBinding("s").getValue().stringValue();
	  				if (p.contains("exactMatch"))matchs.add(o.replace("http://www.orpha.net/ORDO/Orphanet_", ""));
	  				if (p.contains("btnt"))matchs.add(o.replace("http://www.orpha.net/ORDO/Orphanet_", ""));      				
	  				if (p.contains("ntbt"))matchs.add(o.replace("http://www.orpha.net/ORDO/Orphanet_", ""));
	  				
	  				

	  				}
	  		} catch (QueryEvaluationException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}

	  		 return matchs;
	  }
	public static Object tryParse(String text) {
		  try {
		    return Integer.parseInt(text);
		  } catch (NumberFormatException e) {
		    return e.toString();
		  }
		}	
}
