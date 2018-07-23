package com.rabobank.customer.statement.processor.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.customer.statement.processor.bo.Record;

/**
 * 
 * @author Karthik Janakiraman
 * 
 * The service class consists of various service methods that are required for processing the input files.
 *
 */
@Service
public class FileProcessorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessorService.class);

	/**
	 * Returns the type of the input file
	 * @param receivedFile
	 * @return fileType
	 */
	public String determineFileType(MultipartFile receivedFile) {
		FileProcessorService.LOGGER.info("determineFileType");
		String fileName = receivedFile.getOriginalFilename();
		String fileType = fileName.substring(fileName.length() - 3);
		return fileType;
	}

	/**
	 * Returns a string of invalid transactions with description for every invalid transaction.
	 * @param transactions
	 * @return string
	 */
	public String getInvalidTransactions(List<Record> transactions) {
		FileProcessorService.LOGGER.info("getInvalidTransactions");
		StringBuilder sb = new StringBuilder();
		for (Record record : transactions) {
			sb.append("Transaction reference number: ").append(record.getReference()).append(" , ")
					.append("Description is :").append(record.getDescription()).append("\n");
		}
		FileProcessorService.LOGGER.info("invalidTransactions \n" + sb.toString());
		return sb.toString();
	}

	/**
	 * Returns a list of invalid transactions
	 * @param transactionRecords
	 * @return
	 */
	public List<Record> validateTransactions(List<Record> transactionRecords) {
		FileProcessorService.LOGGER.info("validateTransactions");
		Map<Integer, List<Record>> transactionReferenceCountMap = new HashMap<>();
		List<Record> incorrectTransactions = new ArrayList<>();
		for (Record bo : transactionRecords) {
			int transactionReference = bo.getReference();
			
			if (transactionReferenceCountMap.containsKey(transactionReference)) {
				transactionReferenceCountMap.get(transactionReference).add(bo);
			} else {
				addToExistingListForDuplicateTransaction(transactionReferenceCountMap, bo, transactionReference);
			}
			
			addIncorrectEndValueTransactionToMap(incorrectTransactions, bo, calculateEndBalance(bo));
		}
		addDuplicateTransactionToList(transactionReferenceCountMap, incorrectTransactions);
		return incorrectTransactions;
	}

	/**
	 * Adds the duplicate transaction to the map
	 * @param transactionReferenceCountMap
	 * @param record
	 * @param transactionReference
	 */
	private void addToExistingListForDuplicateTransaction(Map<Integer, List<Record>> transactionReferenceCountMap, Record record,
			int transactionReference) {
		List<Record> list = new ArrayList<>();
		list.add(record);
		transactionReferenceCountMap.put(transactionReference, list);
	}

	/**
	 * Adds the invalid transaction, to the list containing the invalid transaction, whose end value is incorrect after mutation
	 * @param incorrectTransactions
	 * @param record
	 * @param endBalance
	 */
	private void addIncorrectEndValueTransactionToMap(List<Record> incorrectTransactions, Record record, float endBalance) {
		if (endBalance != record.getEndBalance()) {
			incorrectTransactions.add(record);
		}
	}

	/**
	 * Adds the duplicate transaction reference to the list containing the invalid transaction
	 * @param transactionRefCount
	 * @param incorrectTransactions
	 */
	private void addDuplicateTransactionToList(Map<Integer, List<Record>> transactionRefCount,
			List<Record> incorrectTransactions) {
		for (Map.Entry<Integer, List<Record>> entry : transactionRefCount.entrySet()) {
			if (entry.getValue().size() > 1) {
				incorrectTransactions.addAll(entry.getValue());
			}
		}
	}

	/**
	 * Calculate the end balance and returns the float value
	 * @param bo
	 * @return
	 */
	private float calculateEndBalance(Record bo) {
		NumberFormat formatter = requiredNumberFormat();
		float startBalance = bo.getStartBalance();
		String mutation = bo.getMutation();
		int length = mutation.length();
		if (mutation.substring(0, 1).equals("-")) {
			return deductiveMutation(formatter, startBalance, mutation, length);
		} else
			return additiveMutation(formatter, startBalance, mutation, length);
	}

	/**
	 * Calculate end value after mutation and returns the float value
	 * @param formatter
	 * @param startBalance
	 * @param mutation
	 * @param length
	 * @return
	 */
	private Float additiveMutation(NumberFormat formatter, float startBalance, String mutation, int length) {
		return new Float(formatter.format((startBalance + Float.parseFloat(mutation.substring(1, length)))));
	}

	/**
	 * Calculate end value after mutation and returns the float value
	 * @param formatter
	 * @param startBalance
	 * @param mutation
	 * @param length
	 * @return
	 */
	private Float deductiveMutation(NumberFormat formatter, float startBalance, String mutation, int length) {
		return new Float(formatter.format((startBalance - Float.parseFloat(mutation.substring(1, length)))));
	}

	/**
	 * Returns the required number formatter
	 * @return
	 */
	private NumberFormat requiredNumberFormat() {
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMinimumFractionDigits(2);
		formatter.setGroupingUsed(false);
		return formatter;
	}

}
