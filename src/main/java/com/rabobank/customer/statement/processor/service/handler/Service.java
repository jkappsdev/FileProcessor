package com.rabobank.customer.statement.processor.service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface Service<E> {
	
	@SuppressWarnings("rawtypes")
	public Service<?> NOT_IMPLEMENTED_SERVICE =  new Service() {

		@Override
		public String process(MultipartFile uploadfile) {
			return HttpStatus.NOT_IMPLEMENTED.toString();
		}
		
	};
	
	public String process(MultipartFile uploadfile);

}
