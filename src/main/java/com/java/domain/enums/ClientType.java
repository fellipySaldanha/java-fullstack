package com.java.domain.enums;

public enum ClientType {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int code;
	private String description;
	
	private ClientType(int code, String description) {	
		this.code = code;
		this.description = description;
	}	
	
	public int getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static ClientType toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (ClientType i : ClientType.values()) {
			if (code.equals(i.getCode())) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Id inválido "+ code);
	}
}
