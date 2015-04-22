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

public class ConversationEntity {
	private String title;
	
	/**
	 * Constructor accepts Conversation data
	 * @param title
	 *            Conversation title
	 */
	public ConversationEntity(String title)
	{
		this.title=title;
	//	this.ID=id;
	}

	/**
	 * function getetitle get title to object Conversation
	 * @return title
	 */
	public String gettitle()
	{
		return title;
	}

	/**
	 * function getid get state to object friend
	 * @return id
	 */
	/*public String getid() 
	{
		return ID;
	}*/
    
	
	/**
	 * This static method will form ConversationEntity class using json format contains
	 * Conversation data
	 * @param json
	 * String in json format contains Conversation data
	 * @return Constructed ConversationEntity
	 */
	public static ConversationEntity getConversation(String json) 
	{
		JSONParser parser = new JSONParser();
		try 
		{
			JSONObject object = (JSONObject) parser.parse(json);
			return new ConversationEntity(object.get("title").toString());
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This static method will form ConversationEntity class using Title
	 * This method will search for Conversation in datastore
	 * @return boolean if Conversation is found correctly or not
	 */
	public static boolean getConversations(String title) 
	{
		System.out.println("hoph");
		
		boolean check1=true;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
			   Query gaeQuery = new Query("conversation");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
					System.out.println(entity.getProperty("title").toString());
					System.out.println("el m3aya "+title);
					if (entity.getProperty("title").toString().equals(title))
					   {
						System.out.println("ana lk 3la tooooooooooooooooool "); 
						   check1=false;
					        break;
							
					   }
			      }
				//System.out.println("bbbbbbbbbbbsssssssssssssssbsssssssssss");		
			if (check1==true)
			{
		        //System.out.println("bbbbbbbbbbb   mora mira sssssssssssssssbsssssssssss");
            	return true;
            }    
     return false;
}

	
	/**
	 * This method will be used to save Conversation object in datastore 
	 * @return boolean if Conversation is saved correctly or not
	 */
	public Boolean saveconversation() 
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		int x=list.size() + 1;	
		String w=""+x ;
		Entity employee = new Entity("conversation");
		employee.setProperty("title", this.title);
		employee.setProperty("ID",w);
		datastore.put(employee);
		return true;
	}
	
	
	/** 
	 * This static method will form ConversationEntity class using json format contains
	 * Conversation data
	 * @param json
	 *            String in json format contains Conversation data
	 * @return Constructed ID
	 */
	public static String GetConversation(String json) 
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
	 * This static method will form ConversationEntity class using Conversation title
	 *This method will search for Conversation in datastore
	 * @param title
	 *            Conversation title
	 * @return ID
	 */		
	public static String Getonversatons(String title)
	{	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				//System.out.println("bbbbbbbb  check true in if ");
	    Query toQuery = new Query("conversation");
		PreparedQuery pqo= datastore.prepare(toQuery);
		for (Entity entity : pqo.asIterable()) 
		{  
//					System.out.println("bbbbbbbbbbb= lol ");
//					System.out.println("message1= "+entity.getProperty("title").toString());
//					System.out.println("message2="+title);
					//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
		   if ( entity.getProperty("title").toString().equals(title))
		   {
				     //   System.out.println("message1= "+entity.getProperty("ID").toString());
			    return entity.getProperty("ID").toString();
	       } 
						
		}	
		       // System.out.println("message1= y hblaaaaaaa  ");			
      return "notfound";
	}
	
	


	/**
	 * 
	 * This static method will form ConversationEntity class using Conversation title and
	 * ID This method will search for Conversation in datastore
	 * @param title
	 *            Conversation title
	 * @return IDs
	 */		
	public static ArrayList<String> GetIDS(String title)
	{	
		ArrayList<String>IDS = new ArrayList();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				//System.out.println("bbbbbbbb  check true in if ");
	    Query toQuery = new Query("conversation");
		PreparedQuery pqo= datastore.prepare(toQuery);
		for (Entity entity : pqo.asIterable()) 
		{  
//					System.out.println("bbbbbbbbbbb= lol ");
//					System.out.println("message1= "+entity.getProperty("title").toString());
//					System.out.println("message2="+title);
					//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
		   if ( entity.getProperty("title").toString().equals(title))
		   {
				     //   System.out.println("message1= "+entity.getProperty("ID").toString());
			  //  return entity.getProperty("ID").toString();
			   IDS.add(entity.getProperty("ID").toString());
	       } 
						
		}	
		       // System.out.println("message1= y hblaaaaaaa  ");			
      return IDS;
	}
	

}
