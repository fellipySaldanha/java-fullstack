package com.java.dto;

import java.io.Serializable;

public class ClientNewDto implements Serializable {

	private static final long serialVersionUID = 1L;

    private String name;
	private String email;
	private String cpfOrCnpj;
    private Integer type;
    
    private String place;
	private String number;
	private String complement;
	private String neighborhood;
    private String zipCode;
    
    private String phone01;
    private String phone02;
    private String phone03;

    private Integer cityId;

    public ClientNewDto(){        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone01() {
        return phone01;
    }

    public void setPhone01(String phone01) {
        this.phone01 = phone01;
    }

    public String getPhone02() {
        return phone02;
    }

    public void setPhone02(String phone02) {
        this.phone02 = phone02;
    }

    public String getPhone03() {
        return phone03;
    }

    public void setPhone03(String phone03) {
        this.phone03 = phone03;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }    
    
}