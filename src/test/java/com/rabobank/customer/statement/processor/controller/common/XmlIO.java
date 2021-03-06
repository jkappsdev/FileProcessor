package com.rabobank.customer.statement.processor.controller.common;

import static java.nio.charset.StandardCharsets.*;

public class XmlIO {

	public static String getXMLInput() {
		
		String xmlAsString = "<records>\n" + "<record reference=\"130498\">\n"
				+ "<accountNumber>NL69ABNA0433647324</accountNumber>\n"
				+ "<description>Tickets for Peter Theu�</description>\n"
				+ "<startBalance>26.9</startBalance>\n"
				+ "<mutation>-18.78</mutation>\n"
				+ "<endBalance>8.12</endBalance>\n"
				+ "</record>\n"
				+ "<record reference=\"167875\">\n"
				+ "<accountNumber>NL93ABNA0585619023</accountNumber>\n"
				+ "<description>Tickets from Erik de Vries</description>\n"
				+ "<startBalance>5429</startBalance>\n"
				+ "<mutation>-939</mutation>\n"
				+ "<endBalance>6368</endBalance>\n"
				+ "</record>\n"
				+ "<record reference= \"147674\">\n"
				+ "<accountNumber>NL93ABNA0585619023</accountNumber>\n"
				+ "<description>Subscription from Peter Dekker</description>\n"
				+ "<startBalance>74.69</startBalance>\n"
				+ "<mutation>-44.91</mutation>\n"
				+ "<endBalance>29.78</endBalance>\n"
				+ "</record>\n"
				+ "<record reference= \"135607\">\n"
				+ "<accountNumber>NL27SNSB0917829871</accountNumber>\n"
				+ "<description>Subscription from Peter Theu�</description>\n"
				+ "<startBalance>70.42</startBalance>\n"
				+ "<mutation>+34.42</mutation>\n"
				+ "<endBalance>104.84</endBalance>\n"
				+ "</record>\n"
				+ "<record reference= \"169639\">\n"
				+ "<accountNumber>NL43AEGO0773393871</accountNumber>\n"
				+ "<description>Tickets for Rik de Vries</description>\n"
				+ "<startBalance>31.78</startBalance>\n"
				+ "<mutation>-6.14</mutation>\n"
				+ "<endBalance>25.64</endBalance>\n"
				+ "</record>\n"
				+ " <record reference= \"105549\">\n"
				+ "<accountNumber>NL43AEGO0773393871</accountNumber>\n"
				+ "<description>Flowers from Peter de Vries</description>\n"
				+ "<startBalance>105.75</startBalance>\n"
				+ "<mutation>-26.17</mutation>\n"
				+ "<endBalance>79.58</endBalance>\n"
				+ "</record>\n"
				+ "  <record reference= \"150438\">\n"
				+ "<accountNumber>NL74ABNA0248990274</accountNumber>\n"
				+ "<description>Tickets from Richard de Vries</description>\n"
				+ "<startBalance>10.1</startBalance>\n"
				+ "<mutation>-0.3</mutation>\n"
				+ "<endBalance>9.8</endBalance>\n"
				+ "</record>\n"
				+ "<record reference= \"172833\">\n"
				+ "<accountNumber>NL69ABNA0433647324</accountNumber>\n"
				+ "<description>Tickets for Willem Theu�</description>\n"
				+ "<startBalance>66.72</startBalance>\n"
				+ "<mutation>-41.74</mutation>\n"
				+ "<endBalance>24.98</endBalance>\n"
				+ "</record>\n"
				+ " <record reference= \"165102\">\n"
				+ "<accountNumber>NL93ABNA0585619023</accountNumber>\n"
				+ "<description>Tickets for Rik Theu�</description>\n"
				+ "<startBalance>3980</startBalance>\n"
				+ "<mutation>+1000</mutation>\n"
				+ "<endBalance>4981</endBalance>\n"
				+ "</record>\n"
				+ " <record reference= \"170148\">\n"
				+ "<accountNumber>NL43AEGO0773393871</accountNumber>\n"
				+ "<description>Flowers for Jan Theu�</description>\n"
				+ "<startBalance>16.52</startBalance>\n"
				+ "<mutation>+43.09</mutation>\n"
				+ "<endBalance>59.61</endBalance>\n"
				+ "</record>\n"
				+ "</records>";
		
		byte[] ptext = xmlAsString.getBytes(ISO_8859_1); 
		String inputString = new String(ptext, UTF_8); 
		
		return inputString;
	}

	public static String expectedOutput() {
		
		String expectedOutput = "Transaction reference number: 167875 , Description is :Tickets from Erik de Vries\n"
			+	"Transaction reference number: 165102 , Description is :Tickets for Rik Theu�\n";
		
		byte[] ptext = expectedOutput.getBytes(ISO_8859_1); 
		String expectedString = new String(ptext, UTF_8); 
		
		return expectedString;

	}

}
