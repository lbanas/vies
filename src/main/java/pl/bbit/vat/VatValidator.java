package pl.bbit.vat;



import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.StringHolder;
import javax.xml.ws.WebServiceRef;

import org.apache.axis.holders.DateHolder;

import checkVat.services.vies.taxud.eu.europa.ec.CheckVatPortType;
import checkVat.services.vies.taxud.eu.europa.ec.CheckVatServiceLocator;

public class VatValidator {

//	@WebServiceRef(wsdlLocation = "http://ec.europa.eu/taxation_customs/vies/checkVatService.wsdl")

	private static VatRegisterInfo requestVatInfo(String country, String vatNumber)  {

		try{
			CheckVatServiceLocator chl = new CheckVatServiceLocator();
			CheckVatPortType getcheckVatPort = chl.getcheckVatPort();
	
			StringHolder country_ = new StringHolder(country);
			StringHolder vatNumber_ = new StringHolder(vatNumber);
			DateHolder date_ = new DateHolder();
			BooleanHolder valid_ = new BooleanHolder();
			StringHolder name_ = new StringHolder();
			StringHolder address_ = new StringHolder();
	
			getcheckVatPort.checkVat(country_, vatNumber_, date_, valid_, name_, address_);
			return new VatRegisterInfo(new String(name_.toString()), address_.toString(), country_.toString(), vatNumber_.toString(), valid_.value);
		}catch (Exception e){
			return new VatRegisterInfo(new String("ERROR CONNECTION"), new String(), new String(), new String(), Boolean.FALSE);
		}
		
	}
	
	public static Boolean validVat(String country, String vatNumber) {
		VatRegisterInfo requestVatInfo = requestVatInfo(country, vatNumber);
		return requestVatInfo.getValidate();
	}
	
}
