package com.rabobank.customer.statement.processor.file.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.customer.statement.processor.bo.Record;
import com.rabobank.customer.statement.processor.controller.FileProcessorService;
import com.rabobank.customer.statement.processor.service.handler.Service;

/**
 * 
 * @author Karthik Janakiraman
 * 
 * The class overrides the process method from Service interface for processing CSV files
 *
 * @param <E>
 */
public class CsvFileProcessor<E> implements Service<E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileProcessor.class);
	
	private FileProcessorService service = new FileProcessorService();

	
	@Override
	public String process(MultipartFile bytes) {
		CsvFileProcessor.LOGGER.info("process");
		try {
			String[] arrayOfRecord = new String(bytes.getBytes()).split("[\\r\\n]+");
			List<Record> transactions = constructRecordBOFromFile(arrayOfRecord);
			return service.getInvalidTransactions(service.validateTransactions(transactions));
		} catch (Exception e) {
			CsvFileProcessor.LOGGER.info("process" + " -  " + "Exception " + e.getMessage());
			return "Error while processing the csv file " + e.getMessage();
		}
	}

	/**
	 * Constructs the required transaction object from the Multipartfile input
	 * @param arrayOfRecord
	 * @return
	 */
	private List<Record> constructRecordBOFromFile(String[] arrayOfRecord) {
		CsvFileProcessor.LOGGER.info("constructRecordBOFromFile");
		List<Record> transactions =  new ArrayList<>();
		for (int x = 1; x < arrayOfRecord.length; x++) {
			String[] record = arrayOfRecord[x].split(",");
			Record rec = new Record();
			for (int i = 0; i < record.length; i++) {
				rec.setReference(Integer.parseInt(record[0]));
				rec.setAccountNumber((record[1]));
				rec.setDescription(record[2]);
				rec.setStartBalance(Float.parseFloat(record[3]));
				rec.setMutation(record[4]);
				rec.setEndBalance(Float.parseFloat(record[5]));
			}
			transactions.add(rec);
		} 
		return transactions;
	}

}
