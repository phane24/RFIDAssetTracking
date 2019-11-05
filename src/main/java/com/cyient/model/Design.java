package com.cyient.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Design")
public class Design implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@Column(name="Tag_Id")
	private int tagId;

	@ManyToOne
	@JoinColumn(name="Customer_Id")	
	private Customer customerId;

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
	
	@Column(name="Parent")
	private String parent;	
	
	@Column(name="Direction")
	private String direction;
	
	@Column(name="Status")
	private String status;

	@Column(name="Priority")
	private String priority;	
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Floor")
	private String floor;	
	
	@Column(name="Suite")
	private String suite;
	
	@Column(name="Vert_In")
	private String vertIn;

	@Column(name="Block")
	private String block;	
	
	@Column(name="Posn")
	private String posn;
	
	@Column(name="Vert_Out")
	private String vertOut;
	
	@Column(name="Port_ID")
	private String portId;	
	
	@Column(name="Rack")
	private String rack;
	
	@Column(name="Sub_RAck")
	private String subrack;

	@Column(name="Card")
	private String card;	
	
	@Column(name="Image")
	private Blob image;
	
	@Column(name="Region")
	private String region;	
	
	@Column(name="City")
	private String city;
	
	
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getVertIn() {
		return vertIn;
	}

	public void setVertIn(String vertIn) {
		this.vertIn = vertIn;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getPosn() {
		return posn;
	}

	public void setPosn(String posn) {
		this.posn = posn;
	}

	public String getVertOut() {
		return vertOut;
	}

	public void setVertOut(String vertOut) {
		this.vertOut = vertOut;
	}

	public String getPortId() {
		return portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getSubrack() {
		return subrack;
	}

	public void setSubrack(String subrack) {
		this.subrack = subrack;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}	
	
	
}