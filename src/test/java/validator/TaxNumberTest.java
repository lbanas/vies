package validator;

import org.junit.Test;
import pl.bbit.vat.VatValidator;
import pl.bbit.vat.validator.TaxNumberValidator;

import static org.junit.Assert.*;

public class TaxNumberTest {

	@Test
	public void test() {
		assertEquals("888888888888", TaxNumberValidator.taxNumberTrim("888-888-888-888"));
		assertEquals("888888888888",TaxNumberValidator.taxNumberTrim("888.888.888-888"));
		assertEquals("888888888888",TaxNumberValidator.taxNumberTrim("888 888 888 888"));
		
		assertEquals("PL", TaxNumberValidator.getCountryCode("PL888 888 888 888"));
		assertEquals("FR", TaxNumberValidator.getCountryCode("FR 888-111"));
		
		assertEquals(Boolean.TRUE, TaxNumberValidator.validCountryCode("PL"));
		assertEquals(Boolean.FALSE, TaxNumberValidator.validCountryCode("PLX"));
		
		assertEquals(Boolean.TRUE, TaxNumberValidator.taxNumberValidator("PL8981874061"));
		assertEquals(Boolean.TRUE, TaxNumberValidator.taxNumberValidator("8981874061"));   //no countryCode then .*
		assertEquals(Boolean.FALSE, TaxNumberValidator.taxNumberValidator("PL898187"));
		
		assertEquals(Boolean.TRUE, TaxNumberValidator.taxNumberValidator("GB123456789"));
		assertEquals(Boolean.TRUE, TaxNumberValidator.taxNumberValidator("GB123456789012"));
		assertEquals(Boolean.TRUE, TaxNumberValidator.taxNumberValidator("GBGD123"));
		assertEquals(Boolean.TRUE, TaxNumberValidator.taxNumberValidator("GBHA123"));
		
		assertEquals(Boolean.TRUE, TaxNumberValidator.taxNumberValidator("8981874061", "PL"));
		
		assertEquals(Boolean.FALSE, VatValidator.validVat("PL", "PL8981874061"));
		assertEquals(Boolean.TRUE, VatValidator.validVat("PL", "6651150689"));
		assertEquals(Boolean.FALSE, TaxNumberValidator.isEUPayment("XX"));
		assertEquals(Boolean.TRUE, TaxNumberValidator.isEUPayment("PL"));
	}

}
