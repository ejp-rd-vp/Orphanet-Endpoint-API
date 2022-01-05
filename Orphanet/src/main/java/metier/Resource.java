package metier;

import java.util.Set;
import java.util.Vector;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

public class Resource {
	String id;
	String type;
	String description;
	String name;
	String homepage;
	Location location;
	 

	public Resource()
	{
		
	}
	public Vector<Resource> ListToken(Set<String> orphaCodes) throws RepositoryException {
		String SparqlEndPointUrl="http://155.133.131.171:8080/blazegraph/sparql";	
		Vector<Resource> resourceResponses=new Vector<Resource>();
		 Repository BlazeGraphServer = new HTTPRepository(SparqlEndPointUrl);  
		 BlazeGraphServer.initialize();		
		 //On ouvre une connexion au repository pour envoyer toute les requêtes
		 RepositoryConnection connection = BlazeGraphServer.getConnection();
		 //Création de la Query SELECT
		 //On initialise la query.	 
		 String requeteSPARQL="";
		 if(! orphaCodes.contains("all"))
		 {
		 requeteSPARQL = "prefix ejp:<http://purl.org/ejp-rd/vocabulary/>\n"+
			 "prefix dc:<http://purl.org/dc/elements/1.1/>\n"+
			 "prefix dcterms:<http://purl.org/dc/terms/>\n"+
			 "prefix dcat:<http://www.w3.org/ns/dcat#>\n"+
			 "prefix ordo:<http://www.orpha.net/ORDO/>\n"+
				  "select  distinct ?id ?type ?title ?country ?url where {\n";
		
		 String expression="";
		 
	 for(String t:orphaCodes)expression=expression+"{?reg dcat:theme ordo:Orphanet_"+t+"}UNION";
		expression=expression+"N"; 
		expression=expression.replace("UNIONN", "");
		requeteSPARQL=requeteSPARQL+expression;
		 requeteSPARQL=requeteSPARQL+"\n.?reg a ?type. ?reg dc:identifier ?id.?reg dcterms:title ?title.?reg foaf:page ?url. ?reg dcterms:publisher ?pubs. ?pubs dcterms:spatial ?sp. ?sp ejp:country ?country} ORDER BY DESC(?id)";
		 
		 }
		 else
		 {
			
			 requeteSPARQL = "prefix ejp:<http://purl.org/ejp-rd/vocabulary/>\n"+
					 "prefix dc:<http://purl.org/dc/elements/1.1/>\n"+
					 "prefix dcterms:<http://purl.org/dc/terms/>\n"+
					 "prefix dcat:<http://www.w3.org/ns/dcat#>\n"+
					 "prefix ordo:<http://www.orpha.net/ORDO/>\n"+
						  "select  distinct ?id ?type ?title ?country ?url where {?reg a ?type. ?reg dc:identifier ?id.?reg dcterms:title ?title.?reg foaf:page ?url. ?reg dcterms:publisher ?pubs. ?pubs dcterms:spatial ?sp. ?sp ejp:country ?country} ORDER BY DESC(?id)";
			 
		 }	 
		 
		 //System.out.println(requeteSPARQL);	
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
				
				if(aBinding.getBinding("url").getValue().stringValue().contains("OC_Exp.php?lng=en&Expert"))
			{		
				Resource T=new Resource();
				T.setId(aBinding.getBinding("id").getValue().stringValue());
				//T.setHomepage(UrlOf(aBinding.getBinding("id").getValue().stringValue()));
				T.setHomepage(aBinding.getBinding("url").getValue().stringValue());
				String type=aBinding.getBinding("type").getValue().stringValue();
				type=type.substring(type.lastIndexOf("/")+1);
				T.setDescription(type.replace("Dataset","")+" of "+aBinding.getBinding("title").getValue().stringValue());
				T.setName(aBinding.getBinding("title").getValue().stringValue());
				T.setType(type);
				T.setLocation(new Location(aBinding.getBinding("country").getValue().stringValue()));
				resourceResponses.add(resourceResponses.size(),T);
			}// else {System.out.println(aBinding.getBinding("url").getValue().stringValue());}
				
				}
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		 return resourceResponses;	
	
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
