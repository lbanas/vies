package pl.bbit.vat.country;

public class Country {

	private String countryCode;
	private String numberPattern;
	private Boolean euCountry;
	
	public Country() {
	}
	
	public Country(String countryCode, String numberPattern, Boolean euCountry) {
		super();
		this.countryCode = countryCode;
		this.numberPattern = numberPattern;
		this.euCountry = euCountry;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getNumberPattern() {
		return numberPattern;
	}
	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
	}
	public Boolean getEuCountry() {
		if(euCountry==null){
			return false;
		}
		return euCountry;
	}
	public void setEuCountry(Boolean euCountry) {
		this.euCountry = euCountry;
	}
	
}
