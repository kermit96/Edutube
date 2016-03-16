package com.wing.mainApp.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class EduSessionListener
 *
 */
public class EduSessionListener implements HttpSessionListener {

	private HashMap map = new HashMap();
	
    /**
     * Default constructor. 
     */
    public EduSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession  session = se.getSession();
    	String id = session.getId();
    	System.out.println("session="+id);
    	map.put(id, session);
    	
    	    	    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	
    	HttpSession  session = se.getSession();
    	String id = session.getId();    	
    	System.out.println("session remove="+id);
    	map.remove(id);
    	
    }
     
    public HttpSession getSession(String id) 
    {
    	
      	 return (HttpSession)map.get(id);
    }
        	
}
