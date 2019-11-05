package com.cyient.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cyient.dao.RFIDDAO;
import com.cyient.model.Customer;
import com.cyient.model.Design;
import com.cyient.model.Inventory;
import com.cyient.model.Taginformation;
import com.cyient.model.Taginformation_history;
import com.cyient.model.Ticketing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.*;


@Controller
@RequestMapping("/")
public class FieldExecutiveController {
	private static final Logger logger = Logger
			.getLogger(RFIDAssetController.class);

	public FieldExecutiveController(){
		System.out.println("FieldExecutiveController()");
		 
	}

	@Autowired
	private RFIDDAO rfidDAO;
	
	
	
	@RequestMapping(value = "getinventorydetails", method = RequestMethod.GET)
	@ResponseBody
	public String listEmployee(@RequestParam("custId") String cid,ModelAndView model) throws IOException {
		
		List<Inventory> inventoryDetails=rfidDAO.fetchInventoryDetailsbyCustomer(cid);
		
//		for(Inventory inv:inventoryDetails){
//			
//		}
//		
		
		Gson gsonBuilder = new GsonBuilder().create();
		   String executivesJson = gsonBuilder.toJson(inventoryDetails);
	          return executivesJson.toString();
	}
	
	
	
	
	@RequestMapping(value = "/fetchinventoryDetails")
	public ModelAndView displayinventorydetails(ModelAndView model) {
		model.setViewName("inventoryDetails");
		return model;
		
	}
	

	@RequestMapping(value = "/viewTicketDetails")
	public ModelAndView viewTicketDetails(ModelAndView model) {
		model.setViewName("viewTicketDetails");
		return model;
		
	}
	
	@RequestMapping(value = "/customerDetails", method = RequestMethod.GET)
    public ModelAndView customerDetails(ModelAndView model) throws IOException {
         model.setViewName("customerDetails");
         return model;
    }


	@RequestMapping(value="/getCustomerData", method=RequestMethod.GET)
	@ResponseBody
	public String getCustomerDetails(HttpServletRequest request){
	       String customerId=request.getParameter("customerId");
	       List<Customer> customerList=rfidDAO.getCustomerDetails(customerId);
	       Gson gsonBuilder=new GsonBuilder().create();
	       String executiveJson=gsonBuilder.toJson(customerList);
	       return executiveJson.toString();
	       
	 } 
	

	@RequestMapping(value = "/designDetails", method = RequestMethod.GET)
	public ModelAndView designDetails(ModelAndView model) throws IOException {
		model.setViewName("designForm");
		return model;
	}
	
	
	 @RequestMapping(value="getDesignData",method = RequestMethod.GET)
	 @ResponseBody
	 public String  getDesignData(ModelAndView model,HttpServletRequest request) {
		 String customerId = request.getParameter("customerId");
			List<Design> listDesign = rfidDAO.getDesignData(customerId);
			System.out.println("LIST"+listDesign);
		   Gson gsonBuilder = new GsonBuilder().create();
		   String designJson = gsonBuilder.toJson(listDesign);
	       return designJson.toString();
	 }
	 
	 

