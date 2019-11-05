package com.cyient.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.cyient.model.Ticketing;
import com.cyient.model.ExecutiveTicketInfo;;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ArrayList<String> Ticket_ID = new ArrayList<String>();
        Ticket_ID.add("Hello");
        Ticket_ID.add("kiran");

        System.out.println(Ticket_ID.get(0));
	}

}
