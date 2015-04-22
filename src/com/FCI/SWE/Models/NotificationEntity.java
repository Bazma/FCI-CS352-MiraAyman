package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class NotificationEntity {
	
	private ArrayList<ObserveUser>list=new ArrayList();
	private String Message;
	private String TypeMessage;
	private String title; 
	/**
	 * Constructor accepts Notification data
	 * 
	 * @param U
	 *            NotificationEntity TypeMessage
	 */
	public NotificationEntity(String U,String msg,String t)
	{
		this.TypeMessage=U;
		this.Message=msg;
		this.title=t;
	}
	
	public void setList(ArrayList<ObserveUser>l)
	{
		this.list.clear();
		for (int i=0;i<l.size();i++)
		{
			list.add(l.get(i));
		}
		
	}
	
	public ArrayList <ObserveUser> getlist()
	{
	  return this.list;	
	}

	/**
	 * function getTypeMessage get TupeMessage to object Notification
	 * @return TypeMessage
	 */
	public String getTypeMessage()
	{
		return TypeMessage;
	}


    
	
	/**
	 * This static method will form NotificationEntity class using json format contains
	 * Notification data
	 * @param json
	 * String in json format contains Notification data
	 * @return Constructed TypeMessage
	 */
	public static NotificationEntity getConversation(String json) 
	{
		JSONParser parser = new JSONParser();
		try 
		{
			JSONObject object = (JSONObject) parser.parse(json);
			return new NotificationEntity(object.get("TypeMessage").toString(),object.get("Message").toString(),object.get("Title").toString());
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//
//	/**
//	 * This static method will form MessageEntity class using Title and
//	 * Id This method will search for Message in datastore
//	 * @return boolean if Message is found correctly or not
//	 */
//	public static boolean getNotification(String username) 
//	{
//		boolean check1=true;
//		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//        
//			   Query gaeQuery = new Query("notification");
//			   PreparedQuery pq = datastore.prepare(gaeQuery);
//		       for (Entity entity : pq.asIterable()) 
//			     {
//					//System.out.println(entity.getProperty("email").toString());
//					if (entity.getProperty("username").toString().equals(username))
//					   {
//					        check1=false;
//					        break;
//							//System.out.println("ana lk 3la tooooooooooooooooool ");
//					   }
//			      }
//				//System.out.println("bbbbbbbbbbbsssssssssssssssbsssssssssss");		
//			if (check1==true)
//			{
//		        //System.out.println("bbbbbbbbbbb   mora mira sssssssssssssssbsssssssssss");
//            	return true;
//            }    
//     return false;
//}

	
	/**
	 * This method will be used to save Notification object in datastore
	 * 
	 * @return boolean if Notification is saved correctly or not
	 */
	public Boolean saveNotification() 
	{
		String emailcur =user.currentactive.getEmail();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("notification");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (int i=0;i<this.list.size();i++)
		{
	     if (list.get(i).getemail().equals(emailcur))
	     {}
	     else
	     {
		Entity employee = new Entity("notification");
		employee.setProperty("emailto", list.get(i).getemail());
		employee.setProperty("emailfrom", emailcur);
		employee.setProperty("TypeMessage",this.TypeMessage);
		employee.setProperty("Message",this.Message);
		employee.setProperty("Title",this.title);
		datastore.put(employee);
	     }
		}
		list.clear();
		return true;
		
	}
	
	
	/** 
	 * This static method will form NotificationEntity class using json format contains
	 * Notification data
	 * @param json
	 *            String in json format contains Notification data
	 * @return Constructed TypeMessage
	 */
	public static String GetNotification(String json) 
	{
		JSONParser parser = new JSONParser();
		try 
		{
			JSONObject object = (JSONObject) parser.parse(json);
			return (object.get("TypeMessage").toString());
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return null;
	}
	
	
	
	/**
	 * 
	 * This static method will form MessageEntity class using friend emailfrom and
	 * status This method will search for friend in datastore
	 * @param emailfrom
	 *            friend emailfrom
	 * @param st
	 *            friend status
	 * @return emailto
	 */		
	public static ArrayList<Notification> GetNotifications(String user)
	{	
		ArrayList<Notification>List=new ArrayList();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb  check true in if ");
	    Query toQuery = new Query("notification");
		PreparedQuery pqo= datastore.prepare(toQuery);
		for (Entity entity : pqo.asIterable()) 
		{  
					System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb= lol ");
				//	System.out.println("message1= "+entity.getProperty("emailto").toString());
				//	System.out.println("message2="+entity.getProperty("emailfrom").toString());
					//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
		   if ( entity.getProperty("emailto").toString().equals(user))
		   {
				Notification ob = new Notification ();
				ob.SetEmailFrom(entity.getProperty("emailfrom").toString());
				ob.SetEmailto(entity.getProperty("emailto").toString());
				ob.SetTypeMessage(entity.getProperty("TypeMessage").toString());
				ob.SetMessage(entity.getProperty("Message").toString());
				ob.Settitle(entity.getProperty("Title").toString());
			    System.out.println("message1= "+entity.getProperty("Message").toString());
			   // List.add("there is notify of type "+entity.getProperty("TypeMessage").toString()+" from "+entity.getProperty("emailfrom").toString()+entity.getProperty("Message").toString());
	            List.add(ob);
		   } 
						
		}	
		       // System.out.println("message1= y hblaaaaaaa  ");			
      return List;
	}		

	

	/**
	 * This method will be used to save Notification object in datastore
	 * 
	 * @return boolean if Notification is saved correctly or not
	 */
	public Boolean saveNotificationRequest(String emailto, String emailfrom) 
	{
		//String emailcur =user.currentactive.getEmail();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("notification");
		PreparedQuery pq = datastore.prepare(gaeQuery);
	
		Entity employee = new Entity("notification");
		employee.setProperty("emailto", emailto);
		employee.setProperty("emailfrom", emailfrom);
		employee.setProperty("TypeMessage",this.TypeMessage);
		employee.setProperty("Message",this.Message);
		employee.setProperty("Title",this.title);
		datastore.put(employee);
	    // }
		
		//list.clear();
		return true;
		
	}
	
	

	/**
	 * 
	 * This static method will form MessageEntity class using friend emailfrom and
	 * status This method will search for friend in datastore
	 * @param emailfrom
	 *            friend emailfrom
	 * @param st
	 *            friend status
	 * @return emailto
	 */		
	public static ArrayList<Notification> GetMessageOfConversation(String user,String title)
	{	
		ArrayList<Notification>List=new ArrayList();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb  check true in if ");
	    Query toQuery = new Query("notification");
		PreparedQuery pqo= datastore.prepare(toQuery);
		for (Entity entity : pqo.asIterable()) 
		{  
					System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb= lol ");
				//	System.out.println("message1= "+entity.getProperty("emailto").toString());
				//	System.out.println("message2="+entity.getProperty("emailfrom").toString());
					//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
		   if ( entity.getProperty("emailto").toString().equals(user) &&entity.getProperty("Title").toString().equals(title))
		   {
				Notification ob = new Notification ();
				ob.SetEmailFrom(entity.getProperty("emailfrom").toString());
				ob.SetEmailto(entity.getProperty("emailto").toString());
				ob.SetTypeMessage(entity.getProperty("TypeMessage").toString());
				ob.SetMessage(entity.getProperty("Message").toString());
				ob.Settitle(entity.getProperty("Title").toString());
			   // System.out.println("message1= "+entity.getProperty("emailto").toString());
			   // List.add("there is notify of type "+entity.getProperty("TypeMessage").toString()+" from "+entity.getProperty("emailfrom").toString()+entity.getProperty("Message").toString());
	            List.add(ob);
		   } 
		   
						
		}
		for (int i=0;i<List.size();i++)
		{
			System.out.println("el list"+List.get(i).getMessage());
		}
		       // System.out.println("message1= y hblaaaaaaa  ");			
      return List;
	}		

	

}
