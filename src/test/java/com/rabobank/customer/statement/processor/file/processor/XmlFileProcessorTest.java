package com.rabobank.customer.statement.processor.file.processor;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.rabobank.customer.statement.processor.bo.Record;
import com.rabobank.customer.statement.processor.controller.FileProcessorService;
import com.rabobank.customer.statement.processor.controller.common.MultiPartFileInput;
import com.rabobank.customer.statement.processor.controller.common.XmlIO;

public class XmlFileProcessorTest {

	@Test
	public void testProcess() {
		assertEquals(XmlIO.expectedOutput(),
				new XmlFileProcessor<>().process(MultiPartFileInput.generateInputMultiPartFile(XmlIO.getXMLInput())));
	}

	@Test
	public void someMethod() {
		FileProcessorService mock = Mockito.mock(FileProcessorService.class);
		Mockito.when(mock.validateTransactions(Mockito.<List<Record>>any()))
				.thenThrow(new RuntimeException("My Message"));
		assertEquals("Error while processing the xml file My Message", new XmlFileProcessor<>(mock)
				.process(MultiPartFileInput.generateInputMultiPartFile(XmlIO.getXMLInput())));
	}

}
