package pl.bbit.vat;

public class TaxNumber {

	private String countryCode;
	private String taxNumber;
	
	public TaxNumber(){
	}
	
	public TaxNumber(String countryCode, String taxNumber) {
		super();
		this.countryCode = countryCode;
		this.taxNumber = taxNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	
	public String toString(){
		return countryCode+" "+taxNumber;
	}
}
