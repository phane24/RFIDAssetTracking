package com.cyient.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cyient.dao.RFIDDAO;
import com.cyient.model.Design;
import com.cyient.model.Executive;
import com.cyient.model.ExecutiveTicketInfo;
import com.cyient.model.Inventory;
import com.cyient.model.Regions;
import com.cyient.model.Ticketing;
import com.cyient.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONArray;

@Controller
public class HomeController {
	private static final Logger logger = Logger
			.getLogger(RFIDAssetController.class);


	@Autowired
	private RFIDDAO rfidDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private String selectedExecutiveId,selectedTicketNum;
		
	public HomeController()
	{
		System.out.println("homeController()");
	}
	
	@RequestMapping(value = "/openTickets")
	public ModelAndView openTickets(ModelAndView model) throws IOException {
		model.setViewName("openTickets");
		return model;
	}

	@RequestMapping(value = "/closedTickets")
	public ModelAndView closedTickets(ModelAndView model) throws IOException {
		model.setViewName("closedTickets");
		return model;
	}
	
	@RequestMapping(value = "/assignedTickets")
	public ModelAndView assignedTickets(ModelAndView model) throws IOException {
		model.setViewName("assignedTickets");
		return model;
	}
	
	@RequestMapping(value = "/unAssignedTickets")
	public ModelAndView unAssignedTickets(ModelAndView model) throws IOException {
		model.setViewName("unAssignedTickets");
		return model;
	}
	
	@RequestMapping(value = "/historyTickets")
	public ModelAndView historyTickets(ModelAndView model) throws IOException {
		model.setViewName("historyTickets");
		return model;
	}
	
	@RequestMapping(value = "/superAdminTotalTickets")
	public ModelAndView superAdminTotalTickets(ModelAndView model) throws IOException {
		model.setViewName("superAdminTotalTickets");
		return model;
	}
	
	@RequestMapping(value = "/totalTickets")
	public ModelAndView totalTickets(ModelAndView model) throws IOException {
		model.setViewName("totalTickets");
		return model;
	}
	
	@RequestMapping(value = "/adminOpenTickets")
	public ModelAndView adminOpenTickets(ModelAndView model) throws IOException {
		model.setViewName("adminOpenTickets");
		return model;
	}
	
	@RequestMapping("getOpenTickets")
    @ResponseBody
    public String  getOpenTicketsData(ModelAndView model) {
		List<Ticketing> listOpen = rfidDAO.getOpenTicketsData();	
        	   Gson gsonBuilder = new GsonBuilder().create();
        	   String openJson = gsonBuilder.toJson(listOpen);
	              return openJson.toString();
    }
	
	@RequestMapping("adminTicketsCount")
    @ResponseBody
    public String  adminTicketsCountData(ModelAndView model) {
		List listData=new ArrayList();
		List<Ticketing> listOpen = rfidDAO.adminOpenTicketsCountData();		              
	    //List<Ticketing> listOpen1 = rfidDAO.closedTicketsCountData();
		// List<Ticketing> listOpen2 = rfidDAO.historyTicketsCountData();	
		List<ExecutiveTicketInfo> listOpen1=rfidDAO.assignedTicketsCountData();
		// List<Ticketing> listOpen2 = rfidDAO.getUnassignedOpenTicketsCountData();	
		List<Ticketing> listOpen2 = rfidDAO.historyTicketsCountData();
		List<Ticketing> listOpen3 = rfidDAO.getAllTicketsCountData();
		   listData.add(listOpen);
		   listData.add(listOpen1);
		   listData.add(listOpen2);
		   listData.add(listOpen3);
		   System.out.println(listData);
		   Gson gsonBuilder = new GsonBuilder().create();
		   String openJson = gsonBuilder.toJson(listData);
	          return openJson.toString();
    }
	
	@RequestMapping("ticketsCount")
    @ResponseBody
    public String  ticketsCountData(ModelAndView model) {
		List listData=new ArrayList();
		List<Ticketing> listOpen = rfidDAO.openTicketsCountData();		              
	    //List<Ticketing> listOpen1 = rfidDAO.closedTicketsCountData();
	      List<Ticketing> listOpen1 = rfidDAO.historyTicketsCountData();
	      List<Ticketing> listOpen2 =rfidDAO.getAllTicketsCountData();
 		   listData.add(listOpen);
		   listData.add(listOpen1);
		   listData.add(listOpen2);
		   System.out.println(listData);
		   Gson gsonBuilder = new GsonBuilder().create();
		   String openJson = gsonBuilder.toJson(listData);
	          return openJson.toString();
    }
	
