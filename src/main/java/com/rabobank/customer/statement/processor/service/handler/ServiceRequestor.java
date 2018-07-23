package com.rabobank.customer.statement.processor.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceRequestor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestor.class);

	@SuppressWarnings("unchecked")
	public static final <E> Service<E> requestService(String id) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		try {
			ServiceRequestor.LOGGER.info("requestService" + " creating service for the bean with id, \"" + id + "\"");
			return (Service<E>) context.getBean(id);
		} catch (BeansException beanException) {
			ServiceRequestor.LOGGER.info("requestService" + " Exception occured while receiveing Object for service " + id + " " + beanException);
			return (Service<E>) Service.NOT_IMPLEMENTED_SERVICE;
		}
	}
}
