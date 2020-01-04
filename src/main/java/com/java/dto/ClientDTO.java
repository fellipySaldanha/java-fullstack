package com.java.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.java.domain.Client;
import com.java.services.validation.ClientUpdate;

import org.hibernate.validator.constraints.Length;

@ClientUpdate
public class ClientDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigátorio")
    @Length(min = 5, max = 120, message = "Tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento Obrigátorio")
    @Email(message = "Email inválido")
    private String email;

    public ClientDTO(){        
    }

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        email = client.getEmail();        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    

}
