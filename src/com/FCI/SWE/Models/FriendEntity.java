package com.FCI.SWE.Models;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for friend, it will holds friend data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class FriendEntity {

	
		
		private String emailto;
		private String emailfrom;
		private String status;
		/**
		 * Constructor accepts user data
		 * 
		 * @param emailto
		 *            friend email to
		 * @param emailfrom
		 *            friend email from
		
		 */
		public FriendEntity(String emailto, String emailfrom,String s)
		{
			this.emailto=emailto;
			this.emailfrom=emailfrom ;
			this.status=s;
		}

		public String getemailto()
		{
			return emailto;
		}

		public void setstatus(String st) 
		{
			this.status=st;

		}

		public String getstat() 
		{
			return status;
		}

		public String getemailfrom() 
		{
			return emailfrom;
		}


		/**
		 * 
		 * This static method will form FriendEntity class using json format contains
		 * user data
		 * 
		 * @param json
		 *            String in json format contains Friend data
		 * @return Constructed Friend entity
		 */
		public static FriendEntity getFriend(String json) 
		{

			JSONParser parser = new JSONParser();
			try {
				JSONObject object = (JSONObject) parser.parse(json);
				return new FriendEntity(object.get("emailto").toString(), object.get(
						"emailfrom").toString(),object.get("status").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}

		/**
		 * 
		 * This static method will form UserEntity class using user name and
		 * password This method will serach for friend in datastore
		 * 
		 * @param emailto
		 *            friend email to
		 * @param emailfrom
		 *            friend email from
		 * @return Constructed friend entity
		 */

		public static boolean getFriend(String emailto, String emailfrom,String st) {
			boolean check=false,check1=true;
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

            if ( emailfrom.equals(emailto))
            {
            	return false;
            }
            else 
            {
				   Query gaeQuery = new Query("users");
				   PreparedQuery pq = datastore.prepare(gaeQuery);
			       for (Entity entity : pq.asIterable()) 
				     {
						System.out.println(entity.getProperty("email").toString());
						if (entity.getProperty("email").toString().equals(emailto))
						   {
						     check=true;	

								System.out.println("ana lk 3la tooooooooooooooooool ");
						   }
				      }
				

					System.out.println("bbbbbbbbbbbsssssssssssssssbsssssssssss");
					
				if (check==true)
				{
					

					System.out.println("bbbbbbbb  check true in if ");
					Query toQuery = new Query("friend");
					PreparedQuery pqo= datastore.prepare(toQuery);
					for (Entity entity : pqo.asIterable()) 
					{
						System.out.println("bbbbbbbbbbb="+check+" iofofofo = "+check1+"fdurwgwurgurwghwurgiurgiurg");
						System.out.println("bbbbbbbbbbb= lol ");
						System.out.println("message1= "+entity.getProperty("emailto").toString());
						System.out.println("message2="+entity.getProperty("emailfrom").toString());
						//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
						if (entity.getProperty("emailto").toString().equals(emailto) && entity.getProperty("emailfrom").toString().equals(emailfrom))
						{
						  check1=false;	
						  System.out.println("bbbbbbbbbbbsssssssssssssssbsssssssssss tany ar7ameny ");
						  break;
					    } 
							
					}	
					System.out.println("bbbbbbbbbbb="+check+" iofofofo = "+check1);
				}
				System.out.println("bbbbbbbbbbb="+check+" iofofofo = "+check1);
	            if (check==true && check1==true)
	            {
	            	System.out.println("bbbbbbbbbbb   mora mira sssssssssssssssbsssssssssss");
	            	return true;
	            }
	            
	         }

            	return false;
		}

		/**
		 * This method will be used to save friend object in datastore
		 * 
		 * @return boolean if friend is saved correctly or not
		 */
		public Boolean saveFriend(String status) {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query gaeQuery = new Query("friend");
			PreparedQuery pq = datastore.prepare(gaeQuery);

			Entity employee = new Entity("friend");

			employee.setProperty("emailto", this.emailto);
			employee.setProperty("emailfrom", this.emailfrom);
			employee.setProperty("status", "sendto");
			
			datastore.put(employee);
			
			
			Entity employee1 = new Entity("friend");

			employee1.setProperty("emailto", this.emailfrom);
			employee1.setProperty("emailfrom", this.emailto);
			employee1.setProperty("status", "sendfrom");
			
			datastore.put(employee1);


			return true;

		}
		
		
		public static String GetRequsts(String json) 
		{

			JSONParser parser = new JSONParser();
			try {
				JSONObject object = (JSONObject) parser.parse(json);
				return (object.get("emailto").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}
		
		public static String GetRequsts(String emailfrom,String st) {
			
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

					System.out.println("bbbbbbbb  check true in if ");
					Query toQuery = new Query("friend");
					PreparedQuery pqo= datastore.prepare(toQuery);
					for (Entity entity : pqo.asIterable()) 
					{  
						System.out.println("bbbbbbbbbbb= lol ");
						System.out.println("message1= "+entity.getProperty("emailto").toString());
						System.out.println("message2="+entity.getProperty("emailfrom").toString());
						//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
						if (entity.getProperty("status").toString().equals(st) && entity.getProperty("emailfrom").toString().equals(emailfrom))
						{
					        System.out.println("message1= "+entity.getProperty("emailto").toString());
					        return entity.getProperty("emailto").toString();
						} 
							
					}	
			        System.out.println("message1= y hblaaaaaaa  ");
						
            	return "notfound";
		}


		
		
		
		
		
		
public static boolean AcceptFriends(String emailfrom,String emailto) {
			boolean check = false,check1=false;
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
                    
				System.out.println("bbbbbbbb  check true in if ");
					Query toQuery = new Query("friend");
					PreparedQuery pqo= datastore.prepare(toQuery);
					for (Entity entity : pqo.asIterable()) 
					{  
						System.out.println("bbbbbbbbbbb= lol ");
						System.out.println("message1= "+entity.getProperty("emailto").toString());
						System.out.println("message2="+entity.getProperty("emailfrom").toString());
						//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
						if ( entity.getProperty("emailfrom").toString().equals(emailfrom)&&entity.getProperty("emailto").toString().equals(emailto))
						{
				//			entity.setProperty("status", "1");
					        System.out.println("message1= "+entity.getProperty("emailto").toString());
					        System.out.println("el data base "+entity.getProperty("emailto").toString()+entity.getProperty("emailfrom").toString() +entity.getProperty("status").toString());
						check= true;
						} 
						
						if ( entity.getProperty("emailfrom").toString().equals(emailto)&&entity.getProperty("emailto").toString().equals(emailfrom))
						{
					//		entity.setProperty("status", "1");
					        System.out.println("message2= "+entity.getProperty("emailto").toString());
					        System.out.println("el data base "+entity.getProperty("emailto").toString()+entity.getProperty("emailfrom").toString() +entity.getProperty("status").toString());
					        check1=true;
						} 
						
					if (check==true &&check1==true)
					{return true;}
							
					}	
					
            	return false;
            
		}



public void savestatusFriend() {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
			Query toQuery = new Query("friend");
			PreparedQuery pqo= datastore.prepare(toQuery);
			for (Entity entity : pqo.asIterable()) 
			{  
				//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
				if ( entity.getProperty("emailfrom").toString().equals(emailfrom)&&entity.getProperty("emailto").toString().equals(emailto))
				{
					entity.setProperty("status", "1");
			        System.out.println("message1= "+entity.getProperty("emailto").toString());
			        System.out.println("el data base "+entity.getProperty("emailto").toString()+entity.getProperty("emailfrom").toString() +entity.getProperty("status").toString());
			    	
					
					datastore.put(entity);	
			        
				}
				
				if ( entity.getProperty("emailfrom").toString().equals(emailto)&&entity.getProperty("emailto").toString().equals(emailfrom))
				{
					entity.setProperty("status", "1");
			        System.out.println("message2= "+entity.getProperty("emailto").toString());
			        System.out.println("el data base "+entity.getProperty("emailto").toString()+entity.getProperty("emailfrom").toString() +entity.getProperty("status").toString());
					
					datastore.put(entity);
				} 
				
			}
					
				
	       
				
 
}
		
		
		
		
}
