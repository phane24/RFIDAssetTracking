package com.cyient.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id	
	@Column(name="Customer_Id")
	private String customerId;

	@Column(name="Exchange_Name")
	private String exchangeName;	
	
	@Column(name="Customer_Name")
	private String customerName;
	
	@Column(name="Phone_No")
	private long phoneNum;	
	
	@Column(name="City")
	private String city;	
	
	@Column(name="Postal_Code")
	private String postalCode;
	
	@Column(name="Address")
	private String address;	
	
	@Column(name="Customer_Status")
	private String customerStatus;
	
	@Column(name="User_Id")
	private String userId;	
	
	@Column(name="Employee_Name")
	private String employeeName;
	
	@Column(name="Surname")
	private String surname;	
	
	@Column(name="Given_Name")
	private String givenName;	
	
	@Column(name="FAX")
	private String fax;
	
	@Column(name="Email")
	private String email;	
	
	@Column(name="Classification")
	private String classification;

	@Column(name="Partial")
	private String partial;
	
	@Column(name="Reporting_Category")
	private String reportingCategory;	
	
	@Column(name="Default_Item_Status")
	private String defaultItemStatus;	

	@Column(name="Initial_Screen")
	private String intialScreen;
	
	@Column(name="Region")
	private String region;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getPartial() {
		return partial;
	}

	public void setPartial(String partial) {
		this.partial = partial;
	}

	public String getReportingCategory() {
		return reportingCategory;
	}

	public void setReportingCategory(String reportingCategory) {
		this.reportingCategory = reportingCategory;
	}

	public String getDefaultItemStatus() {
		return defaultItemStatus;
	}

	public void setDefaultItemStatus(String defaultItemStatus) {
		this.defaultItemStatus = defaultItemStatus;
	}

	public String getIntialScreen() {
		return intialScreen;
	}

	public void setIntialScreen(String intialScreen) {
		this.intialScreen = intialScreen;
	}

}