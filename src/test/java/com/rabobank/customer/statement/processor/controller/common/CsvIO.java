package com.rabobank.customer.statement.processor.controller.common;

public class CsvIO {
	
	public static String getCSVInput() {
		String inputString = "Reference,AccountNumber,Description,Start Balance,Mutation,End Balance\n"
				+ "194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23\n"
				+ "112806,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8\n"
				+ "183049,NL69ABNA0433647324,Clothes for Jan King,86.66,+44.5,131.16\n"
				+ "183356,NL74ABNA0248990274,Subscription for Peter de Vries,92.98,-46.65,46.33\n"
				+ "112806,NL69ABNA0433647324,Clothes for Richard de Vries,90.83,-10.91,79.92\n"
				+ "112806,NL93ABNA0585619023,Tickets from Richard Bakker,102.12,+45.87,147.99\n"
				+ "139524,NL43AEGO0773393871,Flowers from Jan Bakker,99.44,+41.23,140.67\n"
				+ "179430,NL93ABNA0585619023,Clothes for Vincent Bakker,23.96,-27.43,-3.47\n"
				+ "141223,NL93ABNA0585619023,Clothes from Erik Bakker,94.25,+41.6,135.85\n"
				+ "195446,NL74ABNA0248990274,Flowers for Willem Dekker,26.32,+48.98,75.3";
		return inputString;
	}

	public static String expectedOutput() {
		String expectedString = "Transaction reference number: 112806 , Description is :Clothes for Willem Dekker\n"
				+ "Transaction reference number: 112806 , Description is :Clothes for Richard de Vries\n"
				+ "Transaction reference number: 112806 , Description is :Tickets from Richard Bakker\n";
		return expectedString;
	}

}
