package com.FCI.SWE.Models;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class MessageEntity {

	private String email;
	private String ID;
	/**
	 * Constructor accepts Message data
	 * 
	 * @param email
	 *            Message email
	 * @param emailfrom
	 *            Message ID
	
	 */
	public MessageEntity(String email,String id)
	{
		this.email=email;
		this.ID=id;
	}

	/**
	 * function getemail get email to object Message
	 * @return email
	 */
	public String getemail()
	{
		return email;
	}

	/**
	 * function getid get ID to object Message
	 * @return ID
	 */
	public String getid() 
	{
		return ID;
	}
    
	
	/**
	 * This static method will form MessageEntity class using json format contains
	 * Message data
	 * @param json
	 * String in json format contains Message data
	 * @return Constructed Message entity
	 */
	public static MessageEntity getMessage(String json) 
	{
		JSONParser parser = new JSONParser();
		try 
		{
			JSONObject object = (JSONObject) parser.parse(json);
			return new MessageEntity(object.get("email").toString(), object.get("ID").toString());
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This static method will form MessageEntity class using Message email and
	 * id This method will serach for Message in datastore
	 * @return boolean if Message is found correctly or not
	 */
	public static boolean getMessage(String email, String id) 
	{
		boolean check=false,check1=true;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
			   Query gaeQuery = new Query("users");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
					//System.out.println(entity.getProperty("email").toString());
					if (entity.getProperty("email").toString().equals(email))
					   {
					        check=true;	
							//System.out.println("ana lk 3la tooooooooooooooooool ");
					   }
			      }
				//System.out.println("bbbbbbbbbbbsssssssssssssssbsssssssssss");		
			if (check==true)
			{
				//System.out.println("bbbbbbbb  check true in if ");
				Query toQuery = new Query("messages");
				PreparedQuery pqo= datastore.prepare(toQuery);
				for (Entity entity : pqo.asIterable()) 
				{
					//System.out.println("bbbbbbbbbbb="+check+" iofofofo = "+check1+"fdurwgwurgurwghwurgiurgiurg");
					//System.out.println("bbbbbbbbbbb= lol ");
					//System.out.println("message1= "+entity.getProperty("emailto").toString());
					//System.out.println("message2="+entity.getProperty("emailfrom").toString());
					//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
					if (entity.getProperty("email").toString().equals(email) && entity.getProperty("ID").toString().equals(id))
					{
					  check1=false;	
					 // System.out.println("bbbbbbbbbbbsssssssssssssssbsssssssssss tany ar7ameny ");
					  break;
				    } 		
				}	
				//System.out.println("bbbbbbbbbbb="+check+" iofofofo = "+check1);
			}
			//System.out.println("bbbbbbbbbbb="+check+" iofofofo = "+check1);
            if (check==true && check1==true)
            {
            	//System.out.println("bbbbbbbbbbb   mora mira sssssssssssssssbsssssssssss");
            	return true;
            }    
     return false;
}

	
	/**
	 * This method will be used to save Message object in datastore
	 * 
	 * @return boolean if Message is saved correctly or not
	 */
	public Boolean saveMessage() 
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Entity employee = new Entity("messages");
		employee.setProperty("email", this.email);
		employee.setProperty("ID", this.ID);
		datastore.put(employee);	
		return true;
	}
	
	
	/** 
	 * This static method will form MessageEntity class using json format contains
	 * Message data
	 * 
	 * @param json
	 *            String in json format contains Message data
	 * @return Constructed ID
	 */
	public static String GetMessage(String json) 
	{
		JSONParser parser = new JSONParser();
		try 
		{
			JSONObject object = (JSONObject) parser.parse(json);
			return (object.get("ID").toString());
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
	 * This static method will form MessageEntity class using Message email 
	 * This method will serach for Message in datastore
	 * @param email
	 *            Message email
	 * @return ID
	 */		
	public static String GetMessages(String email)
	{	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				//System.out.println("bbbbbbbb  check true in if ");
	    Query toQuery = new Query("messages");
		PreparedQuery pqo= datastore.prepare(toQuery);
		for (Entity entity : pqo.asIterable()) 
		{  
				//	System.out.println("bbbbbbbbbbb= lol ");
				//	System.out.println("message1= "+entity.getProperty("emailto").toString());
				//	System.out.println("message2="+entity.getProperty("emailfrom").toString());
					//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
		   if ( entity.getProperty("email").toString().equals(email))
		   {
				       // System.out.println("message1= "+entity.getProperty("emailto").toString());
			    return entity.getProperty("ID").toString();
	       } 
						
		}	
		       // System.out.println("message1= y hblaaaaaaa  ");			
      return "notfound";
	}		

	

	/**
	 * 
	 * This static method will form MessageEntity class using Message ID and
	 * email This method will serach for Message in datastore
	 * @param IDs
	 *            Message ID
	 * @return email
	 */		
	public static ArrayList<ObserveUser> GetEmails(ArrayList<String>IDs,String currentemail)
	{	
		ArrayList<ObserveUser> Emails = new ArrayList();
	
		for (int i=0;i<IDs.size();i++)
		{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				//System.out.println("bbbbbbbb  check true in if ");
	    Query toQuery = new Query("messages");
		PreparedQuery pqo= datastore.prepare(toQuery);
		for (Entity entity : pqo.asIterable()) 
		{  
				//	System.out.println("bbbbbbbbbbb= lol ");
				//	System.out.println("message1= "+entity.getProperty("emailto").toString());
				//	System.out.println("message2="+entity.getProperty("emailfrom").toString());
					//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
		   if ( entity.getProperty("ID").toString().equals(IDs.get(i))&&entity.getProperty("email").toString()!=currentemail)
		   {
				       // System.out.println("message1= "+entity.getProperty("emailto").toString());
			   // return entity.getProperty("ID").toString();
			   String x=entity.getProperty("email").toString();
				ObserveUser obj= new ObserveUser();
				obj.setemail(x);
			   Emails.add(obj);
	       } 
						
		}	
		}
		for (int i=0;i<Emails.size();i++)
		{
		 System.out.println("email = "+Emails.get(i).getemail());	
		}
		// System.out.println("message1= y hblaaaaaaa  ");			
      return Emails;
	}	
	


}