	@RequestMapping(value="getExecTicketsCount", method = RequestMethod.GET)
    @ResponseBody
    public String  execTicketsCount(ModelAndView model,HttpServletRequest request) {
		String username=request.getParameter("username");
		List listData=new ArrayList();
		List<Ticketing> listOpen = rfidDAO.getExecAssignedTicketsCountData(username);	
		List<Ticketing> listOpen2 = rfidDAO.getExecAcceptedTicketsCountData(username);	
	    List<Ticketing> listOpen1 = rfidDAO.getExecClosedTicketsCountData(username);			 
		   listData.add(listOpen);
		   listData.add(listOpen2);
		   listData.add(listOpen1);
		   System.out.println(listData);
		   Gson gsonBuilder = new GsonBuilder().create();
		   String openJson = gsonBuilder.toJson(listData);
	          return openJson.toString();
    }
	
	@RequestMapping("getClosedTickets")
    @ResponseBody
    public String  getClosedTicketsData(ModelAndView model) {
		List<Ticketing> listClosed = rfidDAO.getClosedTicketsData();
	        	   Gson gsonBuilder = new GsonBuilder().create();
        	   String closedJson = gsonBuilder.toJson(listClosed);
	              return closedJson.toString();
    }
	
	@RequestMapping("getAssignedTickets")
    @ResponseBody
    public String  getAssignedTicketsData(ModelAndView model) {
		List<ExecutiveTicketInfo> listAssigned = rfidDAO.getAssignedTicketsData();
	        	   Gson gsonBuilder = new GsonBuilder().create();
        	   String closedJson = gsonBuilder.toJson(listAssigned);
	              return closedJson.toString();
    }
	
	@RequestMapping("getHistoryTickets")
    @ResponseBody
    public String  getHistoryTicketsData(ModelAndView model) {
		List<Ticketing> listHistory = rfidDAO.getHistoryTicketsData();
		  	   Gson gsonBuilder = new GsonBuilder().create();
        	   String historyJson = gsonBuilder.toJson(listHistory);
	              return historyJson.toString();
    }
	
	/*@RequestMapping(value = "/saveExecutive", method = RequestMethod.POST)
	public ModelAndView saveExecutive(@ModelAttribute Executive executive) {
//		if (executive.getId() == 0) { 
		 
			rfidDAO.addExecutive(executive);
//		} 
			return new ModelAndView("redirect:/home");
		
	}*/
	
