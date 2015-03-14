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
public class UserController
{
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
		String serviceUrl = "http://socialnetworkmbh2015.appspot.com/rest/RegistrationService";
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
		String serviceUrl = "http://socialnetworkmbh2015.appspot.com/rest/LoginService";
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
		String serviceUrl = "http://socialnetworkmbh2015.appspot.com/rest/AddFriendService";
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
				String serviceUrl = "http://socialnetworkmbh2015.appspot.com/rest/ListReqestService";
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
		String serviceUrl = "http://socialnetworkmbh2015.appspot.com/rest/AcceptFriendService";
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
		String serviceUrl = "http://socialnetworkmbh2015.appspot.com/rest/RejectFriendService";
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
				String serviceUrl = "http://socialnetworkmbh2015.appspot.com/rest/ListFriendService";
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
	
	
	
}