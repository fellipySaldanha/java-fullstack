package com.java.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.java.services.validation.ClientInsert;

import org.hibernate.validator.constraints.Length;

@ClientInsert
public class ClientNewDto implements Serializable {

	private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigátorio")
    @Length(min = 5, max = 120, message = "Tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento Obrigátorio")
    @Email(message = "Email inválido")
    private String email;
    
    @NotEmpty(message = "Preenchimento Obrigátorio")
    private String cpfOrCnpj;
    
    private Integer type;
    
    @NotEmpty(message = "Preenchimento Obrigátorio")
    private String place;

    @NotEmpty(message = "Preenchimento Obrigátorio")
	private String number;
    
    private String complement;
    private String neighborhood;
    
    @NotEmpty(message = "Preenchimento Obrigátorio")
    private String zipCode;
    
    @NotEmpty(message = "Preenchimento Obrigátorio")
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