	/* @RequestMapping("getManagerDetails")
	    @ResponseBody
	    public String  getManagerDetails(ModelAndView model) {
			List<User> listManagers = rfidDAO.getManagerDetails();
			System.out.println(listManagers);
		   Gson gsonBuilder = new GsonBuilder().create();
		   String executivesJson = gsonBuilder.toJson(listManagers);
	          return executivesJson.toString();
	    }*/
	
	
	@RequestMapping(value = "/saveExecutive", method = RequestMethod.POST)
	public ModelAndView saveExecutive(@ModelAttribute final Executive executive,RedirectAttributes redirectAttributes) throws MessagingException {
		String status="Technician Added Sucessfully";
		final JSONArray json=new JSONArray();
			String managerId=null;
			User user=new User();
			user.setUsername(executive.getExecutiveId());
			user.setName(executive.getExecutiveName());
			user.setEmailId(executive.getEmailId());
			user.setMobileNumber(executive.getMobile());
			user.setPassword(executive.getPassword());
			user.setRegion(executive.getRegion());
			user.setCreatedDate(executive.getCreatedDate());
			user.setType("FeildExecutive");
    	   rfidDAO.addExecutive(executive);
    	   rfidDAO.addExecutiveIntoUsers(user);
		   managerId=rfidDAO.getManagerId(executive.getManager());
		   final String managerName=executive.getManager();
		   final String managerEmailId=managerId.substring(1, managerId.length()-1);
		   System.out.println("mail::::"+mailSender);
        	List<User> ManagerDetails=rfidDAO.getManagerDetails(executive.getManager());
		   
			for(User det:ManagerDetails)
			{
				json.add(det.getName());
				json.add(det.getUsername());
				json.add(det.getPassword());
			}

		      mailSender.send(new MimeMessagePreparator() {
		    	  public void prepare(MimeMessage mimeMessage) throws MessagingException {		    		
		    	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		    	    message.setFrom("Neeraja.Chaparala@cyient.com");
		    	    message.setTo(managerEmailId);
		    	    message.setSubject("Acceptance of Feild Technician Creation");	    	 
		    	  // message.setText("Dear <b>" + managerName +"</b> ,<br><br> Ticket with Id <b>" +selectedTicketNum+" </b> is assigned. Tikcet Details are:<br> Severity: <b>"+severity+ "</b> <br>Ticket Description: <b>" + ticketDesc+"</b><br>Customer ID <b>"+customerId+"</b> . <br> Please <a href='http://172.16.53.79:8080/RFIDAssetTracking/'>login</a> for other details", true);
		    	    message.setText("Dear <b>" + json.get(0) +"</b> ,<br><br> A new Technician created under your region with details:<br><b>Tehnician Id: </b>"+executive.getExecutiveId()+"<br><b>Technician Name: </b>"+executive.getExecutiveName()+"<br><b>Region: </b> "+executive.getRegion()+"<br><br>Please <a href='http://ctoceu.cyient.com:3290/RFIDAssetTracking/'>login</a> for other details with credetials:<br> <b>Username</b>: "+json.get(1)+"<br><b>Password</b>:"+json.get(2)+"", true);
		    	  }
		    	});
		      redirectAttributes.addFlashAttribute("status", status);
				return new ModelAndView("redirect:/newExecutive");
	}
	                                                                                                                                                                                                                                                                                   
   @RequestMapping(value="getUnassignedExecutives", method = RequestMethod.GET)
    @ResponseBody
    public String  getExecutivesData(ModelAndView model,HttpServletRequest request) {
    	 String region=request.getParameter("region");
    	 String city=request.getParameter("city");
    	 System.out.println("city :::"+city);
		List<Executive> listExecutives = rfidDAO.getUnassignedExecutivesData(region,city);
		System.out.println(listExecutives);
	   Gson gsonBuilder = new GsonBuilder().create();
	   String executivesJson = gsonBuilder.toJson(listExecutives);
          return executivesJson.toString();
    }
    
    @RequestMapping(value="/assignExecutive", method = RequestMethod.GET)
    @ResponseBody
	public String assignExecutive(HttpServletRequest request) throws MessagingException {	
    	
    	 selectedExecutiveId=request.getParameter("executiveId");
    	 
    	 Executive ExecutivesData = rfidDAO.getExecutivesData(selectedExecutiveId);
    	 System.out.println("executives: "+ExecutivesData);
    	 String ticketId = null;
    	 selectedTicketNum=request.getParameter("ticketId");
    	 
    	 List<Ticketing> ticketData = rfidDAO.getTicketsData(selectedTicketNum);
    	 System.out.println("Tickets: "+ticketData);
    	 ExecutiveTicketInfo executiveTicket=new  ExecutiveTicketInfo();
    	 
    	 executiveTicket.setExecutiveId(ExecutivesData.getExecutiveId());
    	 executiveTicket.setExecutiveName(ExecutivesData.getExecutiveName());
    	 executiveTicket.setRegion(ExecutivesData.getRegion());
    	 executiveTicket.setManager(ExecutivesData.getManager());
    	 executiveTicket.setCity(ExecutivesData.getCity());
    	 executiveTicket.setStatus("InProgress");  
    	
    	 
    	 for(Ticketing ticket : ticketData)
	      {
    		 ticketId=ticket.getTicketNum();
    		 //Customer custId = ticket.getCustomer().getCustomerId();
    		 executiveTicket.setCustomer(ticket.getCustomer());
    		 executiveTicket.setUniqueId(rfidDAO.ASCIItoHEX(ticket.getUniqueId()));
        	 executiveTicket.setTicketNum(ticket.getTicketNum());
        	 executiveTicket.setTicketDescription(ticket.getTicketDescription());        	   	 
        	 executiveTicket.setSeverity(ticket.getSeverity());
        	 executiveTicket.setTicketType(ticket.getTicketType());
        	 
	      }
    	
    	 if (executiveTicket.getCustomer() != null) { 
			String status=rfidDAO.assignExecutive(executiveTicket);
			String StatusUpdate=rfidDAO.updateTicketingStatus(ticketId);
			if(status.equalsIgnoreCase("Assigned")&&StatusUpdate.equalsIgnoreCase("Assigned"))
			{
				sendEmail();
			}
    	 }
    	
    	return "Assigned";		
	}
    
    
	@RequestMapping(value = "/technicianAssignedTickets")
	public ModelAndView executiveAssignedTickets(ModelAndView model) throws IOException {
		model.setViewName("executiveOpenTickets");
		return model;
	}

