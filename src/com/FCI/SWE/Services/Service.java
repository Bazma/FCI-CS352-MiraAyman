package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.FriendEntity;
import com.FCI.SWE.Models.UserEntity;
import com.FCI.SWE.Models.user;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class Service {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,@FormParam("password") String pass)
	{
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}

		return object.toString();

	}
	@POST
	@Path("/AddFriendService")
	public String AddFriendService(@FormParam("emailto") String emailto,
			@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat) 
	{
		System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+emailto+stat);
		
		boolean check=false;
		FriendEntity friend = new FriendEntity(emailto, emailfrom,stat);
		check =friend.getFriend(emailto, emailfrom, stat);
		System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
		
		if (check == true) {
		
		    object.put("Status", "OK");
			friend.saveFriend(stat);
			
		}
		else 
		{
			object.put("Status", "Failed");
		}
System.out.println(object.toString());
		return object.toString();

	}
	@POST
	@Path("/ListReqestService")
	public String ListrequestsService(@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat) 
	{
		System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+stat);
		
		FriendEntity friend = new FriendEntity(null,emailfrom,stat);
		String emailto=friend.GetRequsts(emailfrom, stat);
		JSONObject object = new JSONObject();
		object.put("emailto", emailto);
		return object.toString();
	}


	
	
	
	
	@POST
	@Path("/AcceptFriendService")
	public String AccepttFriendService(@FormParam("emailto") String emailto,
			@FormParam("emailfrom") String emailfrom) 
	{
		System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+emailto);
		
		boolean check=false;
		FriendEntity friend = new FriendEntity(emailto, emailfrom,"");
		check =friend.AcceptFriends(emailto, emailfrom);
		System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
		
		if (check == true) {
		
		    object.put("Status", "OK");
			friend.savestatusFriend();
			
		}
		else 
		{
			object.put("Status", "Failed");
		}
        System.out.println(object.toString());

		return object.toString();

	
	
	}
	



}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
