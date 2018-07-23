package com.rabobank.customer.statement.processor.file.processor;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.customer.statement.processor.bo.Records;
import com.rabobank.customer.statement.processor.controller.FileProcessorService;
import com.rabobank.customer.statement.processor.service.handler.Service;

/**
 * 
 * @author Karthik Janakiraman
 * 
 * The class overrides the process method from Service interface for processing XML files
 *
 * @param <E>
 */
public class XmlFileProcessor<E> implements Service<E> {

	public XmlFileProcessor() {
		this.service = new FileProcessorService();
	}
	
	public XmlFileProcessor(FileProcessorService service) {
		super();
		this.service = service;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlFileProcessor.class);
	
	private FileProcessorService service = null;
	
	@Override
	public String process(MultipartFile file) {
		XmlFileProcessor.LOGGER.info("process");
		Records records = constructRecordBOFromFile(file);
		try {
			return service.getInvalidTransactions(service.validateTransactions(records.getRecords()));
		} catch (Exception e) {
			XmlFileProcessor.LOGGER.info("process" + " -  " + "Exception " + e.getMessage());
			return "Error while processing the xml file " + e.getMessage();
		}
	}

	/**
	 * Constructs RecordBO from the Multipartfile input
	 * @param file
	 * @return
	 */
	private Records constructRecordBOFromFile(MultipartFile file) {
		XmlFileProcessor.LOGGER.info("constructRecordBOFromFile");
		Records records = null;
		try {
			StringReader sr = new StringReader(new String(file.getBytes()));
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			records = (Records) unmarshaller.unmarshal(sr);
		} catch (Exception e) {
			XmlFileProcessor.LOGGER.info("constructRecordBOFromFile" + " -  " + "Exception " + e.getMessage());
		}
		return records;
	}

}
