package com.rabobank.customer.statement.processor.controller.common;

import java.nio.charset.StandardCharsets;

import org.springframework.mock.web.MockMultipartFile;

public class MultiPartFileInput {

	public static MockMultipartFile generateInputMultiPartFile(String inputString) {
		MockMultipartFile receivedFile = new MockMultipartFile("receivedFile", "records.csv", "text/plain",
				inputString.getBytes(StandardCharsets.UTF_8));
		return receivedFile;
	}

}