	@RequestMapping(value = "/technicianClosedTickets")
	public ModelAndView executiveClosedTickets(ModelAndView model) throws IOException {
		model.setViewName("executiveClosedTickets");
		return model;
	}
	
	@RequestMapping(value="getExecutiveAssignedTickets", method = RequestMethod.GET)
    @ResponseBody
    public String  getExceutiveOpenTicketsData(HttpServletRequest request) {
		String username=request.getParameter("username");
		System.out.println("username:"+username);
		List<Ticketing> listExecOpen = rfidDAO.getExecAssignedTicketsData(username);		
        	   Gson gsonBuilder = new GsonBuilder().create();
        	   String execOpenJson = gsonBuilder.toJson(listExecOpen);
        	   System.out.println(execOpenJson);
	              return execOpenJson.toString();
    }
	
	@RequestMapping(value="getExecutiveClosedTickets", method = RequestMethod.GET)
    @ResponseBody
    public String  getExceutiveClosedTicketsData(HttpServletRequest request) {
		String username=request.getParameter("username");
		System.out.println("username:"+username);
		List<Ticketing> listExecClosed = rfidDAO.getExecClosedTicketsData(username);		
        	   Gson gsonBuilder = new GsonBuilder().create();
        	   String execClosedJson = gsonBuilder.toJson(listExecClosed);
	              return execClosedJson.toString();
    }
	
	@RequestMapping("getAllTickets")
    @ResponseBody
    public String  getAllTicketsData(ModelAndView model) {
		List<Ticketing> listTickets = rfidDAO.getAllTicketsData();		
        	   Gson gsonBuilder = new GsonBuilder().create();
        	   String execClosedJson = gsonBuilder.toJson(listTickets);
	              return execClosedJson.toString();
    }
    
    @RequestMapping("getUnassignedOpenTickets")
    @ResponseBody
    public String  getUnassignedOpenTicketsData(ModelAndView model) {
		List<Ticketing> listTickets = rfidDAO.getUnassignedOpenTicketsData();
		System.out.println(listTickets);
	   Gson gsonBuilder = new GsonBuilder().create();
	   String ticketsJson = gsonBuilder.toJson(listTickets);
          return ticketsJson.toString();
    }
    
    
    @RequestMapping(value = "/sendEmail")
	public String sendEmail() throws MessagingException {
	     
      mailSender.send(new MimeMessagePreparator() {
    	  public void prepare(MimeMessage mimeMessage) throws MessagingException {
    		  
    		  String severity=null,ticketType=null;
			String customerId=null;
    		  
    		  Executive executiveData = rfidDAO.getExecutivesData(selectedExecutiveId);
    		  
    		  List<Ticketing> ticketData = rfidDAO.getTicketsData(selectedTicketNum);
    		  
    		  for(Ticketing ticket : ticketData)
    	      {
        		 customerId=ticket.getCustomer().getCustomerId();
            	 ticketType=ticket.getTicketType();           	 
            	 severity=ticket.getSeverity();
    	      }
    	      
    	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    	    message.setTo(executiveData.getEmailId());
    	    message.setSubject("Ticket Information");	    	 
    	    message.setText("Dear <b>" + executiveData.getExecutiveName() +"</b> ,<br><br> Ticket with Id <b>" +selectedTicketNum+" </b> is assigned. Ticket Details are:<br> Severity: <b>"+severity+ "</b> <br>Ticket Type: <b>" + ticketType+"</b><br>Customer ID <b>"+customerId+"</b> . <br><br> Please <a href='http://ctoceu.cyient.com:3290/RFIDAssetTracking/'>login</a> for other details", true);
    	    
    	  }
    	});
		
		return "Mail Sent";
	}
    
    
// @SuppressWarnings({ "rawtypes", "unchecked" })
//@RequestMapping(value="getExecutiveOpenTickets", method = RequestMethod.GET)
//  @ResponseBody
//   	public String getRestData(HttpServletRequest request){
//	    	
//		 List ticketslist=new ArrayList();
//		try {    		  
//    		String username=request.getParameter("username");
//            URL url = new URL("http://ctoceu.cyient.com:3290/RFIDRest/Ticketing/getTickets_exec/"+username);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP Error code : "
//                        + conn.getResponseCode());
//            }
//            InputStreamReader in = new InputStreamReader(conn.getInputStream());
//            BufferedReader br = new BufferedReader(in);
//         
//            String output;
//			while ((output = br.readLine()) != null) {            	
//				ticketslist.add(output);                
//            }                               
//            conn.disconnect();
//
//        } catch (Exception e) {
//            System.out.println("Exception in NetClientGet:- " + e);
//        }
//    	return ticketslist.toString();
//   	}
 
