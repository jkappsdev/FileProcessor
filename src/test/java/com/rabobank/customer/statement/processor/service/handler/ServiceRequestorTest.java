package com.rabobank.customer.statement.processor.service.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.rabobank.customer.statement.processor.file.processor.CsvFileProcessor;

public class ServiceRequestorTest {

	@Test
	public void givenFileTypeAsCSV_WhenRequestedForBean_ShouldProvideCSVFileProcessorInstance() {
		assertNotNull(ServiceRequestor.requestService("csv"));
		assertEquals(CsvFileProcessor.class, ServiceRequestor.requestService("csv").getClass());
	}

	@Test
	public void givenInvalidFileType_WhenRequestedForBean_ShouldProvideEmptyService() {
		assertEmptyService(ServiceRequestor.requestService(""));
		assertEmptyService(ServiceRequestor.requestService("CSV"));
	}

	private void assertEmptyService(Service<Object> service) {
		assertNotNull(service);
		assertEquals(HttpStatus.NOT_IMPLEMENTED.toString(), service.process(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenNullFileType_WhenRequestedForBean_ShouldThrowBeanException() {
		assertNotNull(ServiceRequestor.requestService(null));
	}

}
