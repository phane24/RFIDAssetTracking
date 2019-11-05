package com.cyient.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Inventory")
public class Inventory implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;
	
	@Id
	@Column(name="Equipment_Id")
	private String equipmentId;
		
	@ManyToOne
    @JoinColumn(name = "Customer_id")
	private Customer customerId;	
	
	@Column(name="Equipment_Name")
	private String equipmentName;
	
	@Column(name="Exchange_Name")
	private String exchangeName;
	
	@Column(name="Shelf_Name")
	private String shelfName;
	
	@Column(name="Start_Point")
	private String startPoint;	
	
	@Column(name="End_Point")
	private String endPoint;	
	
	@Column(name="Length")
	private String length;

	@Column(name="Inventory_Status_Code")
	private String inventoryStatusCode;
	
	@Column(name="Ownership_Type_Code")
	private String ownershipTypeCode;
	
	@Column(name="Equipment_Ref_Name")
	private String equipmentRefName;
	
	@Column(name="Deployment_Ref_Number")
	private String deploymentRefNumber;
	
	@Column(name="Job_Number")
	private String jobNumber;
	
	@Column(name="Site_Name")
	private String siteName;
	
	@Column(name="Placement_Date")
	@Temporal(TemporalType.DATE)
	private Date placementDate;
	
	@Column(name="Activation_Status")
	private String activationStatus;
	
	@Column(name="Account_Code")
	private String accountCode;
	
	@Column(name="Installed_Date")
	@Temporal(TemporalType.DATE)
	private Date installedDate;
	
	@Column(name="Material_Cost")
	private String materialCost;
	
	@Column(name="Administrative_State")
	private String administrativeState;
	
	@Column(name="Operational_State")
	private String operationalState;
	
	@Column(name="Planning_System_Name")
	private String planningSystemName;
	
	@Column(name="Longitude")
	private String longitude;
	
	@Column(name="Latitude")
	private String latitude;
	
	@Column(name="City")
	private String city;
	
	@Column(name="District")
	private String district;
	
	@Column(name="Region")
	private String region;
	
	@Column(name="Remarks")
	private String remarks;

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public Customer getCustomerId() {
		return customerId;
	}
	
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public String getInventoryStatusCode() {
		return inventoryStatusCode;
	}

	public void setInventoryStatusCode(String inventoryStatusCode) {
		this.inventoryStatusCode = inventoryStatusCode;
	}

	public String getOwnershipTypeCode() {
		return ownershipTypeCode;
	}

	public void setOwnershipTypeCode(String ownershipTypeCode) {
		this.ownershipTypeCode = ownershipTypeCode;
	}

	public String getEquipmentRefName() {
		return equipmentRefName;
	}

	public void setEquipmentRefName(String equipmentRefName) {
		this.equipmentRefName = equipmentRefName;
	}

	public String getDeploymentRefNumber() {
		return deploymentRefNumber;
	}

	public void setDeploymentRefNumber(String deploymentRefNumber) {
		this.deploymentRefNumber = deploymentRefNumber;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Date getPlacementDate() {
		return placementDate;
	}

	public void setPlacementDate(Date placementDate) {
		this.placementDate = placementDate;
	}

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Date getInstalledDate() {
		return installedDate;
	}

	public void setInstalledDate(Date installedDate) {
		this.installedDate = installedDate;
	}

	public String getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(String materialCost) {
		this.materialCost = materialCost;
	}

	public String getAdministrativeState() {
		return administrativeState;
	}

	public void setAdministrativeState(String administrativeState) {
		this.administrativeState = administrativeState;
	}

	public String getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(String operationalState) {
		this.operationalState = operationalState;
	}

	public String getPlanningSystemName() {
		return planningSystemName;
	}

	public void setPlanningSystemName(String planningSystemName) {
		this.planningSystemName = planningSystemName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}