    @RequestMapping(value="getUserName",method = RequestMethod.GET)
    @ResponseBody
    public String getUserName(HttpServletRequest request) {
    	String username=request.getParameter("username");
    	String role=request.getParameter("role");
    	String user=rfidDAO.getUserName(username,role);
		return user;
    	
    }
    

	 @RequestMapping(value= "getRegion", method = RequestMethod.GET)
		@ResponseBody
		public String getRegion(HttpServletRequest request) {
			List<Regions> regions = rfidDAO.getRegion();
			Gson gsonBuilder = new GsonBuilder().create();
			String regionJSON = gsonBuilder.toJson(regions);
	 	   	return regionJSON;
		}
	 
	 
	 @RequestMapping(value= "getCity", method = RequestMethod.GET)
		@ResponseBody
		public String getCity(HttpServletRequest request) {
		 String selectedRegion=request.getParameter("selectedRegion");
			List<Regions> cities = rfidDAO.getCity(selectedRegion);
			Gson gsonBuilder = new GsonBuilder().create();
			String regionJSON = gsonBuilder.toJson(cities);
	 	   	return regionJSON;
		}
	 
	 @RequestMapping(value="getExistRegion", method=RequestMethod.GET)
	 @ResponseBody 
	 public String getExistRegion(HttpServletRequest request) {
		 String region=request.getParameter("region");
		 String user=rfidDAO.getExistRegion(region);
		return user;
		 
	 }	
		 @RequestMapping(value= "getManager", method = RequestMethod.GET)
		@ResponseBody
		public String getManager(HttpServletRequest request) {
		 String region=request.getParameter("selectedRegion");
			List<Regions> regions = rfidDAO.getManager(region);
			Gson gsonBuilder = new GsonBuilder().create();
			String regionJSON = gsonBuilder.toJson(regions);
	 	   	return regionJSON;
		}
	 
	 @RequestMapping(value= "getExchange", method = RequestMethod.GET)
		@ResponseBody
		public String getExchange(HttpServletRequest request) {
			String selectedCity = request.getParameter("selectedCity");
			List<Regions> exchangeList = rfidDAO.getExchange(selectedCity);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String exchangeJson = gsonBuilder.toJson(exchangeList);
	 	   	return exchangeJson;
		}
	 
