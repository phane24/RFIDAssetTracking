package com.cyient.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.cyient.model.Customer;
import com.cyient.model.Design;
import com.cyient.model.Executive;
import com.cyient.model.ExecutiveTicketInfo;
import com.cyient.model.Inventory;
import com.cyient.model.Regions;
import com.cyient.model.Taginformation;
import com.cyient.model.Taginformation_history;
import com.cyient.model.Ticketing;
import com.cyient.model.User;

public interface RFIDDAO {
	
	@Transactional
	public void addUser(User user);
	
	@Transactional
	public User getAllUsersOnCriteria(String username,String password,String type);

	
	@Transactional
	public void addExecutive(Executive executive);
	
	@Transactional
	public void addExecutiveIntoUsers(User executive); 
	
	@Transactional
	public void addTicket(Ticketing ticket);
	
	@Transactional
	public List<Executive> getExecutiveId();
	
	@Transactional
	public String getManagerId(String managerName);
	 
	@Transactional
	public List<Ticketing> getTicketId();
	
	@Transactional
	public List<Ticketing> getUniqueId();
	

	
	@Transactional
	 public List<Ticketing> getOpenTicketsData();
	
	@Transactional
	 public List<Ticketing> adminOpenTicketsCountData();
	
	@Transactional
	 public List<Ticketing> openTicketsCountData();
	
	@Transactional
	 public List<Ticketing> getClosedTicketsData();
		
	@Transactional
	 public List<ExecutiveTicketInfo> getAssignedTicketsData();
	
	@Transactional
	 public List<Ticketing> closedTicketsCountData();
	
	@Transactional
	public List<ExecutiveTicketInfo> assignedTicketsCountData();
	
	@Transactional
	 public List<Ticketing> getHistoryTicketsData();
	
	@Transactional
	 public List<Ticketing> historyTicketsCountData();
	
	@Transactional
	 public List<Executive> getAllExecutivesData();
	
	@Transactional
	 public List<Ticketing> getAllTicketsData();
	
	@Transactional
	 public List<Ticketing> getAllTicketsCountData();
	
	@Transactional
	 public Executive getExecutivesData(String executiveId);
	
	@Transactional
	 public List<Ticketing> getTicketsData(String ticketNum);
	
	@Transactional
	public String assignExecutive(ExecutiveTicketInfo executiveTicket);
	
	@Transactional
	 public List<Ticketing> getExecAssignedTicketsData(String username);
	
	@Transactional
	 public List<Ticketing> getExecClosedTicketsData(String username);
	
	@Transactional
	 public List<Ticketing> getExecAssignedTicketsCountData(String username);
	
	@Transactional
	 public List<Ticketing> getExecClosedTicketsCountData(String username);
	
	@Transactional
	 public List<Ticketing> getUnassignedOpenTicketsData();
	
	@Transactional
	 public List<Ticketing> getUnassignedOpenTicketsCountData();
	
	@Transactional
	 public List<Executive> getUnassignedExecutivesData(String region,String city);
	
	@Transactional
	 public List<Design> getDesignData(String customerId);

	 @Transactional
	 public List<Regions> getRegion();
	 
	 @Transactional
	 public String getUserName(String username,String role);
	 
	 @Transactional
	 public List<Regions> getCity(String selectedRegion);
	 
	  @Transactional
	 public List<Regions> getManager(String region);
	 	 
	 @Transactional
	 public List<Regions> getExchange(String selectedCity);
	 
	 @Transactional
	 public List<Ticketing> getExistingRegion();
	 
	 @Transactional
	 public List<Ticketing> getExistingCity(String selectedRegion);
	 
	 @Transactional
	 public List<Ticketing> getExistingExchange(String selectedCity);
	 
	 @Transactional
	 public List<Ticketing> getExistingFloor(String exchange);
	 	 
	 @Transactional
	 public List<Ticketing> getExistingSuite(String exchange,String floor);	 
	 
	 @Transactional
	 public List<Ticketing> getExistingRack(String exchange,String floor,String suite);
	 
	 @Transactional
	 public List<Ticketing> getExistingSubRack(String exchange,String floor,String suite,String rack);
	 
