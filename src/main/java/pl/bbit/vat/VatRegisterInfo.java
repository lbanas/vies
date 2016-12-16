package pl.bbit.vat;

public class VatRegisterInfo {

	private String name;
	private String address;
	private String countryCode;
	private String vatNumber;
	private Boolean valid = Boolean.FALSE;
	
	public VatRegisterInfo(String name, String address, String countryCode, String vatNumber, Boolean valid) {
		this.name = name;
		this.address = address;
		this.countryCode = countryCode;
		this.vatNumber = vatNumber;
		this.valid = valid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getVatNumber() {
		return vatNumber;
	}
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}
	public Boolean getValidate() {
		return valid;
	}
	public void setValidate(Boolean valid) {
		this.valid = valid;
	}
	
	public String toString() {
		return "[-] "+name+" "+address+" :: "+countryCode+" "+vatNumber+" :: "+valid;
	}
}
