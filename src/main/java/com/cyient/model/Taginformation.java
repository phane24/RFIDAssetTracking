package com.cyient.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "TaguniqueInformation")
public class Taginformation implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getStartpoint() {
		return startpoint;
	}

	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}

	public String getTaguniqueid() {
		return taguniqueid;
	}

	public void setTaguniqueid(String taguniqueid) {
		this.taguniqueid = taguniqueid;
	}

	@Column(name="Ticket_Id")
	private String ticketid;
	
	@Column(name="Customer_Id")
	private String customerid;
	
	@Column(name="Start_Point")
	private String startpoint;

	@Column(name="Tag_Unique_Id")
	private String taguniqueid;
	
	@Column(name="Pre_Tag_Unique_Id")
	private String pre_uniq;
	
	public String getPre_uniq() {
		return pre_uniq;
	}

	public void setPre_uniq(String pre_uniq) {
		this.pre_uniq = pre_uniq;
	}

	@Column(name="fileName")
	 private String fileName;
	 
		@Column(name="filedata" ,length=16777215)
	    private byte[] filedata;

		public byte[] getFiledata() {
			return filedata;
		}

		public void setFiledata(byte[] filedata) {
			this.filedata = filedata;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	
		@Column(name="invJson",length=16777215)
		private String Invjson;

		@Column(name="cusJson",length=16777215)
		private String Cusjon;
		
		@Column(name="desJson",length=16777215)
		private String DesJson;

		public String getInvjson() {
			return Invjson;
		}

		public void setInvjson(String invjson) {
			Invjson = invjson;
		}

		public String getCusjon() {
			return Cusjon;
		}

		public void setCusjon(String cusjon) {
			Cusjon = cusjon;
		}

		public String getDesJson() {
			return DesJson;
		}

		public void setDesJson(String desJson) {
			DesJson = desJson;
		}
		
		

		

	

}