	 @RequestMapping(value= "getExistingRegion", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingRegion(HttpServletRequest request) {
			List<Ticketing> regionList = rfidDAO.getExistingRegion();
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String exchangeJson = gsonBuilder.toJson(regionList);
	 	   	return exchangeJson;
		}
	 
	 @RequestMapping(value= "getExistingCity", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingCity(HttpServletRequest request) {
		 String selectedRegion=request.getParameter("selectedRegion");
			List<Ticketing> regionList = rfidDAO.getExistingCity(selectedRegion);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String exchangeJson = gsonBuilder.toJson(regionList);
	 	   	return exchangeJson;
		}
	 
	 @RequestMapping(value= "getExistingExchange", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingExchange(HttpServletRequest request) {
		 String selectedCity=request.getParameter("selectedCity");
			List<Ticketing> exchangeList = rfidDAO.getExistingExchange(selectedCity);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String exchangeJson = gsonBuilder.toJson(exchangeList);
	 	   	return exchangeJson;
		}
	 
	 @RequestMapping(value= "getExistingFloor", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingFloor(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			List<Ticketing> floorList = rfidDAO.getExistingFloor(selectedExchange);
			Gson gsonBuilder = new GsonBuilder().create();
	 	   	String floorJson = gsonBuilder.toJson(floorList);
	 	   	return floorJson;
		}
	 
	 @RequestMapping(value= "getExistingSuite", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingSuite(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			List<Ticketing> suiteList = rfidDAO.getExistingSuite(selectedExchange,selectedFloor);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String suiteJson = gsonBuilder.toJson(suiteList);
	 	   	return suiteJson;
		}
	 
	 @RequestMapping(value= "getExistingRack", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingRack(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			String selectedSuite = request.getParameter("selectedSuite");
			List<Ticketing> rackList = rfidDAO.getExistingRack(selectedExchange,selectedFloor,selectedSuite);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String rackJson = gsonBuilder.toJson(rackList);
	 	   	return rackJson;
		}
	 
	 @RequestMapping(value= "getExistingSubRack", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingSubRack(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			String selectedSuite = request.getParameter("selectedSuite");
			String selectedRack = request.getParameter("selectedRack");
			List<Ticketing> subRackList = rfidDAO.getExistingSubRack(selectedExchange,selectedFloor,selectedSuite,selectedRack);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String subRackJson = gsonBuilder.toJson(subRackList);
	 	   	return subRackJson;
		}
	 
	 @RequestMapping(value= "getExistingCustomerId", method = RequestMethod.GET)
		@ResponseBody
		public String getExistingCustomerId(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			String selectedSuite = request.getParameter("selectedSuite");
			String selectedRack = request.getParameter("selectedRack");
			String selectedSubRack=request.getParameter("selectedSubRack");
			ArrayList<String> subRackList = rfidDAO.getExistingCustomerId(selectedExchange,selectedFloor,selectedSuite,selectedRack,selectedSubRack);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String subRackJson = gsonBuilder.toJson(subRackList);
	 	   	return subRackJson;
		}
	 
	 @RequestMapping(value= "getExistUniqueId", method = RequestMethod.GET)
		@ResponseBody
		public String getExistUniqueId(HttpServletRequest request) {
		 String selectedExchange = request.getParameter("selectedExchange");
		 String selectedCustomerId=request.getParameter("selectedCustomerId");
		 //String ticketId=request.getParameter("ticketId");
			List<Ticketing> exchangeList = rfidDAO.getExistUniqueId(selectedExchange,selectedCustomerId);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String exchangeJson = gsonBuilder.toJson(exchangeList);
	 	   	return exchangeJson;
		}
	 
	 @RequestMapping(value= "getStartnEndPoint", method = RequestMethod.GET)
		@ResponseBody
		public String getStartnEndPoint(HttpServletRequest request) {
		 String selectedExchange = request.getParameter("selectedExchange");
		 String selectedCustomerId=request.getParameter("selectedCustomerId");
			List<Design> exchangeList = rfidDAO.getStartnEndPoint(selectedExchange,selectedCustomerId);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String exchangeJson = gsonBuilder.toJson(exchangeList);
	 	   	return exchangeJson;
		}
	 
	 @RequestMapping(value= "TestUniqueIdExist", method = RequestMethod.GET)
		@ResponseBody
		public String TestUniqueIdExist(HttpServletRequest request) {
		 String UniqueId = request.getParameter("UniqueId");
		String exchangeList = rfidDAO.TestUniqueIdExist(UniqueId);
		return exchangeList;
		}
	 
	 @RequestMapping(value= "TestCustIdExist", method = RequestMethod.GET)
		@ResponseBody
		public String TestCustIdExist(HttpServletRequest request) {
		 String CustId = request.getParameter("CustId");
		String exchangeList = rfidDAO.TestCustIdExist(CustId);
		return exchangeList;
		}
	 
	 @RequestMapping(value= "getFloor", method = RequestMethod.GET)
		@ResponseBody
		public String getFloor(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			List<Design> floorList = rfidDAO.getFloor(selectedExchange);
			Gson gsonBuilder = new GsonBuilder().create();
	 	   	String floorJson = gsonBuilder.toJson(floorList);
	 	   	return floorJson;
		}
	 
	 @RequestMapping(value= "getSuite", method = RequestMethod.GET)
		@ResponseBody
		public String getSuite(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			List<Design> suiteList = rfidDAO.getSuite(selectedExchange,selectedFloor);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String suiteJson = gsonBuilder.toJson(suiteList);
	 	   	return suiteJson;
		}
	 
	 @RequestMapping(value= "getRack", method = RequestMethod.GET)
		@ResponseBody
		public String getRack(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			String selectedSuite = request.getParameter("selectedSuite");
			List<Design> rackList = rfidDAO.getRack(selectedExchange,selectedFloor,selectedSuite);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String rackJson = gsonBuilder.toJson(rackList);
	 	   	return rackJson;
		}
	 
	 @RequestMapping(value= "getSubRack", method = RequestMethod.GET)
		@ResponseBody
		public String getSubRack(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			String selectedSuite = request.getParameter("selectedSuite");
			String selectedRack = request.getParameter("selectedRack");
			List<Design> subRackList = rfidDAO.getSubRack(selectedExchange,selectedFloor,selectedSuite,selectedRack);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String subRackJson = gsonBuilder.toJson(subRackList);
	 	   	return subRackJson;
		}
	 
	 @RequestMapping(value= "getCustomerId", method = RequestMethod.GET)
		@ResponseBody
		public String getCustomerId(HttpServletRequest request) {
			String selectedExchange = request.getParameter("selectedExchange");
			String selectedFloor = request.getParameter("selectedFloor");
			String selectedSuite = request.getParameter("selectedSuite");
			String selectedRack = request.getParameter("selectedRack");
			String selectedSubRack=request.getParameter("selectedSubRack");
			ArrayList<String> subRackList = rfidDAO.getCustomerId(selectedExchange,selectedFloor,selectedSuite,selectedRack,selectedSubRack);
		   	Gson gsonBuilder = new GsonBuilder().create();
	 	   	String subRackJson = gsonBuilder.toJson(subRackList);
	 	   	return subRackJson;
		}
	  
	 
	 @RequestMapping(value = "/managerTotalTickets")
		public ModelAndView managerTotalTickets(ModelAndView model) throws IOException {
			model.setViewName("managerTotalTickets");
			return model;
		}
		
	 @RequestMapping(value = "/managerRejectedTickets")
		public ModelAndView managerRejectedTickets(ModelAndView model) throws IOException {
			model.setViewName("managerRejectedTickets");
			return model;
		}
	 
		@RequestMapping(value = "/techniciansList")
		public ModelAndView techniciansList(ModelAndView model) throws IOException {
			model.setViewName("techniciansList");
			return model;
		}
		
		 @RequestMapping(value="getManagerTechnicians", method = RequestMethod.GET)
		    @ResponseBody
		    public String getManagerTechnicians(ModelAndView model,HttpServletRequest request) {
		    	String username=request.getParameter("username");
				List<Executive> listClosed = rfidDAO.getManagerTechnicians(username);
			        	   Gson gsonBuilder = new GsonBuilder().create();
		        	   String closedJson = gsonBuilder.toJson(listClosed);
			              return closedJson.toString();
		    }


		@RequestMapping(value="managerTicketsCount", method = RequestMethod.GET)
	    @ResponseBody
	    public String managerTicketsCount(ModelAndView model,HttpServletRequest request) {
			String username=request.getParameter("username");
			List listData=new ArrayList();
			List<ExecutiveTicketInfo> listOpen = rfidDAO.managerTotalTicketsCount(username);			 
			   listData.add(listOpen);
			   System.out.println(listData);
			   Gson gsonBuilder = new GsonBuilder().create();
			   String openJson = gsonBuilder.toJson(listData);
		          return openJson.toString();
	    }
	    
		@RequestMapping(value="managerRejectedCount", method=RequestMethod.GET)
		@ResponseBody
		public String managerRejectedTicketsCount(ModelAndView model, HttpServletRequest request){
			
			String username=request.getParameter("username");
			List listData=new ArrayList();
			List<ExecutiveTicketInfo> rejectedTickets=rfidDAO.managerRejectedTicketsCount(username);
			listData.add(rejectedTickets);
			Gson gsonBuilder = new GsonBuilder().create();
			String openJson=gsonBuilder.toJson(listData);
			return openJson.toString();
		}
		
		@RequestMapping(value="getManagerTotalTickets", method = RequestMethod.GET)
	    @ResponseBody
	    public String getManagerTotalTickets(ModelAndView model,HttpServletRequest request) {
			String username=request.getParameter("username");
			System.out.println("USER"+username);
			List<ExecutiveTicketInfo> listTotal = rfidDAO.managerTotalTickets(username);
			  	   Gson gsonBuilder = new GsonBuilder().create();
	        	   String totalJson = gsonBuilder.toJson(listTotal);
		              return totalJson.toString();
	    }
		
		@RequestMapping(value="getManagerRejectedTickets", method=RequestMethod.GET)
		@ResponseBody
		public String getManagerRejectedTickets(ModelAndView model, HttpServletRequest request){
			String username=request.getParameter("username");
			List<ExecutiveTicketInfo> listRejected=rfidDAO.managerRejectedTickets(username);
			Gson gsonBuilder=new GsonBuilder().create();
			String totalJson = gsonBuilder.toJson(listRejected);
			return totalJson.toString();
		}		
		
		
		@RequestMapping(value="getDesignShelf", method = RequestMethod.GET)
	    @ResponseBody
	    public String getDesignShelf(ModelAndView model,HttpServletRequest request) {
			String exchangeName=request.getParameter("exchangeName");		
			List<Design> listTotal = rfidDAO.getDesignShelf(exchangeName);
			  	   Gson gsonBuilder = new GsonBuilder().create();
	        	   String totalJson = gsonBuilder.toJson(listTotal);
		              return totalJson.toString();
	    }
		
		@RequestMapping(value="getInvShelf", method = RequestMethod.GET)
	    @ResponseBody
	    public String getInvShelf(ModelAndView model,HttpServletRequest request) {
			String exchangeName=request.getParameter("exchangeName");		
			List<Inventory> listTotal = rfidDAO.getInvShelf(exchangeName);
			  	   Gson gsonBuilder = new GsonBuilder().create();
	        	   String totalJson = gsonBuilder.toJson(listTotal);
		              return totalJson.toString();
	    }
		
		/*Added for fetching roles in header page */
		 @RequestMapping(value= "getRoles", method = RequestMethod.GET)
			@ResponseBody
			public String getRoles(HttpServletRequest request) {
			 String username=request.getParameter("userName");
				List<User> user = rfidDAO.getRoles(username);
				Gson gsonBuilder = new GsonBuilder().create();
				String regionJSON = gsonBuilder.toJson(user);
		 	   	return regionJSON;
			}
		 

			@SuppressWarnings("unchecked")
			@RequestMapping(value="getUnassignedUnRejectedExecutives", method = RequestMethod.GET)
			@ResponseBody
			public String  getUnassignedUnRejectedExecutives(ModelAndView model,HttpServletRequest request) {
				String region=request.getParameter("region");
				String city=request.getParameter("city");
				String ticketId=request.getParameter("ticketId");
				System.out.println("city :::"+city);
				List listExec=new ArrayList();
				List<ExecutiveTicketInfo> listUnRejectedExecutives = rfidDAO.getUnassignedUnRejectedExecutives(ticketId);
				//System.out.println(listExecutives);
				for(ExecutiveTicketInfo exe:listUnRejectedExecutives)
				{
					listExec.add(exe.getExecutiveId());
				}
				
				String listExecs=null;
				if(listExec!=null)
	        	{
		        	StringBuffer sb = new StringBuffer();
		        	for(int i=0;i<listExec.size();i++)
		        	{
		        		if(i==(listExec.size()-1))
		        		sb.append("'"+listExec.get(i)+"'");
		        		else
		        			sb.append("'"+listExec.get(i)+"'"+",");
		        	}
		        	 listExecs = sb.toString();
	        	
	        	}
				List<Executive> listExecutives = rfidDAO.getUnassignedUnRejectedExecutivesData(region,city,listExecs);
				Gson gsonBuilder = new GsonBuilder().create();
				String executivesJson = gsonBuilder.toJson(listExecutives);
				return executivesJson.toString();
			}
	    
		
}
