package com.java.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.java.domain.enums.PaymentStatus;

@Entity
public class PaymentBankBill extends Payment{
	
	private static final long serialVersionUID = 1L;
	private Date dueDate;
	private Date paymenDate;
	
	public PaymentBankBill() {	
	}

	public PaymentBankBill(Integer id, PaymentStatus status, Order order, Date dueDate, Date paymentDate) {
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
