package com.cyient.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cyient.dao.RFIDDAO;
import com.cyient.model.Executive;
import com.cyient.model.Ticketing;
import com.cyient.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class RFIDAssetController {
	private static final Logger logger = Logger
			.getLogger(RFIDAssetController.class);

	public RFIDAssetController() {
		System.out.println("RFIDAssetController()");
		 
	}

	@Autowired
	private RFIDDAO rfidDAO;
	
	
	
	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		User user = new User();
		model.addObject("User", user);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		User user = new User();
		model.addObject("User", user);
		model.setViewName("userReg");
		return model;
	}
	
	@RequestMapping(value = "/sessionExpired")
	public ModelAndView sessionExpired(ModelAndView model) {
		model.setViewName("sessionExpired");
		return model;		
	}
	
	@RequestMapping(value = "/newExchange", method = RequestMethod.GET)
	public ModelAndView newExchange(ModelAndView model) {
		//Exchange exchange = new Exchange();
		//model.addObject("Exchange", exchange);
		User user=new User();
		model.addObject("User",user);
		model.setViewName("exchangeReg");
		return model;
	}
	
	@RequestMapping(value = "/newExecutive", method = RequestMethod.GET)
	public ModelAndView newExecutive(ModelAndView model) {
		Executive executive = new Executive();
		model.addObject("Executive", executive);
		model.setViewName("executiveReg");
		return model;
	}
	
	@RequestMapping(value="/newTicket", method=RequestMethod.GET)
	public ModelAndView newTicket(ModelAndView model)
	{
		Ticketing ticket=new Ticketing();
		model.addObject("Ticketing",ticket);
		model.setViewName("addTicket");
		return model;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView newRedirectHome(ModelAndView model) {
		model.setViewName("homePage");
		return model;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user,RedirectAttributes redirectAttributes) {
		String status="User Added Sucessfully";
		if (user.getUsername() !=null) { 
			rfidDAO.addUser(user);
		} 
		redirectAttributes.addFlashAttribute("status", status);
		return new ModelAndView("redirect:/newUser");
	}
	
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute User user,ModelAndView model, HttpSession session,HttpServletRequest request) {
           User resp = rfidDAO.getAllUsersOnCriteria(user.getUsername(),user.getPassword(),user.getType());        
           if(resp==null)
           {
                  return new ModelAndView("redirect:/");
           }
           else
           {      
        	  //session=request.getSession();
        	  session.setAttribute("userName",user.getUsername());
			  session.setAttribute("password",user.getPassword());
        	  session.setAttribute("userRole",user.getType());
        	  System.out.println(user.getUsername());
        	         	   
	              model.setViewName("homePage");
	              return model;
           }

    }	
	
	@RequestMapping(value = "/validateSelectUser", method = RequestMethod.GET)
	@ResponseBody
    public String checkSelectUser(ModelAndView model, HttpSession session,HttpServletRequest request) {
	
          // User resp = rfidDAO.getAllUsersOnCriteria(user.getUsername(),user.getPassword(),user.getType());        
		 String username=request.getParameter("userName");
		 String password=request.getParameter("password");
		 String role=request.getParameter("role");
		 System.out.println(username+":"+password+":"+role);
		 User resp = rfidDAO.getAllUsersOnCriteria(username,password,role);  
	
		 
           if(resp==null)
           {
        	   return "failure";
                 // return new ModelAndView("redirect:/");
           }
           else
           {      
        	  //session=request.getSession();
        	  session.setAttribute("userName",username);
        	  session.setAttribute("password",password);
        	  session.setAttribute("userRole",role);
        	  System.out.println(username);
	          // model.setViewName("homePage");
	              return "success";
           }
    }

	@RequestMapping(value = "/saveCreatedTicket", method = RequestMethod.POST)
	public ModelAndView saveTicket(@ModelAttribute Ticketing ticket,RedirectAttributes redirectAttributes) {
	
		rfidDAO.addTicket(ticket);
		String status="Ticket Created Successfully";
		redirectAttributes.addFlashAttribute("status", status);
		return new ModelAndView("redirect:/newTicket");
	}
	
	 @RequestMapping(value = "/logout")
	 public String logout(@ModelAttribute User user, HttpSession session,HttpServletRequest request) {
          	  session.removeAttribute("userName");
          	  session.invalidate();
              return "redirect:/";
	 }

	 
	 @RequestMapping(value="/getLastExecutiveId", method=RequestMethod.GET)
	 @ResponseBody
	 public String getLastExecutiveId(HttpServletRequest request){
		
		 List<Executive> executiveList=rfidDAO.getExecutiveId();
		 Gson gsonBuilder=new GsonBuilder().create();
		 String executiveJson=gsonBuilder.toJson(executiveList);
		 return executiveJson.toString();
		 
	 }
	 
	 @RequestMapping(value="/getLastTicketId", method=RequestMethod.GET)
	 @ResponseBody
	 public String getLastTicketId(HttpServletRequest request){
		
		 List<Ticketing> ticketList=rfidDAO.getTicketId();
		 Gson gsonBuilder=new GsonBuilder().create();
		 String executiveJson=gsonBuilder.toJson(ticketList);
		 return executiveJson.toString();
		 
	 }
	 
	 @RequestMapping(value="/getLastUniqueId", method=RequestMethod.GET)
	 @ResponseBody
	 public String getLastUniqueId(HttpServletRequest request){
		
		 List<Ticketing> ticketList=rfidDAO.getUniqueId();
		 Gson gsonBuilder=new GsonBuilder().create();
		 String executiveJson=gsonBuilder.toJson(ticketList);
		 return executiveJson.toString();
		 
	 }
	 
}
