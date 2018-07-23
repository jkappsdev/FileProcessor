package com.rabobank.customer.statement.processor.file.processor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rabobank.customer.statement.processor.controller.common.CsvIO;
import com.rabobank.customer.statement.processor.controller.common.MultiPartFileInput;

public class CsvFileProcessorTest {

	@Test
	public void testProcess() {
		assertEquals(CsvIO.expectedOutput(), 
				new CsvFileProcessor<>().process(MultiPartFileInput.generateInputMultiPartFile(CsvIO.getCSVInput())));
	}

}