	@RequestMapping(value="saveExistingData",method = RequestMethod.GET)
	 @ResponseBody
	 public String saveExistingData(ModelAndView model,HttpServletRequest request) {
		 String customerId = request.getParameter("customerId");
		 String inventoryJson = request.getParameter("inventoryJson");
		 String designJson = request.getParameter("designJson");
		 String customerJson = request.getParameter("customerJson");
		 String ticketId = request.getParameter("ticketId");
		 
		 System.out.println("INVController "+inventoryJson);
		 System.out.println("DESController "+designJson);
		 System.out.println("CustController "+customerJson);
//		 JSONObject custObj = (JSONObject)JSONSerializer.toJSON(customerJson);
//		 JSONObject designObj = (JSONObject)JSONSerializer.toJSON(designJson);
//		 JSONObject inventoryObj = (JSONObject)JSONSerializer.toJSON(inventoryJson);
//		 System.out.println("Customer"+custObj);
//		 System.out.println("Design"+designObj);
//		 System.out.println("Inventory"+inventoryObj);
		 
		
//		 Customer customer=new Customer();
//		 System.out.println(custObj.getString("custId"));
		 String uniqueid;
		 try{
			 uniqueid=rfidDAO.fetchtUniqueId(customerId,ticketId);
			 /*rfidDAO.saveCustomer(customerId,customerJson);
			 rfidDAO.saveInventory(customerId,inventoryJson);
			 rfidDAO.saveDesign(customerId,designJson);		
			 rfidDAO.updateTicketStatus(ticketId);	*/
			 return uniqueid;
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		return null;
		
		
//		 if(customer.getCustomerId().equals(custObj.getString("custId"))){			
//			 customer.setAddress(custObj.getString("address"));
//			 customer.setCity(custObj.getString("city"));
//			 customer.setCustomerName(custObj.getString("custName"));
//			 customer.setCustomerStatus(custObj.getString("custStatus"));
//			 customer.setFax(custObj.getString("fax"));
//			 customer.setPartial(custObj.getString("partial"));
//			 customer.setPostalCode(custObj.getString("postCode"));
//			 customer.setPhoneNum(Long.parseLong(custObj.getString("phone")));
//			 customer.setSurname(custObj.getString("surname"));
//			 customer.setUserId(custObj.getString("userId"));		
//			 
//		 }
//		
//		 if(customer.getCustomerId().equals(custObj.getString("custId"))){
//			 Design design=new Design();
//			 design.setTagId(Integer.parseInt(designObj.getString("tagId")));
//			 design.setExchangeName(designObj.getString("exchangename"));
//			 design.setShelfName((designObj.getString("shelfName")));
//			 design.setStartPoint(designObj.getString("startPoint"));
//			 design.setEndPoint(designObj.getString("endpoint"));
//			 design.setLength(designObj.getString("length"));
//			 design.setAddress(designObj.getString("address"));
//			 design.setFloor(designObj.getString("floor"));
//			 design.setSuite(designObj.getString("suite"));
//			 design.setVertIn(designObj.getString("vertIn"));
//			 rfidDAO.saveDesign(design);
//		 }
//		 
//		 if(customer.getCustomerId().equals(custObj.getString("custId"))){
//			 Inventory inventory=new Inventory();
//			 inventory.setEquipmentId(inventoryObj.getString("equipId"));
//			 inventory.setExchangeName(inventoryObj.getString("exchangenameId"));
//			 inventory.setShelfName(inventoryObj.getString("shelfName"));
//			 inventory.setLength(inventoryObj.getString("length"));
//			 inventory.setInventoryStatusCode(inventoryObj.getString("InvStatus"));
//			 inventory.setOwnershipTypeCode(inventoryObj.getString("ownerType"));
//			 inventory.setSiteName(inventoryObj.getString("siteName"));
//			// inventory.setInstalledDate(Date.valueOf(inventoryObj.getString("installedDate")));
//			 inventory.setOperationalState(inventoryObj.getString("operState"));
//			 rfidDAO.saveInventory(inventory);
//		 }
//		
			
	    //   return "Details updated successfully";
	 }
	 
	
	 @RequestMapping(value="saveNewData",method = RequestMethod.GET)
	 @ResponseBody
	 public String saveNewData(ModelAndView model,HttpServletRequest request) {
		 String customerId = request.getParameter("customerId");
		 String inventoryJson = request.getParameter("inventoryJson");
		 String designJson = request.getParameter("designJson");
		 String customerJson = request.getParameter("customerJson");
		 String ticketId = request.getParameter("ticketId");
		 System.out.println("ticket no>>>>>"+ticketId);
	       String uniqueid=rfidDAO.fetchtUniqueId(customerId,ticketId);

		 
//		 System.out.println(customerJson);
//		 System.out.println(inventoryJson);
//		 System.out.println(designJson);
//		 System.out.println("unique>>>>>>>>>..."+uniqueid);

		 
			
	       return uniqueid;
	 }
	 
	 @RequestMapping(value = "/saveallDetails", method = RequestMethod.GET)
	    public ModelAndView saveallDetails(ModelAndView model) throws IOException {
		 
		 	Taginformation taginfo= new Taginformation();
		 	model.addObject("taginformation",taginfo);
	         model.setViewName("tagForm");
	         return model;
	    }
	 @RequestMapping(value = "/updatealldetails", method = RequestMethod.GET)
	    public ModelAndView updateAllDetails(ModelAndView model) throws IOException {
		 
		 	Taginformation taginfo= new Taginformation();
		 	model.addObject("taginformation",taginfo);
	         model.setViewName("tagForm");
	         return model;
	    }
	 
	 @RequestMapping(value = "/saveTagInformation", method = RequestMethod.POST)
	 public ModelAndView saveTagInfo(@ModelAttribute("taginformation") Taginformation taginformation, ModelAndView model){
		 
		 rfidDAO.saveTaginfo(taginformation);
		 
		 return new ModelAndView("redirect:/home");
	 }
	 
	 

		@RequestMapping("getAllExchanges")
	    @ResponseBody
	    public String  getExchanges(ModelAndView model) {
			List<Inventory> listHistory = rfidDAO.getAllExchanges();
			  	   Gson gsonBuilder = new GsonBuilder().create();
	        	   String historyJson = gsonBuilder.toJson(listHistory);
		              return historyJson.toString();
	    }
		
		@RequestMapping(value="getDesignExchanges", method = RequestMethod.GET)
	    @ResponseBody
	    public String  getExchanges(ModelAndView model,HttpServletRequest request) {
			List<Design> listHistory = rfidDAO.getDesignExchanges();
			  	   Gson gsonBuilder = new GsonBuilder().create();
	        	   String historyJson = gsonBuilder.toJson(listHistory);
		              return historyJson.toString();
	    }
		
		@RequestMapping(value="getFloors", method = RequestMethod.GET)
	    @ResponseBody
	    public String  getFloors(ModelAndView model,HttpServletRequest request) {
			 
			 String exchange = request.getParameter("selectedExchange");
			List<Design> listHistory = rfidDAO.getFloor(exchange);
			  	   Gson gsonBuilder = new GsonBuilder().create();
	        	   String historyJson = gsonBuilder.toJson(listHistory);
		              return historyJson.toString();
	    }
		
		@RequestMapping(value="getSuites", method = RequestMethod.GET)
	    @ResponseBody
	    public String  getSuites(ModelAndView model,HttpServletRequest request) {
			 String exchange = request.getParameter("selectedExchange");
			 String floor = request.getParameter("selectedFloor");
			List<Design> listHistory = rfidDAO.getSuite(exchange,floor);
			  	   Gson gsonBuilder = new GsonBuilder().create();
	        	   String historyJson = gsonBuilder.toJson(listHistory);
		              return historyJson.toString();
	    }

		
		 @RequestMapping(value = "/fetchtaginfo", method = RequestMethod.POST)
		 @ResponseBody
		 public String fetchtaginfo(MultipartHttpServletRequest request,RedirectAttributes redirectAttributes) throws IOException{
			
			 
			 Iterator<String> itr =  request.getFileNames();
			 JSONObject designObj=new JSONObject();
			 for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				    String name = entry.getKey();
				    String value = entry.getValue()[0];
				   // System.out.println("name>>>>>>>."+name+"value>>>>>>>"+value);
				    designObj = (JSONObject)JSONSerializer.toJSON(value);
				   // System.out.println(designObj);
				}
			 
			// System.out.println("customerid"+designObj.getString("customer_id"));
			 
		     MultipartFile file = request.getFile(itr.next());
		     //System.out.println(file.getOriginalFilename() +" uploaded!");
			  
			 
			// System.out.println("file>>>>>>>>>"+file.getOriginalFilename());
			// System.out.println("file size>>>>>>>>>"+file.getSize());

			 if(file.getSize()>3145728){
				// System.out.println("selected image is more than max size");
						;
			  return "fail";
			 }
			 else{
				 Taginformation taginfo= new Taginformation();
				 taginfo.setCustomerid(designObj.getString("customer_id"));
				 taginfo.setTicketid(designObj.getString("ticket_id"));
				 taginfo.setStartpoint(designObj.getString("startPoint"));
				 taginfo.setTaguniqueid(designObj.getString("unique_id"));
				 taginfo.setPre_uniq(designObj.getString("pre_unique_id"));
				 taginfo.setFileName(file.getOriginalFilename());
				 taginfo.setFiledata(file.getBytes());
				 taginfo.setInvjson(designObj.getString("invdata"));
				 taginfo.setCusjon(designObj.getString("cusdata"));
				 taginfo.setDesJson(designObj.getString("desdata"));
				String flag= rfidDAO.saveTaginfo(taginfo);
				if(flag.equalsIgnoreCase("true")){
					System.out.println("ticketid======================"+designObj.getString("ticket_id"));
					String flag1= rfidDAO.updateTicketStatus(designObj.getString("ticket_id"));	
					System.out.println("update>>>>>.."+flag1);
					return flag1;
				}
				 
			   return "success";
			 }
		 }
	 
//		 @RequestMapping(value="getDetails", method = RequestMethod.GET)
//		    @ResponseBody
//		    public String  getDetails(ModelAndView model,HttpServletRequest request) {
//				 String custId = request.getParameter("custId");
//				 String floor = request.getParameter("ticketId");
//				List<Design> designList = rfidDAO.getDesignData(custId);
//				List<Inventory> invList = rfidDAO.fetchInventoryDetailsbyCustomer(custId);
//				List<Customer> custList = rfidDAO.getCustomerDetails(custId);
//				List list=new ArrayList();
//				list.add(designList);
//				list.add(invList);
//				list.add(custList);
////				JSONObject jsonObj=new JSONObject();
////				jsonObj.accumulate("Design", designList);
////				jsonObj.accumulate("Inventory", invList);
////				jsonObj.accumulate("Customer", custList);
//				  	   Gson gsonBuilder = new GsonBuilder().create();
//		        	   String listJson = gsonBuilder.toJson(list);
//			              return listJson.toString();
//		    }
		 
		 
		 
