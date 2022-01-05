/*
 * Class : Infos
 *
 * Description   : this class generate a set of information about the Orphanet Endpoint.
 * 
 * Version       : 1.0
 *
 * Date          : 23/11/2021
 * 
 * Copyright     : Boulares Ouchenne
 */
package metier;

import java.util.HashSet;
import java.util.Set;

public class Infos {
String id, name, description,homepage;
Set<String> type;
Set<String> apiVersion;
Set<String> sampleRequests;
Organisation organisation;
public Infos() {
	this.id="Orphanet-Catalog";
	this.type = new HashSet<String>();
	this.type.add("Catalog Of Registries");
	this.type.add("CatalogOfBiobanks");
	this.organisation=new Organisation();
	this.name="Orphanet Catalog";
	this.description="The Orphanet catalog of rare disease Biobanks and Registries.";
	this.homepage="http://www.orpha.net/";
	this.apiVersion = new HashSet<String>();
	this.apiVersion.add("v0.3");
	this.sampleRequests = new HashSet<String>();
	this.sampleRequests.add("/search?code=http://www.orpha.net/ORDO/Orphanet_778 will return results based on the specified OrphaCode");
	this.sampleRequests.add("/search?code=http://identifiers.org/icd/Q87.4 will return results based on the specified ICD10 Code");
	this.sampleRequests.add("/search?code=https://www.omim.org/entry/100800 will return results based on the specified OMIM Code");
	//voir Orphanet_15
	//this.sampleRequests.add("/search?code=http://identifiers.org/icd/Q87.4 will return results based on the specified ICD10 Code}.");
	//this.sampleRequests.add("/search?code=http://identifiers.org/icd/Q87.4 will return results based on the specified ICD10 Code}.");

}


	
}
