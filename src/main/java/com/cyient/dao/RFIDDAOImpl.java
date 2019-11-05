package com.cyient.dao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Repository
public class RFIDDAOImpl implements RFIDDAO {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
	@Override
	public User getAllUsersOnCriteria(String username,String password,String type) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
        c.add(Restrictions.eq("username",username));
        c.add(Restrictions.eq("password",password));
		c.add(Restrictions.eq("type",type));
        return (User)c.uniqueResult();
	} 
	
	@Override
	public void addExecutive(Executive executive) {
		sessionFactory.getCurrentSession().saveOrUpdate(executive);
	}

	@Override
	public void addExecutiveIntoUsers(User executive){
		sessionFactory.getCurrentSession().saveOrUpdate(executive);
	}
	
	@Override
	public void addTicket(Ticketing ticket){
		
		sessionFactory.getCurrentSession().saveOrUpdate(ticket);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getOpenTicketsData() {		
		return sessionFactory.getCurrentSession().createQuery("from Ticketing where status='Open'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> adminOpenTicketsCountData() {		
		return sessionFactory.getCurrentSession().createQuery("select count(*) from Ticketing where status='Open'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> openTicketsCountData() {		
		return sessionFactory.getCurrentSession().createQuery("select count(*) FROM Ticketing where status='Open' OR status='Not Accepted'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> closedTicketsCountData() {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		 String date1 = dateFormat.format(cal.getTime());
		 String date2 = dateFormat.format(cal1.getTime());
		return sessionFactory.getCurrentSession().createQuery("select count(*) from Ticketing where Status='Closed' and Closed_Date<='"+date2+"' and Closed_Date>='"+date1+"'").list();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> historyTicketsCountData() {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -7);	
		 String date1 = dateFormat.format(cal.getTime());		
		return sessionFactory.getCurrentSession().createQuery("select count(*) from ExecutiveTicketInfo where Status='Closed'").list();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getClosedTicketsData() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		 String date1 = dateFormat.format(cal.getTime());
		 String date2 = dateFormat.format(cal1.getTime());
		return sessionFactory.getCurrentSession().createQuery("from Ticketing where Status='Closed' and Closed_Date<='"+date2+"' and Closed_Date>='"+date1+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveTicketInfo> getAssignedTicketsData() {
		
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where status='InProgress' or status='Accepted'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getHistoryTicketsData() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -7);	
		 String date1 = dateFormat.format(cal.getTime());		
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where Status='Closed'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Executive> getAllExecutivesData() {
		return sessionFactory.getCurrentSession().createCriteria(Executive.class).list();
	}
	
	@Override
	public Executive getExecutivesData(String executiveId) {
		return (Executive) sessionFactory.getCurrentSession().get(Executive.class, executiveId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getTicketsData(String ticketNum) {
		return sessionFactory.getCurrentSession().createQuery("from Ticketing where Ticket_No='"+ticketNum+"'").list();
	}
	
	@Override
	public String assignExecutive(ExecutiveTicketInfo executiveTicket){
		sessionFactory.getCurrentSession().saveOrUpdate(executiveTicket);
		return "Assigned";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExecAssignedTicketsData(String username) {	
		System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where Status='InProgress' and executiveId='"+username+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExecClosedTicketsData(String username) {
		System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where Status='Closed' and executiveId='"+username+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExecAssignedTicketsCountData(String username) {	
		System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("select count(*) from ExecutiveTicketInfo where Status='InProgress' and executiveId='"+username+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExecClosedTicketsCountData(String username) {
		System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("select count(*) from ExecutiveTicketInfo where Status='Closed' and executiveId='"+username+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getAllTicketsData() {
		return sessionFactory.getCurrentSession().createQuery("from Ticketing").list();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getAllTicketsCountData() {
		return sessionFactory.getCurrentSession().createQuery("select count(*) from Ticketing").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getUnassignedOpenTicketsData(){
		return sessionFactory.getCurrentSession().createQuery("FROM Ticketing where status='Open' OR status='Not Accepted'").list();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getUnassignedOpenTicketsCountData(){
		return sessionFactory.getCurrentSession().createQuery("select count(*) FROM Ticketing where status='Open' OR status='Not Accepted'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Executive> getExecutiveId(){		
		return sessionFactory.getCurrentSession().createQuery("select executiveId from Executive where id=(select max(id) from Executive)").list();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getTicketId(){		
		return sessionFactory.getCurrentSession().createQuery("select ticketNum from Ticketing where id=(select max(id) from Ticketing)").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getUniqueId(){
		
		return sessionFactory.getCurrentSession().createQuery("select uniqueId from Ticketing where id=(select max(id) from Ticketing)").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Executive> getUnassignedExecutivesData(String region,String city){
		return sessionFactory.getCurrentSession().createQuery("FROM Executive where region='"+region+"' and city ='"+city+"'").list();
		//return sessionFactory.getCurrentSession().createQuery("FROM Executive where region='"+region+"' and city ='"+city+"' and executiveId NOT IN (SELECT executiveId FROM ExecutiveTicketInfo)").list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getDesignData(String customerId){
		return sessionFactory.getCurrentSession().createQuery("FROM Design where customerId='"+customerId+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Regions> getRegion() {
		return sessionFactory.getCurrentSession().createQuery("select distinct region from Regions").list();
	}
	
	@Override
	public String getUserName(String username,String role){
		List list=sessionFactory.getCurrentSession().createQuery("select username from User where username='"+username+"'and type='"+role+"'").list();
		if(list.isEmpty())
			return "Not Exists";
		else
			return "Exists";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Regions> getCity(String selectedRegion) {
		return sessionFactory.getCurrentSession().createQuery("select distinct city from Regions where region='"+selectedRegion+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Regions> getManager(String region) {
		return sessionFactory.getCurrentSession().createQuery("select distinct username from User where type='Manager' and region='"+region+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Regions> getExchange(String selectedCity) {
		return sessionFactory.getCurrentSession().createQuery("select distinct exchangeName from Regions where city='"+selectedCity+"'").list();
	}
	
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistingRegion() {
		return sessionFactory.getCurrentSession().createQuery("select distinct region from Ticketing ").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistingCity(String selectedRegion) {
		return sessionFactory.getCurrentSession().createQuery("select distinct city from Ticketing where region='"+selectedRegion+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistingExchange(String selectedCity) {
		return sessionFactory.getCurrentSession().createQuery("select distinct exchangeName from Ticketing where city='"+selectedCity+"'").list();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistingFloor(String selectedExchange) {
		return sessionFactory.getCurrentSession().createQuery("select distinct floor from Ticketing where exchangeName='"+selectedExchange+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistingSuite(String exchange,String floor) {
		return sessionFactory.getCurrentSession().createQuery("select distinct suite from Ticketing where exchangeName='"+exchange+"' and floor='"+floor+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistingRack(String exchange,String floor,String suite) {
		return sessionFactory.getCurrentSession().createQuery("select distinct rack from Ticketing where exchangeName='"+exchange+"' and floor='"+floor+"' and suite='"+suite+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistingSubRack(String exchange,String floor,String suite,String rack) {
		return sessionFactory.getCurrentSession().createQuery("select distinct subrack from Ticketing where exchangeName='"+exchange+"' and floor='"+floor+"' and suite='"+suite+"' and rack='"+rack+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getExistingCustomerId(String exchange,String floor,String suite,String rack,String subRack) {
		//return sessionFactory.getCurrentSession().createQuery("select distinct customerId from Design where exchangeName='"+exchange+"' and floor='"+floor+"' and suite='"+suite+"' and rack='"+rack+"' and subrack='"+subRack+"'").list();
		
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Ticketing.class);
        c.add(Restrictions.eq("exchangeName",exchange));
        c.add(Restrictions.eq("floor",floor));
        c.add(Restrictions.eq("suite",suite));
        c.add(Restrictions.eq("rack",rack));
        c.add(Restrictions.eq("subrack",subRack));
       // return (List<Design>) c.list();
         
        List <Ticketing> old_object = (List<Ticketing>) c.list();
        ArrayList<String> customer_id =new ArrayList<String>();
		if(old_object!=null)
		{
			for(Ticketing data:old_object)
			{
				customer_id.add(data.getCustomer().getCustomerId());
			}
		}
        return customer_id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExistUniqueId(String selectedExchange,String selectedCustomerId) {
		List<Ticketing> uniqueId=sessionFactory.getCurrentSession().createQuery("select distinct uniqueId from Ticketing where exchangeName='"+selectedExchange+"' and customer='"+selectedCustomerId+"' and status='Closed' ").list();
		List existUniqueId=sessionFactory.getCurrentSession().createQuery("select distinct taguniqueid from Taginformation where customerid='"+selectedCustomerId+"'").list();
		List ticket=sessionFactory.getCurrentSession().createQuery("select count(*) from Ticketing where exchangeName='"+selectedExchange+"' and customer='"+selectedCustomerId+"' and (status='Open' or status='InProgress')").list();
		List emptyList=new ArrayList();
		List list=new ArrayList();
		list.add(0);
		if(!(ticket.equals(list)))
		{	
			if(existUniqueId.isEmpty())
			{
				return uniqueId;
			}
			else
			{
				return existUniqueId;
			}
		}
		else
		{
			return emptyList;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String TestUniqueIdExist(String uniqueId){
		List<Ticketing> list=sessionFactory.getCurrentSession().createQuery("select distinct uniqueId from Ticketing where uniqueId='"+uniqueId+"'and ticketType='New'").list();
		if(list.isEmpty())
		{
			return "Not Exists";
		}
		else
		{
			return "Exists";
		}
		
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> TestCustIdExist(String custId){
		List<Ticketing> list=sessionFactory.getCurrentSession().createQuery("select distinct customer from Ticketing where customer='"+custId+"'and ticketType='New'").list();
		if(list.isEmpty())
		{
			return "Not Exists";
		}
		else
		{
			return "Exists";
		}
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public String TestCustIdExist(String custId) {
		
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Ticketing.class);
		Customer cust=new Customer();
		cust.setCustomerId(custId);
        c.add(Restrictions.eq("customer",cust));
      
         
        List <Ticketing> old_object = (List<Ticketing>) c.list();
        ArrayList<String> customer_id =new ArrayList<String>();
		if(old_object!=null)
		{
			for(Ticketing data:old_object)
			{
				customer_id.add(data.getCustomer().getCustomerId());
			}
		}
		
		if(customer_id.isEmpty())
		{
			return "Not Exists";
		}
		else
		{
			return "Exists";
		}
        
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getStartnEndPoint(String selectedExchange,String selectedCustomerId) {
		return sessionFactory.getCurrentSession().createQuery(" from Design where exchangeName='"+selectedExchange+"' and customerId='"+selectedCustomerId+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getFloor(String exchange) {
		return sessionFactory.getCurrentSession().createQuery("select distinct floor from Design where exchangeName='"+exchange+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getSuite(String exchange,String floor) {
		return sessionFactory.getCurrentSession().createQuery("select distinct suite from Design where exchangeName='"+exchange+"' and floor='"+floor+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getRack(String exchange,String floor,String suite) {
		return sessionFactory.getCurrentSession().createQuery("select distinct rack from Design where exchangeName='"+exchange+"' and floor='"+floor+"' and suite='"+suite+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getSubRack(String exchange,String floor,String suite,String rack) {
		return sessionFactory.getCurrentSession().createQuery("select distinct subrack from Design where exchangeName='"+exchange+"' and floor='"+floor+"' and suite='"+suite+"' and rack='"+rack+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getCustomerId(String exchange,String floor,String suite,String rack,String subRack) {
		//return sessionFactory.getCurrentSession().createQuery("select distinct customerId from Design where exchangeName='"+exchange+"' and floor='"+floor+"' and suite='"+suite+"' and rack='"+rack+"' and subrack='"+subRack+"'").list();
		
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Design.class);
        c.add(Restrictions.eq("exchangeName",exchange));
        c.add(Restrictions.eq("floor",floor));
        c.add(Restrictions.eq("suite",suite));
        c.add(Restrictions.eq("rack",rack));
        c.add(Restrictions.eq("subrack",subRack));
       // return (List<Design>) c.list();
         
        List <Design> old_object = (List<Design>) c.list();
        ArrayList<String> customer_id =new ArrayList<String>();
		if(old_object!=null)
		{
			for(Design data:old_object)
			{
				customer_id.add(data.getCustomerId().getCustomerId());
				
				//temp=data.getOrgStock();
			}
		}
        return customer_id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> fetchInventoryDetailsbyCustomer(String cid) {
		// TODO Auto-generated method stub
		 		return sessionFactory.getCurrentSession().createQuery(" From Inventory where customerId='"+cid+"'").list();
	}
	
	@SuppressWarnings("unchecked")
    @Override
    public List<Customer> getCustomerDetails(String custid) {
           return sessionFactory.getCurrentSession().createQuery("from Customer where Customer_Id='"+custid+"'").list();
    }

	@Override
	public void saveCustomer(String custId,String customerJson) {
		JSONObject custObj = (JSONObject)JSONSerializer.toJSON(customerJson);
		 
		  Query q = sessionFactory.getCurrentSession().createQuery("from Customer where customerId ='"+custId+"'");
		   Customer customer = (Customer)q.list().get(0);

			 customer.setAddress(custObj.getString("address"));
			 customer.setCity(custObj.getString("city"));
			 customer.setCustomerName(custObj.getString("custName"));
			 customer.setCustomerStatus(custObj.getString("custStatus"));
			 customer.setFax(custObj.getString("fax"));
			 customer.setPartial(custObj.getString("partial"));
			 customer.setPostalCode(custObj.getString("postCode"));
			 customer.setPhoneNum(Long.parseLong(custObj.getString("phone")));
			 customer.setSurname(custObj.getString("surname"));
			 customer.setUserId(custObj.getString("userId"));
		  
		   sessionFactory.getCurrentSession().update(customer);
		
	}

	@Override
	public void saveInventory(String custId,String inventoryJson) {
		System.out.println("INVJSON "+inventoryJson);
		 JSONObject inventoryObj = (JSONObject)JSONSerializer.toJSON(inventoryJson);
		 System.out.println("INVOBJ "+inventoryObj);
		 Query q = sessionFactory.getCurrentSession().createQuery("from Inventory where customerId ='"+custId+"'");
		 Inventory inventory = (Inventory)q.list().get(0);

		 inventory.setEquipmentId(inventoryObj.getString("equipId"));
		 inventory.setExchangeName(inventoryObj.getString("exchangenameId"));
		 inventory.setShelfName(inventoryObj.getString("shelfName"));
		 inventory.setLength(inventoryObj.getString("length"));
		 inventory.setInventoryStatusCode(inventoryObj.getString("InvStatus"));
		 inventory.setOwnershipTypeCode(inventoryObj.getString("ownerType"));
		 inventory.setSiteName(inventoryObj.getString("siteName"));
		 inventory.setInstalledDate(Date.valueOf(inventoryObj.getString("installedDate")));
		 inventory.setOperationalState(inventoryObj.getString("operState"));
		
		 sessionFactory.getCurrentSession().update(inventory);
	}

	@Override
	public void saveDesign(String custId,String designJson) {
		System.out.println("DESJSON "+designJson);
		JSONObject designObj = (JSONObject)JSONSerializer.toJSON(designJson);
		
		 System.out.println("DESOBJ "+designObj);
		
		 Query q = sessionFactory.getCurrentSession().createQuery("from Design where customerId ='"+custId+"'");
		 Design design = (Design)q.list().get(0);
		 
		 design.setTagId(Integer.parseInt(designObj.getString("tagId")));
		 design.setExchangeName(designObj.getString("exchangename"));
		 design.setShelfName((designObj.getString("shelfName")));
		 design.setStartPoint(designObj.getString("startPoint"));
		 design.setEndPoint(designObj.getString("endpoint"));
		 design.setLength(designObj.getString("length"));
		 design.setAddress(designObj.getString("address"));
		 design.setFloor(designObj.getString("floor"));
		 design.setSuite(designObj.getString("suite"));
		 design.setVertIn(designObj.getString("vertIn"));
		
		 sessionFactory.getCurrentSession().update(design);
	}
	

	@Override
	public String fetchtUniqueId(String Customerid,String ticketId) {
		List result= sessionFactory.getCurrentSession().createQuery("select uniqueId from ExecutiveTicketInfo where customer='"+Customerid+"' and ticketNum='"+ticketId+"' ").list();
		String uniqueid=result.toString();
		return uniqueid;
	}

	public String saveTaginfo(Taginformation taginformation) {	
		String flag="false";
		try{
			 sessionFactory.getCurrentSession().saveOrUpdate(taginformation);
			 flag="true";

		}catch(Exception e){
			System.out.println(" taginfo>>>>>>>>>>>"+flag+e);
			return flag+e;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getManagerId(String managerName) {
		List<User> list=sessionFactory.getCurrentSession().createQuery("select distinct emailId from User where username='"+managerName+"'").list();
		return list.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getManagerDetails(String managerId){
		return sessionFactory.getCurrentSession().createQuery("from User where username='"+managerId+"'").list();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveTicketInfo> managerTotalTickets(String username) {
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where manager='"+username+"'").list();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveTicketInfo> managerRejectedTickets(String username){
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where manager='"+username+"' and status='Not Accepted'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveTicketInfo> managerTotalTicketsCount(String username) {			
		return sessionFactory.getCurrentSession().createQuery("select count(*) from ExecutiveTicketInfo where manager='"+username+"'").list();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveTicketInfo> managerRejectedTicketsCount(String username){
		return sessionFactory.getCurrentSession().createQuery("select count(*) from ExecutiveTicketInfo where manager='"+username+"' and status='Not Accepted'").list(); 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Executive> getManagerTechnicians(String username) {
		return sessionFactory.getCurrentSession().createQuery("from Executive where manager='"+username+"'").list();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveTicketInfo> assignedTicketsCountData() {
		return sessionFactory.getCurrentSession().createQuery("select count(*) FROM ExecutiveTicketInfo where status='InProgress' or status='Accepted'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getAllExchanges() {
		return sessionFactory.getCurrentSession().createQuery("select distinct exchangeName from Inventory").list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getDesignExchanges() {
		return sessionFactory.getCurrentSession().createQuery("select distinct exchangeName from Design").list();
	}
	
	public String ASCIItoHEX(String ascii) 
    { 
        // Initialize final String 
        String hex = ""; 
  
        // Make a loop to iterate through 
        // every character of ascii string 
        for (int i = 0; i < ascii.length(); i++) { 
  
            // take a char from 
            // position i of string 
            char ch = ascii.charAt(i); 
  
            // cast char to integer and 
            // find its ascii value 
            int in = (int)ch; 
  
            // change this ascii value 
            // integer to hexadecimal value 
            String part = Integer.toHexString(in); 
  
            // add this hexadecimal value 
            // to final string. 
            hex += part; 
        } 
        // return the final string hex 
        return hex; 
    } 

	@Override
	public String updateTicketStatus(String ticketId) {
		System.out.println("TICKET "+ticketId);
		
		
		 Query q = sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where ticketNum ='"+ticketId+"'");
		 ExecutiveTicketInfo executiveTicket = (ExecutiveTicketInfo)q.list().get(0);
		 
		 executiveTicket.setStatus("Closed");
	
		
		 sessionFactory.getCurrentSession().update(executiveTicket);
		 
		 Query q1 = sessionFactory.getCurrentSession().createQuery("from Ticketing where ticketNum ='"+ticketId+"'");
		 Ticketing ticketing = (Ticketing)q1.list().get(0);
		 
		 ticketing.setStatus("Closed");
		 
		 Calendar cal = Calendar.getInstance();
		ticketing.setClosedDate(cal.getTime());
		ticketing.setClosedTime(cal.getTime());
	
		
		 sessionFactory.getCurrentSession().update(ticketing);
		 
		return "Updated";
	}
	
	@Override
	public String getExistRegion(String region)
	{
		
		List<User> user=sessionFactory.getCurrentSession().createQuery("SELECT username FROM User where type='Manager' and region='"+region+"'").list();
		
		if(user.isEmpty())
		{
			return "Not Exists";
		}
		else
		{
			return "Exists";
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Design> getDesignShelf(String exchangeName) {
		return sessionFactory.getCurrentSession().createQuery("select distinct shelfName from Design where exchangeName='"+exchangeName+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getInvShelf(String exchangeName) {
		return sessionFactory.getCurrentSession().createQuery("select distinct shelfName from Inventory where exchangeName='"+exchangeName+"'").list();
	}
	
	@Override
	 public String updateTicketingStatus(String ticketId){
		 Query q1 = sessionFactory.getCurrentSession().createQuery("from Ticketing where ticketNum ='"+ticketId+"'");
		 Ticketing ticketing = (Ticketing)q1.list().get(0);
		 
		 ticketing.setStatus("InProgress");
	
		 sessionFactory.getCurrentSession().update(ticketing);
		
		return "Assigned";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getRoles(String userName) {
		return sessionFactory.getCurrentSession().createQuery("select type from User where username='"+userName+"' ").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Taginformation> fetchTaginfoByCid(String Pre_cid) {
		
		
		return 	sessionFactory.getCurrentSession().createQuery("from Taginformation where  customerid='"+Pre_cid+"' ").list();
		
		/*String flag="false";
		
		try{
			sessionFactory.getCurrentSession().createQuery("from Taginformation where  customerid='"+Pre_cid+"' ").list();
			
			Query q = sessionFactory.getCurrentSession().createQuery("from Taginformation where  customerid='"+Pre_cid+"' ");
			  Taginformation taginfo=(Taginformation)q.list().get(0);
			  System.out.println ("taginfo>>>>>>>>"+taginfo.getId());
			  Taginformation_history taginfohistory= new Taginformation_history();
			  
			  
			  taginfohistory.setTicketid(taginfo.getTicketid());
			  taginfohistory.setCustomerid(taginfo.getCustomerid());
			  taginfohistory.setStartpoint(taginfo.getStartpoint());
			  taginfohistory.setTaguniqueid(taginfo.getTaguniqueid());
			  taginfohistory.setFileName(taginfo.getFileName());
			  taginfohistory.setFiledata(taginfo.getFiledata());
			  taginfohistory.setCusjon(taginfo.getCusjon());
			  taginfohistory.setDesJson(taginfo.getDesJson());
			  taginfohistory.setInvjson(taginfo.getInvjson());
			  
			 sessionFactory.getCurrentSession().saveOrUpdate(taginfohistory);

			 
			flag="true";
			 
		}catch(Exception e){
			flag="exeption in tag_hist"+e;
			System.out.println(flag);

			return flag;
			
		}
		return flag*/
	}

	@Override
	public String saveTag_hist(Taginformation_history taghist) {
		// TODO Auto-generated method stub
		
		String flag="false";
		try{
			 sessionFactory.getCurrentSession().saveOrUpdate(taghist);
			 flag="true";

		}catch(Exception e){
			System.out.println(" taghist>>>>>>>>>>>"+flag+e);
			return flag+e;
		}
		return flag;
	}

	@Override
	public String delete_taginfo(Taginformation taghist,String cid) {
		// TODO Auto-generated method stub
		String flag1="false";
		try{
		
			 Taginformation taginfo = (Taginformation) sessionFactory.getCurrentSession().load(Taginformation.class, taghist.getId());
        if (null != taginfo) {
              this.sessionFactory.getCurrentSession().delete(taginfo);
        }
		//sessionFactory.getCurrentSession().delete(cid, taghist);
			 flag1="true";

		}catch(Exception e){
			System.out.println(" taghist>>>>>>>>>>>"+flag1+e);
			return flag1+e;
		}
		return flag1;
	}

	@Override
	public String save_newTaginfo(Taginformation taginfo) {
		// TODO Auto-generated method stub
		String flag2="false";
		try{
			 sessionFactory.getCurrentSession().saveOrUpdate(taginfo);
			 flag2="true";

		}catch(Exception e){
			System.out.println(" taghist>>>>>>>>>>>"+flag2+e);
			return flag2+e;
		}
		return flag2;
	}

	@Override
	public String saveExistingTaginfo(Taginformation taginformation, String previous_cid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List fetchTaginfo(String cid, String tid) {
		// TODO Auto-generated method stub
		List taginfo= 	sessionFactory.getCurrentSession().createQuery("from Taginformation where  customerid='"+cid+"' and ticketid='"+tid+"' ").list();
			if(!taginfo.isEmpty()){
				return taginfo;
			}
			else{
				return sessionFactory.getCurrentSession().createQuery("from Taginformation_history where  customerid='"+cid+"' and ticketid='"+tid+"' ").list();
			}
	}
	
	@Override
	public String saveTechStatus(String ticketId, String techStatus,String execId,String commentsData,String remarksData) {
		 Query q1 = sessionFactory.getCurrentSession().createQuery("from Ticketing where ticketNum ='"+ticketId+"'");
		 Ticketing ticketing = (Ticketing)q1.list().get(0);
		 
		 ticketing.setStatus(techStatus);
		 ticketing.setComments(commentsData);
		 ticketing.setRemarks(remarksData);
	
		 sessionFactory.getCurrentSession().update(ticketing);
		 
		 Query q2 = sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where ticketNum ='"+ticketId+"' and executiveId='"+execId+"'");
		 ExecutiveTicketInfo executiveTicketInfo = (ExecutiveTicketInfo)q2.list().get(0);
		 
		 executiveTicketInfo.setStatus(techStatus);
		 executiveTicketInfo.setComments(commentsData);
		 executiveTicketInfo.setRemarks(remarksData);
	
		 sessionFactory.getCurrentSession().update(executiveTicketInfo);
		
		return techStatus;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExecAcceptedTicketsCountData(String username) {	
		//System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("select count(*) from ExecutiveTicketInfo where Status='Accepted' and executiveId='"+username+"'").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticketing> getExecAcceptedTicketsData(String username) {	
		//System.out.println("user"+username);
		return sessionFactory.getCurrentSession().createQuery("from ExecutiveTicketInfo where Status='Accepted' and executiveId='"+username+"'").list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutiveTicketInfo> getUnassignedUnRejectedExecutives(String ticketId){
		return sessionFactory.getCurrentSession().createQuery("FROM ExecutiveTicketInfo where ticketNum='"+ticketId+"'").list();		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Executive> getUnassignedUnRejectedExecutivesData(String region,String city,String execId){
		return sessionFactory.getCurrentSession().createQuery("FROM Executive where region='"+region+"' and city='"+city+"'and executiveId NOT In("+execId+")").list();
	}	
	
}