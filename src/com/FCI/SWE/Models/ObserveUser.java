package com.FCI.SWE.Models;

import java.util.ArrayList;

public class ObserveUser extends Observer  {
	private String email;
	
	/**
	 * this function setmail to set email user to object observer user
	 * @param e
	 */
	public void setemail(String e)
	{
		this.email=e;
	}
	
	/**
	 *  function getemail get email to object observer user
	 * @return email
	 */
	public String getemail()
	{
		return this.email;
	}
	
	/**
	 * function that take list email and save it in table  NotificationEntity
	 * @param obj 
	 */
	public void update(NotificationEntity obj)
	{
		System.out.println(obj.getTypeMessage());
		for (int i=0;i<obj.getlist().size();i++)
		{
			
			System.out.println("yyyyyyyyyyyy"+obj.getlist().get(i).email);	
		}
		
		//NotificationEntity obj=new NotificationEntity("","");
	    obj.saveNotification();
	
			
		//	System.out.println("yyyyyyyyhhhhhhhhhhhh"+obj.);	
		
		
	}
	
	
	/**
	 * function that take list email and save it in table  NotificationEntity
	 * @param obj 
	 */
	public void RecivedNotication(NotificationEntity obj)
	{
	
		NotificationEntity ob = new NotificationEntity("","","");
		 ArrayList<Notification> Notifications= new ArrayList();
		 Notifications=ob.GetNotifications(user.currentactive.getEmail());
		//NotificationEntity obj=new NotificationEntity("","");
	    obj.saveNotification();
	
			
		//	System.out.println("yyyyyyyyhhhhhhhhhhhh"+obj.);	
		
		
	}
	
}
