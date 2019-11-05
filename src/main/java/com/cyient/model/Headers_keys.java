package com.cyient.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "headers_keys")
public class Headers_keys implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;


	@Id
	@Column(name="secret_key")
	private String secret_key;	
	
	@Column(name="company_id")
	private String company_id;

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}




	

}