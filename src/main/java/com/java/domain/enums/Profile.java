package com.java.domain.enums;

public enum Profile {
	ADMIN(1, "ROLE_ADM"),
	CLIENT(2, "ROLE_CLIENT");	
	
	private int code;
	private String description;
	
	private Profile(int code, String description) {	
		this.code = code;
		this.description = description;
	}	
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static Profile toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (Profile i : Profile.values()) {
			if (code.equals(i.getCode())) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Id inválido "+ code);
	}
	
}
