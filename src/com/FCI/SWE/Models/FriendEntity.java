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

		/**
		 * Constructor accepts user data
		 * 
		 * @param emailto
		 *            friend email to
		 * @param emailfrom
		 *            friend email from
		
		 */
		public FriendEntity(String emailto, String emailfrom) {
			this.emailto = emailto;
			this.emailfrom = emailfrom;

		}

		public String getemailto() {
			return emailto;
		}

		public String getemailfrom() {
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
		public static FriendEntity getFriend(String json) {

			JSONParser parser = new JSONParser();
			try {
				JSONObject object = (JSONObject) parser.parse(json);
				return new FriendEntity(object.get("emailto").toString(), object.get(
						"emailfrom").toString());
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

		public static boolean getFriend(String emailto, String emailfrom) {
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
						   }
				      }
				
				if (check==true)
				{
					
					Query toQuery = new Query("friend");
					PreparedQuery pqo= datastore.prepare(toQuery);
					for (Entity entity : pqo.asIterable()) 
					{
						System.out.println(entity.getProperty("email").toString());
						//ya Mira we will check in every row in the friend table that if the emailto == emailto or emailfrom == email from && emailto == email from 
						if (entity.getProperty("emailto").toString().equals(emailto) && entity.getProperty("emailfrom").toString().equals(emailfrom))
						{
						  check1=false;	
						  break;
					    } 
							
					}
					
				}
	         }
           
            if (check==true &&check1==true)
            {
            	
            	return true;
            }
            
            else return false;
		}

		/**
		 * This method will be used to save friend object in datastore
		 * 
		 * @return boolean if friend is saved correctly or not
		 */
		public Boolean saveFriend() {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query gaeQuery = new Query("friend");
			PreparedQuery pq = datastore.prepare(gaeQuery);
		

			Entity employee = new Entity("friend");

			employee.setProperty("emailto", this.emailto);
			employee.setProperty("emailfrom", this.emailfrom);
			datastore.put(employee);
			
			
			Entity employee1 = new Entity("friend");

			employee1.setProperty("emailto", this.emailfrom);
			employee1.setProperty("emailfrom", this.emailto);
			datastore.put(employee1);


			return true;

		}
		
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