		 @SuppressWarnings({ "unchecked", "rawtypes" })
			@RequestMapping(value="getDetails", method = RequestMethod.POST)
			    @ResponseBody
			    public String  getDetails(ModelAndView model,HttpServletRequest request) {
					 String custId = request.getParameter("custId");
					 String floor = request.getParameter("ticketId");
					List<Design> designList = rfidDAO.getDesignData(custId);
					List<Inventory> invList = rfidDAO.fetchInventoryDetailsbyCustomer(custId);
					List<Customer> custList = rfidDAO.getCustomerDetails(custId);
					List list=new ArrayList();
					list.add(designList);
					list.add(invList);
					list.add(custList);
					try{
					List<Taginformation> taginfo=rfidDAO.fetchTaginfo(custId,floor);
					if(taginfo.isEmpty()){}
					else{
					System.out.println("taginfo>>>>>>>>>>>>>>>>>>>>>>>>>>."+taginfo.get(0).getFiledata());
					
					list.add(taginfo);
				
					byte[] encodeBase64 = Base64.encodeBase64(taginfo.get(0).getFiledata());
					String b=ImageConvert(encodeBase64);
					list.add(b);
					}
					
					}catch(Exception ex){
						List<Taginformation_history> taginfo=rfidDAO.fetchTaginfo(custId,floor);
						if(taginfo.isEmpty()){}
						else{
						System.out.println("taginfo>>>>>>>>>>>>>>>>>>>>>>>>>>."+taginfo.get(0).getFiledata());
						
						list.add(taginfo);
						byte[] encodeBase64 = Base64.encodeBase64(taginfo.get(0).getFiledata());
						String b=ImageConvert(encodeBase64);
						list.add(b);
						}
					}
				
					  	   Gson gsonBuilder = new GsonBuilder().create();
			        	   String listJson = gsonBuilder.toJson(list);
				              return listJson.toString();
			    }
		 