	 @Transactional
	 public ArrayList<String> getExistingCustomerId(String exchange,String floor,String suite,String rack,String subRack);
	 	 
	 @Transactional
	 public List<Ticketing> getExistUniqueId(String selectedExchange,String selectedCustomerId);
	 
	 @Transactional
	 public String TestUniqueIdExist(String UniqueId);
	 
	 @Transactional
	 public String TestCustIdExist(String CustId);
	 
	 @Transactional
	 public List<Design> getStartnEndPoint(String selectedExchange,String selectedCustomerId);
	 
	 @Transactional
	 public List<Design> getFloor(String exchange);
	 
	 @Transactional
	 public List<Design> getSuite(String exchange,String floor);
	 
	 @Transactional
	 public List<Design> getRack(String exchange,String floor,String suite);

	 @Transactional
	 public List<Design> getSubRack(String exchange,String floor,String suite,String rack);
	 
	 @Transactional
	 public ArrayList<String> getCustomerId(String exchange,String floor,String suite,String rack,String subRack);
	 
	 @Transactional
	 public List<Inventory> fetchInventoryDetailsbyCustomer(String cid);
	 
	 @Transactional
     public List<Customer> getCustomerDetails(String Customerid);
	 
	@Transactional
	public void saveCustomer(String custId,String customerJson);		
	
	@Transactional
	public void saveInventory(String custId,String inventoryJson);
	
	@Transactional
	public void saveDesign(String custId,String designJson);
	 
	 @Transactional
    public String fetchtUniqueId(String Customerid,String ticketId);
	 
	 @Transactional
	 public String saveTaginfo(Taginformation taginformation);

	 @Transactional
	 public List<User> getManagerDetails(String managerId);
	 
	 @Transactional
     public List<ExecutiveTicketInfo> managerTotalTickets(String username);
	 
	 @Transactional
	 public List<ExecutiveTicketInfo> managerRejectedTickets(String username);
	 	 
	 @Transactional
     public List<Executive> getManagerTechnicians(String username);
	 
	 @Transactional
     public List<ExecutiveTicketInfo> managerTotalTicketsCount(String username);
	 
	 @Transactional
	 public List<ExecutiveTicketInfo> managerRejectedTicketsCount(String username);
	 	 
	 @Transactional
	 public List<Inventory> getAllExchanges();
	 
	 @Transactional
	 public List<Design> getDesignExchanges();
	 
	 @Transactional
	 public String ASCIItoHEX(String ascii);
	 
	 @Transactional
	 public String updateTicketStatus(String ticketId);
	 
	 @Transactional
	 public String getExistRegion(String region);
	 
	 @Transactional
	 public List<Design> getDesignShelf(String exchangeName);
	 
	 @Transactional
	 public List<Inventory> getInvShelf(String exchangeName);
	 
	 @Transactional
	 public String updateTicketingStatus(String ticketId);
	 
	  @Transactional
	 public List<User> getRoles(String userName);
		 
	 @Transactional
	 public String saveExistingTaginfo(Taginformation taginformation,String previous_cid);
	 
	 @Transactional
	 public List<Taginformation> fetchTaginfoByCid(String Pre_cid);
	 
	 @Transactional
	 public String saveTag_hist(Taginformation_history taghist);
	 
	 @Transactional
	 public String delete_taginfo(Taginformation taghist,String cid);

	 @Transactional
	 public String save_newTaginfo(Taginformation taginfo);

	 @Transactional
	public List fetchTaginfo(String cid, String tid);
	 
	 @Transactional
	public String saveTechStatus(String ticketId, String techStatus,String execId, String commentsData, String remarksData);

	 @Transactional
	public List<Ticketing> getExecAcceptedTicketsData(String username);

	 @Transactional
	public List<Ticketing> getExecAcceptedTicketsCountData(String username);

	 @Transactional
	public List<ExecutiveTicketInfo> getUnassignedUnRejectedExecutives(String ticketId);

	 @Transactional
	public List<Executive> getUnassignedUnRejectedExecutivesData(String region, String city, String listExecs);
}
