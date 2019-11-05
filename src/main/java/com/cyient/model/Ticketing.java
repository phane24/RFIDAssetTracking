package com.cyient.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Ticketing")
public class Ticketing implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@Column(name="Ticket_No")
	private String ticketNum;

	@Column(name="Ticket_Type")
	private String ticketType;	
	
	@Column(name="Region")
	private String region;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Exchange_Name")
	private String exchangeName;
	
	@Column(name="Floor")
	private String floor;	
	
	@Column(name="Suite")
	private String suite;
	
	@Column(name="Rack")
	private String rack;
	
	@Column(name="Sub_RAck")
	private String subrack;

	
	@Column(name="Ticket_Description")
	private String ticketDescription;
	
	@Column(name="Open_Date")
	@Temporal(TemporalType.DATE)
	private Date openDate;
	
	@Column(name="Open_Time")
	@Temporal(TemporalType.TIME)
	private Date openTime;
	
	@Column(name="Closed_Date")
	@Temporal(TemporalType.DATE)
	private Date closedDate;
	
	@Column(name="Closed_Time")
	@Temporal(TemporalType.TIME)
	private Date closedTime;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="Severity")
	private String severity;

	@Column(name="Unique_Id")
	private String uniqueId;
	
	@Column(name="Comments")
	private String comments;
	
	@Column(name="Remarks")
	private String remarks;
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToOne
    @JoinColumn(name = "Customer_Id")
	private Customer customer;
	
	
	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}
	
	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}		
	
	 public Customer getCustomer() {
		  return customer;
		 }
		 public void setCustomer(Customer customer) {
		  this.customer = customer;
		 }

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
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

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

}