package metier;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import org.openrdf.repository.RepositoryException;
public class Result {
	public Vector<Resource> resourceResponses;
	public String apiVersion= "v0.3";
	public Page page;
	public Result(Set<String> orphaCodes) throws RepositoryException {
		Resource V= new Resource();
		this.resourceResponses=V.ListToken(orphaCodes);
	}
	
	public Result() throws RepositoryException {
		this.resourceResponses=new Vector<Resource>();
	}
		public void ApplyFilterCountries(Set<String> countries) {
	Vector<Resource> Concerned=new Vector<Resource>();
	for(Resource r:this.resourceResponses)
	 if(countries.contains(r.getLocation().getCountry()))
		 Concerned.add(Concerned.size(), r);	
	
	this.resourceResponses=Concerned;	
	}
	public void ApplyFilterTypes(Set<String> types) {
		
		Vector<Resource> Concerned=new Vector<Resource>();
		for(Resource r:this.resourceResponses)
		 if(types.contains(r.getType()))
			 Concerned.add(Concerned.size(), r);	
		
		this.resourceResponses=Concerned;	
		
	}
	public void ApplyFilterPagination(int skip, int limit) {
	
		this.page= new Page();

		this.page.setSize(limit);
		Vector<Resource> All=this.resourceResponses;
		this.page.setTotalElements(All.size());
		if(this.page.getTotalElements()==0)this.page.setTotalPages(0);
		else if((this.page.getTotalElements()%limit!=0))
		this.page.setTotalPages((this.page.getTotalElements()/limit)+1);
		else this.page.setTotalPages((this.page.getTotalElements()/limit));
		this.page.setNumber(skip);
		int start,end=-9;
		if(skip==0)
		{
		  start=0;
		  end=limit;
		}
		else 
		{
			start=limit*skip;
			end=start+limit;
		}	
		Vector<Resource> Temp=new Vector<Resource>();

			while(( start< end)&&( start< All.size()))
			{
			
			Temp.add(Temp.size(),(Resource) All.get(start));	
			start++;
			}	
				this.resourceResponses=Temp;

		
	}
	public void ApplyFilterSearchByName(String name) {
		String[] words = name.split("\\W+");
		Set<String> wSet = new HashSet<String>(); 
		for(int i=0;i<words.length;i++)
		wSet.add(words[i].toLowerCase());
		
		Vector<Resource> Concerned=new Vector<Resource>();
		for(Resource r:this.resourceResponses)
		{ 
			String resname=r.getName().toLowerCase();
			for(String q:wSet)
			if(resname.contains(q))
			{
				Concerned.add(Concerned.size(), r);break;	
			}	
		}
		
		
		this.resourceResponses=Concerned;	
		
	}
	
}
