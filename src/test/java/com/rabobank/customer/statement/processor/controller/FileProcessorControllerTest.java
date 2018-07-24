package com.rabobank.customer.statement.processor.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.customer.statement.processor.controller.common.CsvIO;
import com.rabobank.customer.statement.processor.controller.common.MultiPartFileInput;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FileProcessorController.class, secure = false)
public class FileProcessorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	FileProcessorService service;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(new FileProcessorController(service)).build();
	}

	@Test
	public void testProcessFile() throws Exception {
		MockMultipartFile records = MultiPartFileInput.generateInputMultiPartFile(CsvIO.getCSVInput());

		when(service.determineFileType(Mockito.<MultipartFile> any())).thenReturn("csv");
		
		String expectedString = CsvIO.expectedOutput();
		
		MvcResult result = mockMvc
				.perform(multipart("/processFile").file(records).contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
				.andExpect(status().isOk()).andReturn();
		

		System.out.println("expectedString :" + expectedString);
		System.out.println("result.getResponse().getContentAsString(): " + result.getResponse().getContentAsString());
		assertEquals(expectedString, result.getResponse().getContentAsString());

	}

}
