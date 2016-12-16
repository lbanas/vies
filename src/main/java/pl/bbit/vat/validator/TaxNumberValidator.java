package pl.bbit.vat.validator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.bbit.vat.TaxNumber;
import pl.bbit.vat.country.Country;

public class TaxNumberValidator {

	private static final String COUNTRY_FILE = "country.json";

	public static Boolean taxNumberValidator(String taxNo) {
		String pattern = getTaxNumberPattern(getCountryCode(taxNumberTrim(taxNo)));
		return checkPattern(taxNo, pattern);
	}
	
	public static Boolean taxNumberValidator(String taxNo, String countryCode_){
		if(checkPattern(countryCode_, "^[A-Z]{2}")){
			taxNo = countryCode_+taxNo;
		}
		return taxNumberValidator(taxNo);
	}
	
	
	public static String taxNumberTrim(String taxNo){
		return taxNo.replaceAll("[-\\. ]*", "");
	}
	
	private static boolean checkPattern(String taxNo, String pattern_){
		if(taxNo!=null && pattern_!=null){
			Pattern pattern = Pattern.compile(pattern_);
			Matcher matcher = pattern.matcher(taxNo);
			if (matcher.find()) {
				return true;
			}
		}
		return false;
	}
	
	public static String getCountryCode(String taxNo){
		
		if(checkPattern(taxNo, "^[A-Z]{2}")){
			return taxNo.substring(0, 2);
		}
		
		return new String();
	}
	
	public static boolean validCountryCode(String country){
		String[] countryCodes = Locale.getISOCountries();
		for (String string : countryCodes) {
			if(string.equals(country)){
				return true;
			}
		}
		return false;
	}
	
	private static Map<String, Country> getTaxNumberAllPatterns() {
		
		Map<String, Country> countyMap  =  new HashMap<>();
		Gson gson = new Gson();
	 
		Type stringCountryMap = new TypeToken<HashMap<String, Country>>(){}.getType();
		try {
			BufferedReader br = new BufferedReader(new FileReader(ClassLoader.getSystemClassLoader().getResource(COUNTRY_FILE).getFile()));
			countyMap =  gson.fromJson(br, stringCountryMap);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return countyMap;
	}
	
	/**
	 * Return tax number pattern from json file 
	 * @param countryCode
	 * @return String with add pattern ^(XX)?+<patern from JSON>+$
	 */
	private static String getTaxNumberPattern(String countryCode) {
		Map<String, Country> taxNumberAllPatterns = getTaxNumberAllPatterns();
		if(taxNumberAllPatterns.containsKey(countryCode)){
			return "^("+countryCode+")?"+taxNumberAllPatterns.get(countryCode).getNumberPattern()+"$";
		}
		return ".*";
	}
	
	public static TaxNumber getTaxNumber(String taxNo){
		TaxNumber tx = new TaxNumber();
		if(taxNumberValidator(taxNo)){
			tx.setCountryCode(getCountryCode(taxNo));
			tx.setTaxNumber(taxNo.substring(2));
		}
		return tx;
	}
	
	public static boolean isEUPayment(String countryCode){
		Map<String, Country> taxNumberAllPatterns = getTaxNumberAllPatterns();
		if(taxNumberAllPatterns.containsKey(countryCode)){
			return taxNumberAllPatterns.get(countryCode).getEuCountry();
		} 
		return false;
	}
	
}
