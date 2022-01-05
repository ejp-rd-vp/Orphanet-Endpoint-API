package metier;

public class Organisation {
	String id, name, description,homepage;

	public Organisation() {
	this.id="Orphanet";
	this.name="Orphanet Organisation";
	this.description="The Orphanet Organisation";
	this.homepage="http://www.orpha.net/";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	
}
