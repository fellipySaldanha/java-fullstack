package com.java.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.java.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("paymentCard")
public class PaymentCard extends Payment{
	
	private static final long serialVersionUID = 1L;
	private Integer installments;
	
	public PaymentCard() {	
	}

	public PaymentCard(Integer id, PaymentStatus status, Order order, Integer installments) {
		super(id, status, order);		
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}	
	
}
