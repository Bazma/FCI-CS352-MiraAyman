package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PageEntity {
	public String Name;
	public String Type ;
	public String Category;
	public String like ;
	public String Owner;
	
	
	
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetName(String n)
	 {
		 this.Name=n;
	 }
	
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetType(String n)
	 {
		 this.Type=n;
	 }
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetCategory(String n)
	 {
		 this.Category=n;
	 }
	 /**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void Setlike(String n)
	 {
		 this.like=n;
	 }
	 /**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetOwner(String n)
	 {
		 this.Owner=n;
	 }

		/**
		 * function getName get name to object user
		 * @return name
		 */
		public String getName() 
		{
			return Name;
		}
		

		/**
		 * function getName get name to object user
		 * @return name
		 */
		public String getType() 
		{
			return Type;
		}
		

	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getOwner() 
	{
		return Owner;
	}
	
	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getCategory() 
	{
		return Category;
	}
	
	
	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getlike() 
	{
		return like;
	}
	

	
	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static PageEntity getpost(String json)
	{
		PageEntity obj= new PageEntity(); 
		JSONParser parser = new JSONParser();
		try
		{
			JSONObject object = (JSONObject) parser.parse(json);
			obj.SetOwner(object.get("owner").toString());
			obj.SetCategory(object.get("Category").toString());
			obj.SetName(object.get("Name").toString());
			obj.Setlike(object.get("like").toString());
			obj.SetType(object.get("Type").toString());
			
			return obj;
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for friend in datastore
	 * @return boolean if friend is found correctly or not
	 */
	public static boolean CheckPage(String Name) 
	{
	   
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         Query gaeQuery = new Query("page");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
					//System.out.println(entity.getProperty("email").toString());
					if (entity.getProperty("Name").toString().equals(Name))
					   {
					        return false;
					        
							//System.out.println("ana lk 3la tooooooooooooooooool ");
					   }
			      }
		    
     return true;
}

	
	
	/**
	 * This method will be used to save Notification object in datastore
	 * 
	 * @return boolean if Notification is saved correctly or not
	 */
	public Boolean savePage() 
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("page");
		PreparedQuery pq = datastore.prepare(gaeQuery); 
		Entity employee = new Entity("page");
		employee.setProperty("Owner",this.Owner);
		employee.setProperty("Category", this.Category);
		employee.setProperty("like",this.like);
		employee.setProperty("Type",this.Type);	
		employee.setProperty("Name",this.Name);
		
		datastore.put(employee);
		return true;
		
	}
	

}