		 private String ImageConvert(byte[] encodeBase64){
				
			 String base64Encoded;
				try {
					base64Encoded = new String(encodeBase64, "UTF-8");
					System.out.println(" file data>>>>>>>>>>>>>>>>>>>>>>>>>>"+base64Encoded);
//					list.add(base64Encoded);
					return base64Encoded;

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			 
			 
		 }
		 
				 
		 @RequestMapping(value = "/savetaginfo", method = RequestMethod.POST)
		 @ResponseBody
		 public String saveExistingtaginfo(MultipartHttpServletRequest request,RedirectAttributes redirectAttributes) throws IOException{
			
			 
			 Iterator<String> itr =  request.getFileNames();
			 JSONObject designObj=new JSONObject();
			 for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				    String name = entry.getKey();
				    String value = entry.getValue()[0];
				   // System.out.println("name>>>>>>>."+name+"value>>>>>>>"+value);
				    designObj = (JSONObject)JSONSerializer.toJSON(value);
				   // System.out.println(designObj);
				}
			 
			// System.out.println("customerid"+designObj.getString("customer_id"));
			 
		     MultipartFile file = request.getFile(itr.next());
		     //System.out.println(file.getOriginalFilename() +" uploaded!");
			  
			 
			// System.out.println("file>>>>>>>>>"+file.getOriginalFilename());
			// System.out.println("file size>>>>>>>>>"+file.getSize());

			 if(file.getSize()>3145728){
				// System.out.println("selected image is more than max size");
						;
			  return "fail";
			 }
			 else{
				 Taginformation taginfo= new Taginformation();
				 taginfo.setCustomerid(designObj.getString("customer_id"));
				 taginfo.setTicketid(designObj.getString("ticket_id"));
				 taginfo.setStartpoint(designObj.getString("startPoint"));
				 taginfo.setTaguniqueid(designObj.getString("unique_id"));
				 taginfo.setPre_uniq(designObj.getString("pre_unique_id"));

				 taginfo.setFileName(file.getOriginalFilename());
				 taginfo.setFiledata(file.getBytes());
				 taginfo.setInvjson(designObj.getString("invdata"));
				 taginfo.setCusjon(designObj.getString("cusdata"));
				 taginfo.setDesJson(designObj.getString("desdata"));
				 
				 
				 String inv=designObj.getString("invdata").toString();
				 JSONObject invJosn=(JSONObject)JSONSerializer.toJSON(inv);
				 String cid=invJosn.getString("customerid");
				 
				 String des=designObj.getString("desdata").toString();
				 JSONObject desJson=(JSONObject)JSONSerializer.toJSON(des);
				 
				 String cus=designObj.getString("cusdata").toString();
				 JSONObject cusJson=(JSONObject)JSONSerializer.toJSON(cus);
				 
				
				 
				 System.out.println("current customer>>>>>>>>>>>>>>>>>>>>>>>>"+cid);
				 
				 // String s= rfidDAO.saveExistingTaginfo(taginfo,cid);
				 try{ 
				 List<Taginformation> taginformation=rfidDAO.fetchTaginfoByCid(cid);
				  if(taginformation.size()==0){
					  return "No tag information  found for selected customer";
					  
				  }else{
					  
					  rfidDAO.saveCustomer(cid,cus);
						 rfidDAO.saveInventory(cid,inv);
						 rfidDAO.saveDesign(cid,des);
					  
				  
				  System.out.println(" pre taginfo>>>>>"+taginformation.get(0));
				  Taginformation pretaginof=new Taginformation();
				  
				  pretaginof.setCustomerid(taginformation.get(0).getCustomerid());
				  pretaginof.setTicketid(taginformation.get(0).getTicketid());
				   pretaginof.setStartpoint(taginformation.get(0).getStartpoint());
				   pretaginof.setTaguniqueid(taginformation.get(0).getTaguniqueid());
				   pretaginof.setPre_uniq(taginformation.get(0).getPre_uniq());
				   pretaginof.setFileName(taginformation.get(0).getFileName());
				   pretaginof.setFiledata(taginformation.get(0).getFiledata());
				   pretaginof.setInvjson(taginformation.get(0).getInvjson());
				   pretaginof.setCusjon(taginformation.get(0).getCusjon());
				   pretaginof.setDesJson(taginformation.get(0).getDesJson());
				   pretaginof.setId(taginformation.get(0).getId());
				   System.out.println("id>>>>>>>>>>>>>>>."+taginformation.get(0).getId());
				  
				  //store it in taginfo history
				   Taginformation_history taghist= new Taginformation_history();
				   taghist.setCustomerid(taginformation.get(0).getCustomerid());
				   taghist.setTicketid(taginformation.get(0).getTicketid());
				   taghist.setStartpoint(taginformation.get(0).getStartpoint());
				   taghist.setTaguniqueid(taginformation.get(0).getTaguniqueid());
				   taghist.setPre_uniq(taginformation.get(0).getPre_uniq());

				   taghist.setFileName(taginformation.get(0).getFileName());
				   taghist.setFiledata(taginformation.get(0).getFiledata());
				   taghist.setInvjson(taginformation.get(0).getInvjson());
				   taghist.setCusjon(taginformation.get(0).getCusjon());
				   taghist.setDesJson(taginformation.get(0).getDesJson());
				   
				  
				  String flag=rfidDAO.saveTag_hist(taghist);
				  
				  if(flag.equalsIgnoreCase("true")){
					  
					  String flag1=rfidDAO.delete_taginfo(pretaginof,cid);
					  
					  if(flag1.equalsIgnoreCase("true")){
						  
						  String flag2=rfidDAO.save_newTaginfo(taginfo);
						  if(flag2.equalsIgnoreCase("true")){
							  
							  String flag3=rfidDAO.updateTicketStatus(designObj.getString("ticket_id"));
							  System.out.println("update>>>>>>>>>>>>>"+flag3);
							  return flag3;
						  }else{
							  System.out.println("flag2>>>>>."+flag2);
							  return flag;
						  }
					  }else{
						  System.out.println("flag1>>>>>."+flag);
						  return flag;
					  }
					  
				  }else{
					  System.out.println("flag>>>>>."+flag);
					  return flag;
				  }
				  }
				 }catch(Exception e){
					 return e.toString();
				 }
				  
				  
				 
				 //rfidDAO.updateTicketStatus(designObj.getString("ticket_id"));	
			  // return null;
			 }
				 
		 }
		 
		 
		 
