package com.java.domain.enums;

public enum PaymentStatus {
	PENDING(1, "Pendente"),
	PAID(2, "Quitado"),
	CANCELED(3, "Cancelado");
	
	private int code;
	private String description;
	
	private PaymentStatus(int code, String description) {	
		this.code = code;
		this.description = description;
	}	
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (PaymentStatus i : PaymentStatus.values()) {
			if (code.equals(i.getCode())) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Id inválido "+ code);
	}
	
}
