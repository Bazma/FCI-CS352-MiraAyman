package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class LikePageEntity {

	public String Name;
	public String like ;
	public String email;
	
	
	
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
	 public  void Setemail(String n)
	 {
		 this.email=n;
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
		public String getemail() 
		{
			return email;
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
	public static LikePageEntity getLike(String json)
	{
		LikePageEntity obj= new LikePageEntity(); 
		JSONParser parser = new JSONParser();
		try
		{
			JSONObject object = (JSONObject) parser.parse(json);
		
			obj.Setemail(object.get("email").toString());
			obj.SetName(object.get("Name").toString());
			obj.Setlike(object.get("like").toString());
			
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
	public static boolean CheckLikePage(String Name) 
	{
	   String email=user.currentactive.getEmail();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		boolean check=false,check1=false;
         Query gaeQuery = new Query("page");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
					//System.out.println(entity.getProperty("email").toString());
					if (entity.getProperty("Name").toString().equals(Name))
					   {
					        check=true;
					        break;
							//System.out.println("ana lk 3la tooooooooooooooooool ");
					   }
			      }
		       if (check==true)
		       {
		    	   Query gaeQuery1 = new Query("LikePage");
				   PreparedQuery pq1 = datastore.prepare(gaeQuery1);
			       for (Entity entity1 : pq1.asIterable()) 
				     {
						//System.out.println(entity.getProperty("email").toString());
						if (entity1.getProperty("Name").toString().equals(Name)&&entity1.getProperty("email").toString().equals(email))
						   {
						        check1=true;
						        break;
								//System.out.println("ana lk 3la tooooooooooooooooool ");
						   }
				      } 
		    	   
		       }
		    if (check==true&&check1==false)
		    {
		    	return true;
		    }
     return false;
}

	
	
	/**
	 * This method will be used to save Notification object in datastore
	 * 
	 * @return boolean if Notification is saved correctly or not
	 */
	public Boolean saveLikePage() 
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("LikePage");
		PreparedQuery pq = datastore.prepare(gaeQuery); 
		Entity employee = new Entity("LikePage");
		employee.setProperty("like",this.like);
		employee.setProperty("email",this.email);	
		employee.setProperty("Name",this.Name);
		
		datastore.put(employee);
		return true;
		
	}
	

}
