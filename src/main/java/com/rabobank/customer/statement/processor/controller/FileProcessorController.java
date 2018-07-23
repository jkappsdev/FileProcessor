package com.rabobank.customer.statement.processor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.customer.statement.processor.service.handler.ServiceRequestor;

@RestController
public class FileProcessorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessorController.class);
	
	private FileProcessorService service;

	@Autowired
	public FileProcessorController(FileProcessorService service) {
		this.service = service;
	}

	@PostMapping
	@RequestMapping(path = "/processFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> processFile(@RequestParam(required = false) MultipartFile receivedFile) {
		System.out.println("Hey Man" + receivedFile);
		try {
			FileProcessorController.LOGGER.info("processFile" + " -  " +  receivedFile.getOriginalFilename());
			String fileType = this.service.determineFileType(receivedFile);
			return ResponseEntity.ok().body(ServiceRequestor.requestService(fileType).process(receivedFile));
		} catch (Exception e) {
			FileProcessorController.LOGGER.info("processFile" + " -  " + "Exception " + e.getMessage());
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
