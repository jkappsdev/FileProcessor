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

@Service
public class FileProcessorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessorService.class);

	public String determineFileType(MultipartFile receivedFile) {
		FileProcessorService.LOGGER.info("determineFileType");
		String fileName = receivedFile.getOriginalFilename();
		String fileType = fileName.substring(fileName.length() - 3);
		return fileType;
	}

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

	public List<Record> validateTransactions(List<Record> transactionRecords) {
		FileProcessorService.LOGGER.info("validateTransactions");
		Map<Integer, List<Record>> transactionRefCount = new HashMap<>();
		List<Record> incorrectTransactions = new ArrayList<>();
		for (Record bo : transactionRecords) {
			int transactionReference = bo.getReference();
			
			if (transactionRefCount.containsKey(transactionReference)) {
				transactionRefCount.get(transactionReference).add(bo);
			} else {
				addToExistingListForExistingTransaction(transactionRefCount, bo, transactionReference);
			}
			
			addIncorrectendValueTransactionToMap(incorrectTransactions, bo, calculateEndBalance(bo));
		}
		addDuplicateTransactionToMap(transactionRefCount, incorrectTransactions);
		return incorrectTransactions;
	}

	private void addToExistingListForExistingTransaction(Map<Integer, List<Record>> transactionRefCount, Record bo,
			int transactionReference) {
		List<Record> list = new ArrayList<>();
		list.add(bo);
		transactionRefCount.put(transactionReference, list);
	}

	private void addIncorrectendValueTransactionToMap(List<Record> incorrectTransactions, Record bo, float endBalance) {
		if (endBalance != bo.getEndBalance()) {
			incorrectTransactions.add(bo);
		}
	}

	private void addDuplicateTransactionToMap(Map<Integer, List<Record>> transactionRefCount,
			List<Record> incorrectTransactions) {
		for (Map.Entry<Integer, List<Record>> entry : transactionRefCount.entrySet()) {
			if (entry.getValue().size() > 1) {
				incorrectTransactions.addAll(entry.getValue());
			}
		}
	}

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

	private Float additiveMutation(NumberFormat formatter, float startBalance, String mutation, int length) {
		return new Float(formatter.format((startBalance + Float.parseFloat(mutation.substring(1, length)))));
	}

	private Float deductiveMutation(NumberFormat formatter, float startBalance, String mutation, int length) {
		return new Float(formatter.format((startBalance - Float.parseFloat(mutation.substring(1, length)))));
	}

	private NumberFormat requiredNumberFormat() {
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMinimumFractionDigits(2);
		formatter.setGroupingUsed(false);
		return formatter;
	}

}
