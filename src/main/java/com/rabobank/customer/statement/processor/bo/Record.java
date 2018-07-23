package com.rabobank.customer.statement.processor.bo;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
	
	@XmlAttribute
	private int reference;
	@XmlElement(name="accountNumber")
	private String accountNumber;
	@XmlElement(name="startBalance")
	private float startBalance;
	@XmlElement(name="mutation")
	private String mutation;
	@XmlElement(name="description")
	private String description;
	@XmlElement(name="endBalance")
	private float endBalance;
	
}