			@RequestMapping(value="saveTechStatus", method = RequestMethod.GET)
		    @ResponseBody
		    public String  saveTechStatus(ModelAndView model,HttpServletRequest request) {
				 String ticketId = request.getParameter("ticketId");
				 String techStatus = request.getParameter("techStatus");
				 String exeId=request.getParameter("username");
				 String commentsData=request.getParameter("commentsData");
				 String remarksData=request.getParameter("remarksData");
				 System.out.println("COMMENTS"+commentsData);
				String status = rfidDAO.saveTechStatus(ticketId,techStatus,exeId,commentsData,remarksData);
				  	  return status;
		    }


			@RequestMapping(value = "/technicianAcceptedTickets")
			public ModelAndView technicianAcceptedTickets(ModelAndView model) throws IOException {
				model.setViewName("technicianAcceptedTickets");
				return model;
			} 
			
			@RequestMapping(value="getExecutiveAcceptedTickets", method = RequestMethod.GET)
		    @ResponseBody
		    public String  getExecutiveAcceptedTickets(HttpServletRequest request) {
				String username=request.getParameter("username");
				System.out.println("username:"+username);
				List<Ticketing> listExecOpen = rfidDAO.getExecAcceptedTicketsData(username);		
		        	   Gson gsonBuilder = new GsonBuilder().create();
		        	   String execOpenJson = gsonBuilder.toJson(listExecOpen);
		        	   System.out.println(execOpenJson);
			              return execOpenJson.toString();
		    }
			
		 
}
