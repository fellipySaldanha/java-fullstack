package com.java.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.java.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("paymentBankBill")
public class PaymentSlip extends Payment {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymenDate;

	public PaymentSlip() {
	}

	public PaymentSlip(Integer id, PaymentStatus status, Order order, Date dueDate, Date paymentDate) {
		super(id, status, order);		
		this.dueDate = dueDate;
		this.paymenDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymenDate() {
		return paymenDate;
	}

	public void setPaymenDate(Date paymenDate) {
		this.paymenDate = paymenDate;
	}	
	
}
