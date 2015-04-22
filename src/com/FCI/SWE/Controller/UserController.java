package com.FCI.SWE.Controller;
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
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.FriendEntity;
import com.FCI.SWE.Models.Notification;
import com.FCI.SWE.Models.NotificationEntity;
import com.FCI.SWE.Models.ObserveUser;
import com.FCI.SWE.Models.Postpram;
import com.FCI.SWE.Models.Subject;
import com.FCI.SWE.Models.UserEntity;
import com.FCI.SWE.Models.conversation;
import com.FCI.SWE.Models.user;
import com.google.cloud.sql.jdbc.Connection;

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
public class UserController
{
	public static int count;
	
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@GET
	@Path("/signup")
	public Response signUp() 
	{
		return Response.ok(new Viewable("/jsp/register")).build();
	}
	
	

	
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/HashTag")
	public Response HashTag() 
	{
		return Response.ok(new Viewable("/jsp/HashTag")).build();
	}
	
	
	
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/WritePost")
	public Response WritePost() 
	{
		return Response.ok(new Viewable("/jsp/CreatePost")).build();
	}
	
	
	

	
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/WritePagePost")
	public Response WritePagePost() 
	{
		return Response.ok(new Viewable("/jsp/PagePost")).build();
	}

	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/LikePage")
	public Response LikePage() 
	{
		return Response.ok(new Viewable("/jsp/LikePage")).build();
	}
	
	
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/Page")
	public Response Page() 
	{
		return Response.ok(new Viewable("/jsp/CreatePage")).build();
	}

//	/**
//	 * Action function to render Signup page, this function will be executed
//	 * using url like this /rest/signup
//	 * 
//	 * @return sign up page
//	 */
//	@GET
//	@Path("/ReplyMessage")
//	public Response ReplyMessage() 
//	{
//		return Response.ok(new Viewable("/jsp/ReplyMessage")).build();
//	}
//

	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/Chat")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response Chat()
	{
		return Response.ok(new Viewable("/jsp/WriteMessage")).build(); 
	}


	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/SendMessages")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response SendMessages()
	{
		return Response.ok(new Viewable("/jsp/Message")).build(); 
	}
	

	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/SendMessage")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response SendMessage()
	{
		return Response.ok(new Viewable("/jsp/Send Message")).build(); 
	}
	/**
	 * Action function to render home page of application, home page contains
	 * only sign up and login buttons
	 * 
	 * @return entry point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() 
	{
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}
	
	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login()
	{
		return Response.ok(new Viewable("/jsp/login")).build();
	}


	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,@FormParam("email") String email, @FormParam("password") String pass)
	{
		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "uname=" + uname + "&email=" + email	+ "&password=" + pass;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,@FormParam("password") String pass) 
	{
		String serviceUrl = "http://localhost:8888/rest/LoginService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "uname=" + uname + "&password=" + pass;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			UserEntity user = UserEntity.getUser(object.toJSONString());
			//System.out.println(object.toJSONString()+"log in ya 7agggggggg");
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home", map)).build();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
	
	
	/**
	 * Action function to render home page this function will be executed using
	 * url like this /rest/
	 * 
	 * @return home page
	 */
	@POST
	@Path("/")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response responselog()
	{
	    user.currentactive.NULLUser();
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	
	/**
	 * Action function to render addfriend page this function will be executed using
	 * url like this /rest/addfriend
	 * 
	 * @return  addfriend page
	 */
	@POST
	@Path("/addFriend")
	//@Produces(MediaType.TEXT_PLAIN)
	public Response responseadd()
	{
		return Response.ok(new Viewable("/jsp/addfriend")).build(); 
	}
	
	
	
	/**
	 * Action function to response to add request, This function will act as
	 * a controller part and it will calls AddFriendService to make
	 * add
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided friend status
	 * @return Status string
	 */
	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String responseaddfri(@FormParam("emailto") String emailto,@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat)
	{
		stat="0";
		emailfrom =user.currentactive.getEmail();
		//System.out.println("aho y ebne  "+user.currentactive.getEmail()+emailfrom+emailto+stat);
		String serviceUrl = "http://localhost:8888/rest/AddFriendService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&emailto=" + emailto+ "&emailfrom=" + emailfrom+ "&status=" + stat;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			//System.out.println("eh y 3am "+emailto + emailfrom + stat);
			if (object.get("Status").equals("OK"))
			{
				//System.out.println("d5l  mora mira ");
				return "THE Request has been Sent";
			}
		} 
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Failed";
	}

	
	/**
	 * Action function to response to get request. This function will act as a
	 * controller part, it will calls ListReqestService to check friend data and get
	 * friend from datastore
	 * 
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param emailto
	 *            provided friend status
	 * @return ListOfRequest view
	 */
	@POST
	@Path("/getRequest")
	@Produces("text/html")
	public Response getRequests(@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat)
	{
				stat="sendfrom";
				emailfrom =user.currentactive.getEmail();
				//System.out.println("aho y ebne  "+user.currentactive.getEmail()+emailfrom+stat);
				String serviceUrl = "http://localhost:8888/rest/ListReqestService";
				try {
					URL url = new URL(serviceUrl);
					String urlParameters =  "&emailfrom=" + emailfrom+ "&status=" + stat;
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setDoInput(true);
					connection.setInstanceFollowRedirects(false);
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(60000);  //60 Seconds
					connection.setReadTimeout(60000);  //60 Seconds
					connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
					OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
					writer.write(urlParameters);
					writer.flush();
					String line, retJson = "";
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = reader.readLine()) != null)
					{
						retJson += line;
					}
					writer.close();
					reader.close();
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(retJson);
					JSONObject object = (JSONObject) obj;
					//System.out.println("eh y 3am "+ emailfrom + stat);
					//System.out.println(object.get("emailto"));
					/*if (object.get("emailto").equals(null))
						{
				         	return null;
				        }*/	
					Map<String, String> map = new HashMap<String, String>();
					FriendEntity friend= new  FriendEntity("","","");
					//System.out.println(" null mesh fe request lololololol"+object.toJSONString());
					String mail= friend.GetRequsts(object.toJSONString());
					//System.out.println(" null mesh fe request yyyyyylololol"+object.toJSONString());
					//System.out.println(" null mesh fe request lolololololhgighihgihirjhotht"+mail);
					if (object.get("emailto").equals("notfound"))
					{			   
					  	return Response.ok(new Viewable("/jsp/Failed")).build();
			        }
					else
					{
					    map.put("emailto", mail);
					    //System.out.println(mail+"  hihwguhwrghigo  "+"emailto");
					    return Response.ok(new Viewable("/jsp/ListOfRequest", map)).build();			
					}		
				}
				catch (MalformedURLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (ParseException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * UserEntity user = new UserEntity(uname, email, pass);
				 * user.saveUser(); return uname;
				 */
			return null;
		}
	
	
	/**
	 * Action function to response to accept request, This function will act as
	 * a controller part and it will calls AcceptFriendService to make
	 * accept request
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @return Status string
	 */
	@POST
	@Path("/acceptRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String responseaddfri(@FormParam("emailto") String emailto,@FormParam("emailfrom") String emailfrom)
	{
		emailfrom = user.currentactive.getEmail();
		//emailto = ${it.emailto};	
		//System.out.println("aho y ebne  "+user.currentactive.getEmail()+emailfrom+emailto);	
		String serviceUrl = "http://localhost:8888/rest/AcceptFriendService";
		try 
		{
			URL url = new URL(serviceUrl);
			String urlParameters = "&emailto=" + emailto+ "&emailfrom=" + emailfrom;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			//System.out.println("eh y 3am "+emailto + emailfrom );
			if (object.get("Status").equals("OK"))
			{
				System.out.println("d5l  mora mira ");
				return "you are now friend with"+emailto;
			}
		} 
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "Failed";
	}


			
	/**
	 * Action function to response to reject request, This function will act as
	 * a controller part and it will calls RejectFriendService to make
	 * reject request
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @return Status string
	 */
	@POST
	@Path("/rejectRequest")
	@Produces(MediaType.TEXT_PLAIN)
	public String responsedeletefri(@FormParam("emailto") String emailto,@FormParam("emailfrom") String emailfrom)
	{
    	emailfrom = user.currentactive.getEmail();	
		//System.out.println("aho y ebne  "+user.currentactive.getEmail()+emailfrom+emailto);		
		String serviceUrl = "http://localhost:8888/rest/RejectFriendService";
		try
		{
			URL url = new URL(serviceUrl);
			String urlParameters = "&emailto=" + emailto+ "&emailfrom=" + emailfrom;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			//System.out.println("eh y 3am "+emailto + emailfrom );	
			if (object.get("Status").equals("OK"))
			{
				//System.out.println("d5l  mora mira ");
				return "you are  rejected with "+emailto;
			}
		} 
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed";
	}


	/**
	 * Action function to response to get request. This function will act as a
	 * controller part, it will calls ListFriendService to check friend data and get
	 * friend from datastore
	 * 
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param emailto
	 *            provided friend status
	 * @return ListOfFriend view
	 */
	@POST
	@Path("/getfriends")
	@Produces("text/html")
	public Response getfriendss(@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat)
	{
				stat="1";
				emailfrom =user.currentactive.getEmail();
				//System.out.println("aho y ebne  "+user.currentactive.getEmail()+emailfrom+stat);		
				String serviceUrl = "http://localhost:8888/rest/ListFriendService";
				try 
				{
					URL url = new URL(serviceUrl);
					String urlParameters =  "&emailfrom=" + emailfrom+ "&status=" + stat;
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setDoInput(true);
					connection.setInstanceFollowRedirects(false);
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(60000);  //60 Seconds
					connection.setReadTimeout(60000);  //60 Seconds
					connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
					OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
					writer.write(urlParameters);
					writer.flush();
					String line, retJson = "";
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = reader.readLine()) != null)
					{
						retJson += line;
					}
					writer.close();
					reader.close();
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(retJson);
					JSONObject object = (JSONObject) obj;
					//System.out.println("eh y 3am "+ emailfrom + stat);	
					Map<String, String> map = new HashMap<String, String>();
					FriendEntity friend= new  FriendEntity("","","");
					//System.out.println(" null mesh fe request lololololol"+object.toJSONString());
					String mail= friend.GetRequsts(object.toJSONString());
					//System.out.println(" null mesh fe request yyyyyylololol"+object.toJSONString());
					//System.out.println(" null mesh fe request lolololololhgighihgihirjhotht"+mail);
					if (object.get("emailto").equals("notfound"))
					{
					  	return Response.ok(new Viewable("/jsp/Failed")).build();
			        }
					else
					{
					    map.put("emailto", mail);
					    //System.out.println(mail+"  hihwguhwrghigo  "+"emailto");
					    return Response.ok(new Viewable("/jsp/ListOfFriend", map)).build();
					}	
				}
				catch (MalformedURLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * UserEntity user = new UserEntity(uname, email, pass);
				 * user.saveUser(); return uname;
				 */
			return null;
	}
	
	
	
	
	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/WriteMessage")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("Title") String title,@FormParam("email") String email)
	{
		email =user.currentactive.getEmail();
		String serviceUrl = "http://localhost:8888/rest/WriteMessagesService";
		try {
			
			URL url = new URL(serviceUrl);
			String urlParameters = "&Title=" + title + "&email=" + email	;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Created Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
	

	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/AddChat")
	@Produces(MediaType.TEXT_PLAIN)
	public String AddChat(@FormParam("Title") String uname,@FormParam("EmailFriend") String email)
	{
		String serviceUrl = "http://localhost:8888/rest/AddChatService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&Title=" + uname + "&EmailFriend=" + email	;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Added Friend Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}



	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/Notify")
	@Produces("text/html")
	public Response Notify(@FormParam("Title") String title,@FormParam("msg") String msg,@FormParam("mail") String emailuser) 
	{

		 
		emailuser= user.currentactive.getEmail();
		String serviceUrl = "http://localhost:8888/rest/NotifyService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "Title=" + title + "&msg=" + msg;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			//Map<String, String> map = new HashMap<String, String>();
			//UserEntity user = UserEntity.getUser(object.toJSONString());
			//System.out.println(object.toJSONString()+"log in ya 7agggggggg");
			//map.put("name", user.getName());
		//	map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home")).build();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
	
 public void AttachNotification(ArrayList<ObserveUser>Emails)
 {
	 Subject object = new Subject();
	 object.attachtolist(Emails);
	 for (int i=0;i<Emails.size();i++)
		{
			System.out.println("ssss "+Emails.get(i).getemail());
			//list.get(i).update();
		}
		
	 
 }
	

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
 	@POST
	@Path("/homeNotification")
	@Produces("text/html")
	public Response Notifiation(@FormParam("email") String email) 
	{
		email=user.currentactive.getEmail();
		/*ArrayList<Notification>Notification1= new ArrayList();
		conversation o=new conversation();
		Notification1=o.NotifyAll(email);
		System.out.print("ssssssssssssssssssssssssssssssssssssssssssssss");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0;i<Notification1.size();i++)
		{
			//System.out.print("ssssssssssssssssssssssssssssssssssssssssssssss");
			
		  map.put("notify", "There is notifiy of type "+Notification1.get(i).getTypeMessage()+" from "+Notification1.get(i).getEmailto()+" message = "+Notification1.get(i).getMessage());
		 
			System.out.println(map+"ssssssssssssssssssssssssssssssssssssssssssssss");
		}
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssssss");
		*/
		String serviceUrl = "http://localhost:8888/rest/NotifiatioService";
		try 
		{
			URL url = new URL(serviceUrl);
			String urlParameters = "&email=" + email ;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONArray object = (JSONArray) obj;
			Map<String, Vector<Notification>> map = new HashMap<String, Vector<Notification>>();
			Vector<Notification> data =new Vector<Notification>();
			for (int i=0 ;i<object.size();i++)
			{
				JSONObject mapJson = (JSONObject) object.get(i);
				Notification p =new Notification();
				
				p.SetEmailto(mapJson.get("emailto").toString());
				p.SetMessage(mapJson.get("msg").toString());
				p.SetTypeMessage(mapJson.get("type").toString());
				data.add(p);
				System.out.print(mapJson.get("msg"));
			}
			map.put("data",data);
			
			return Response.ok(new Viewable("/jsp/Notification", map)).build();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
			
 	@GET
 	@Path("/ReplyMessage")
    @Produces("text/html")
	public Response ReplyMessage() 
	{
		String email=user.currentactive.getEmail();
		/*ArrayList<Notification>Notification1= new ArrayList();
		conversation o=new conversation();
		Notification1=o.NotifyAll(email);
		System.out.print("ssssssssssssssssssssssssssssssssssssssssssssss");
		*/
		System.out.println("ha ss");
		String serviceUrl = "http://localhost:8888/rest/NotifiationService";
		try {
			URL url = new URL(serviceUrl);
			//String urlParameters = "&email=" + email ;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	//		writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONArray object = (JSONArray) obj;
			Map<String, Vector<String>> map = new HashMap<String, Vector<String>>();
			Vector<String> data =new Vector<String>();
			for (int i=0 ;i<object.size()-1;i++)
			{
				JSONObject mapJson = (JSONObject) object.get(i);
				
				String x=mapJson.get("msg").toString();
				data.add(x);
				System.out.print(mapJson.get("msg"));
			}
			JSONObject mapJson = (JSONObject) object.get(object.size()-1);
			String x=mapJson.get("j").toString();
			data.add(x);
			
			for (int i=0 ;i<data.size();i++)
			{
				System.out.println("a y 3am controller aho "+data.get(i));
				
			}
			map.put("data",data);
			
			return Response.ok(new Viewable("/jsp/ReplyMessage", map)).build();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
 

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/reply")
	@Produces("text/html")
	public Response Notifyreply(@FormParam("Title") String title,@FormParam("msg") String msg,@FormParam("mail") String emailuser) 
	{

		 
		emailuser= user.currentactive.getEmail();
		String serviceUrl = "http://localhost:8888/rest/replyService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "Title=" + title + "&msg=" + msg;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			//Map<String, String> map = new HashMap<String, String>();
			//UserEntity user = UserEntity.getUser(object.toJSONString());
			//System.out.println(object.toJSONString()+"log in ya 7agggggggg");
			//map.put("name", user.getName());
		//	map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home")).build();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
	



	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/CreatePost")
	@Produces(MediaType.TEXT_PLAIN)
	public String CreatePost(@FormParam("Content") String Content,@FormParam("Tag") String Tag,@FormParam("email") String email,@FormParam("Feeling") String Feeling,@FormParam("privacy") String privacy,@FormParam("Hash") String Hash)
	{
		Tag=Tag;
		System.out.println("tag in controller "+Tag);
		String serviceUrl = "http://localhost:8888/rest/CreatePostService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&Content=" + Content + "&Tag=" + Tag+"&email="+email+"&Feeling="+Feeling+"&privacy="+privacy+"&Hash="+Hash;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Post has been published Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}

	
	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/CreatePage")
	@Produces(MediaType.TEXT_PLAIN)
	public String CreatePage(@FormParam("Name") String Name,@FormParam("Type") String Type,@FormParam("email") String email,@FormParam("category") String category)
	{
		String serviceUrl = "http://localhost:8888/rest/CreatePageService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&Name=" + Name+"&Type="+Type+"&email="+email+"&category="+category;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Page has been published Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}


	
	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/Like")
	@Produces(MediaType.TEXT_PLAIN)
	public String Like(@FormParam("Name") String Name,@FormParam("email") String email)
	{
		String serviceUrl = "http://localhost:8888/rest/LikeService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&Name=" + Name+"&email="+email;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Like Page has been done Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
	

	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/CreatePagePost")
	@Produces(MediaType.TEXT_PLAIN)
	public String CreatePagePost(@FormParam("Name") String Name,@FormParam("email") String email,@FormParam("Content") String Content,@FormParam("privacy") String privacy)
	{
		String serviceUrl = "http://localhost:8888/rest/CreatePagePostService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&Name=" + Name+"&email="+email+"&Content="+Content+"&privacy="+privacy;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Post Page has been done Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
 	@POST
 	@Path("/TimeLine")
    @Produces("text/html")
	public Response TimeLine() 
	{
		
		System.out.println("ha ss");
		String serviceUrl = "http://localhost:8888/rest/TimeLineService";
		
		try {
			URL url = new URL(serviceUrl);
			//String urlParameters = "&email=" + email ;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	//		writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONArray object = (JSONArray) obj;
			Map<String, Vector<Postpram>> map = new HashMap<String, Vector<Postpram>>();
			Vector<Postpram> data =new Vector<Postpram>();
			for (int i=0 ;i<object.size();i++){
				JSONObject mapJson = (JSONObject) object.get(i);
				Postpram p =new Postpram();
				p.SetContent(mapJson.get("content").toString());
				p.SetEmail(mapJson.get("email").toString());
				p.SetID(mapJson.get("id").toString());
				p.SetSeen(mapJson.get("seen").toString());
				p.SetHash(mapJson.get("hash").toString());
				data.add(p);
				System.out.print(mapJson.get("content"));
			}
			map.put("data",data);
			
							
//				String s = null;
//				//JSONParser parser = new JSONParser();
//				try {
//					JSONArray array1 = (JSONArray)parser.parse(s);
//					for(int i = 0;i<array1.size();i++){
//						JSONObject obj1 = (JSONObject)array1.get(i);
//					
//						/*form post object
//						 * add this object to posts vector
//						 */
//					}
//					
//					/*
//					 * put vector in map with specific key ("posts",vector)
//					 */
//					
					
					
//					
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				return array.toJSONString();
//			}
//
			/*
			if (object.get("post").equals(""))
				return null;
			else {
				
				Map<String, String> map = new HashMap<String, String>();
				String x=object.get("post").toString() ;
				x= x.replace("~"," <br> ");
				x= x.replace("$"," <br> ------------------------------------------------------------- <br> ");
	
			    map.put("post",x);
					
				return Response.ok(new Viewable("/jsp/TimeLine", map)).build();
			}*/
			return  Response.ok(new Viewable("/jsp/TimeLine", map)).build();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;
	}
 	
 	
	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/likepost")
	@Produces(MediaType.TEXT_PLAIN)
	public String likepost(@FormParam("ID") String Name)
	{
		System.out.print("y beh "+Name);
		String serviceUrl = "http://localhost:8888/rest/likepostService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&ID=" + Name;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) 
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Post has been liked Successfully";
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
	
	

 	@POST
 	@Path("/ViewHashTag")
    @Produces("text/html")
	public Response ViewHashTag(@FormParam("HashTag") String HashTag) throws ParseException 
	{
 		System.out.println("ana lk 3la toooooooooooooooooolf el controller "+HashTag);
		
		
		System.out.println("ha ss");
		String serviceUrl = "http://localhost:8888/rest/ViewHashTagService";
		try {
			URL url = new URL(serviceUrl);
			String urlParameters = "&HashTag=" + HashTag ;
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(60000);  //60 Seconds
			connection.setReadTimeout(60000);  //60 Seconds
			
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, retJson = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				retJson += line;
			}
			writer.close();
			reader.close();
			
			
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(retJson);
					JSONArray object = (JSONArray) obj;
					Map<String, Vector<Postpram>> map = new HashMap<String, Vector<Postpram>>();
					Vector<Postpram> data =new Vector<Postpram>();
					for (int i=0 ;i<object.size();i++){
						JSONObject mapJson = (JSONObject) object.get(i);
						Postpram p =new Postpram();
						p.SetEmail(mapJson.get("email").toString());
						p.SetContent(mapJson.get("content").toString());
						p.SetHash(mapJson.get("hash").toString());
						p.SetID(mapJson.get("id").toString());
						//p.Settitle(mapJson.get("m").toString());
						
						data.add(p);
   
						System.out.println("check 2"+p.gettitle());
						
						//System.out.println("check "+mapJson.get("m"));
						System.out.print(mapJson.get("content"));
					//	System.out.println("check "+data.get(i).getcontent());
					}
				   count=object.size();
				    map.put("data",data);
					
					return Response.ok(new Viewable("/jsp/Show",map)).build();
				}
				catch (MalformedURLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		/*		catch (ParseException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				return null;
			
	
	
	}		 
}