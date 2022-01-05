package metier;

public class Location {
	String id,country;

	public Location(String country) {
		this.country = country;
		/*
		//le ID
		if (country.equalsIgnoreCase("SWITZERLAND"))this.id="CH";//1
		if (country.equalsIgnoreCase("BELGIUM"))this.id="BE";//2
		if (country.equalsIgnoreCase("ITALY"))this.id="IT";//3
		if (country.equalsIgnoreCase("SPAIN"))this.id="ES";//4
		if (country.equalsIgnoreCase("NETHERLANDS"))this.id="NL";//5
		if (country.equalsIgnoreCase("GERMANY"))this.id="DE";//6
		if (country.equalsIgnoreCase("AUSTRIA"))this.id="AT";//7
		if (country.equalsIgnoreCase("IRELAND"))this.id="IE";//8
		if (country.equalsIgnoreCase("FRANCE"))this.id="FR";//9
		if (country.equalsIgnoreCase("BULGARIA"))this.id="BG";//10

		if (country.equalsIgnoreCase("UNITED KINGDOM"))this.id="GB";//1
		if (country.equalsIgnoreCase("UNITED STATES"))this.id="US";//2
		if (country.equalsIgnoreCase("TURKEY"))this.id="TR";//3
		if (country.equalsIgnoreCase("SWEDEN"))this.id="SE";//4
		if (country.equalsIgnoreCase("POLAND"))this.id="PL";//5
		if (country.equalsIgnoreCase("FINLAND"))this.id="FI";//6
		if (country.equalsIgnoreCase("DENMARK"))this.id="DK";//7
		if (country.equalsIgnoreCase("CROATIA"))this.id="HR";//8
		if (country.equalsIgnoreCase("CYPRUS"))this.id="CY";//9
		if (country.equalsIgnoreCase("ESTONIA"))this.id="EE";//10

		if (country.equalsIgnoreCase("GREECE"))this.id="GR";//1
		if (country.equalsIgnoreCase("HUNGARY"))this.id="HU";//2
		if (country.equalsIgnoreCase("ISRAEL"))this.id="IL";//3
		if (country.equalsIgnoreCase("ICELAND"))this.id="IS";//4
		if (country.equalsIgnoreCase("LATVIA"))this.id="LV";//5
		if (country.equalsIgnoreCase("LITHUANIA"))this.id="LT";//6
		if (country.equalsIgnoreCase("LUXEMBOURG"))this.id="LU";//7
		if (country.equalsIgnoreCase("MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF"))this.id="MK";//8
		if (country.equalsIgnoreCase("NORWAY"))this.id="NO";//9
		if (country.equalsIgnoreCase("CANADA"))this.id="CA";//10


		if (country.equalsIgnoreCase("AUSTRALIA"))this.id="AU";//1
		if (country.equalsIgnoreCase("MALTA"))this.id="MT";//2
		if (country.equalsIgnoreCase("UKRAINE"))this.id="UA";//3
		if (country.equalsIgnoreCase("PORTUGAL"))this.id="PT";//4
		if (country.equalsIgnoreCase("ROMANIA"))this.id="RO";//5
		if (country.equalsIgnoreCase("CZECH REPUBLIC"))this.id="CZ";//6
		if (country.equalsIgnoreCase("SERBIA"))this.id="RS";//7
		if (country.equalsIgnoreCase("SLOVENIA"))this.id="SI";//8
		if (country.equalsIgnoreCase("SLOVAKIA"))this.id="SK";//9

		*/

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

}
