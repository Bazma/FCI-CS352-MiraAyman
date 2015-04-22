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
<<<<<<< HEAD
import java.util.StringTokenizer;
=======
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
<<<<<<< HEAD
import org.json.simple.JSONArray;
=======
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

<<<<<<< HEAD
import com.FCI.SWE.Controller.UserController;
import com.FCI.SWE.Models.Command;
import com.FCI.SWE.Models.ConversationEntity;
import com.FCI.SWE.Models.FriendEntity;
import com.FCI.SWE.Models.LikePageEntity;
import com.FCI.SWE.Models.Message;
import com.FCI.SWE.Models.MessageEntity;
import com.FCI.SWE.Models.Notification;
import com.FCI.SWE.Models.NotificationEntity;
import com.FCI.SWE.Models.ObserveUser;
import com.FCI.SWE.Models.PageEntity;
import com.FCI.SWE.Models.PagePost;
import com.FCI.SWE.Models.PostBuilder;
import com.FCI.SWE.Models.PostEntity;
import com.FCI.SWE.Models.Postpram;
import com.FCI.SWE.Models.Subject;
import com.FCI.SWE.Models.UserEntity;
import com.FCI.SWE.Models.UserPost;
import com.FCI.SWE.Models.conversation;
import com.FCI.SWE.Models.custompost;
import com.FCI.SWE.Models.privacypost;
import com.FCI.SWE.Models.privatepost;
import com.FCI.SWE.Models.publicpost;
=======
import com.FCI.SWE.Models.FriendEntity;
import com.FCI.SWE.Models.UserEntity;
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352
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
public class Service
{
	

<<<<<<< HEAD
	
=======
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352
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
	public String registrationService(@FormParam("uname") String uname,@FormParam("email") String email, @FormParam("password") String pass)
	{
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
		if (user == null) 
		{
			object.put("Status", "Failed");
		} 
		else 
		{
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}
		return object.toString();
	}
	
	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/AddFriendService")
	public String AddFriendService(@FormParam("emailto") String emailto,@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat) 
	{
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+emailto+stat);
		boolean check=false;
<<<<<<< HEAD
		NotificationEntity obj = new NotificationEntity("com.FCI.SWE.Models.SendRequest","","");
=======
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352
		FriendEntity friend = new FriendEntity(emailto, emailfrom,stat);
		check =friend.getFriend(emailto, emailfrom, stat);
		System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
		if (check == true) 
		{
		    object.put("Status", "OK");
			friend.saveFriend(stat);
<<<<<<< HEAD
			obj.saveNotificationRequest(emailto,emailfrom);
=======
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352
		}
		else 
		{
			object.put("Status", "Failed");
		}
        System.out.println(object.toString());
		return object.toString();
	}
	
	

<<<<<<< HEAD
	
	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/WriteMessagesService")
	public String WriteMessagesService(@FormParam("Title") String title,@FormParam("email") String email) 
	{
		System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		boolean check=false,check1=false;
		ConversationEntity con = new ConversationEntity(title);
		check =con.getConversations(title);
		System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
		if (check == true) 
		{
		    object.put("Status", "OK");
			con.saveconversation();
			System.out.println(email+"    id "+con.Getonversatons(title));
			MessageEntity mess =new MessageEntity(email,con.Getonversatons(title));
			check1=mess.getMessage(email,con.Getonversatons(title));
			if (check1==true)
			{
			mess.saveMessage();
			}
		}
		else 
		{
			object.put("Status", "Failed");
		}
        System.out.println(object.toString());
		return object.toString();
	}
=======
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352
	/**
	 * ListReqestService Rest service, this service will be called to make
	 * list request. This function will store friend data in data store
	 * 
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */	
	@POST
	@Path("/ListReqestService")
	public String ListrequestsService(@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat) 
	{
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+stat);
		FriendEntity friend = new FriendEntity(null,emailfrom,stat);
		String emailto=friend.GetRequsts(emailfrom, stat);
		JSONObject object = new JSONObject();
		object.put("emailto", emailto);
		return object.toString();
	}
	

	/**
	 * AcceptFriendService Rest service, this service will be called to make
	 * accept friend. This function will store friend data in data store
	 * 
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param emailto
	 *            provided emailto
	 * @return Status json
	 */
	@POST
	@Path("/AcceptFriendService")
	public String AccepttFriendService(@FormParam("emailto") String emailto,@FormParam("emailfrom") String emailfrom) 
	{
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+emailto);
		boolean check=false;
		FriendEntity friend = new FriendEntity(emailto, emailfrom,"");
		check =friend.AcceptFriends(emailto, emailfrom);
		//System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
		if (check == true) 
		{
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
	
	
	/**
	 * rejecttFriendService Rest service, this service will be called to make
	 * reject friend. This function will store friend data in data store
	 * 
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param emailto
	 *            provided emailto
	 * @return Status json
	 */
	@POST
	@Path("/RejectFriendService")
	public String rejectFriendService(@FormParam("emailto") String emailto,@FormParam("emailfrom") String emailfrom) 
	{
	//	System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+emailto);
		boolean check=false;
		FriendEntity friend = new FriendEntity(emailto, emailfrom,"");
		check =friend.AcceptFriends(emailto, emailfrom);
		JSONObject object = new JSONObject();
		
		if (check == true)
		{
		    object.put("Status", "OK");
			friend.deletstatusFriend();
		}
		else 
		{
			object.put("Status", "Failed");
		}
       // System.out.println(object.toString());
		return object.toString();
	}
	
	@POST
	@Path("/ListFriendService")
	public String ListfriendsService(@FormParam("emailfrom") String emailfrom,@FormParam("status") String stat) 
	{
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+emailfrom+stat);
		FriendEntity friend = new FriendEntity(null,emailfrom,stat);
		String emailto=friend.GetRequsts(emailfrom, stat);
		JSONObject object = new JSONObject();
		object.put("emailto", emailto);
		return object.toString();
	}	

<<<<<<< HEAD
	
	

	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/AddChatService")
	public String AddChatService(@FormParam("Title") String title,@FormParam("EmailFriend") String email) 
	{
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		boolean check=false,check1=false;
	    System.out.println("hhh email"+email);
		ConversationEntity con = new ConversationEntity(title);
		check =con.getConversations(title);
		System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
		if (check == false) 
		{
		    object.put("Status", "OK");
		//	con.saveconversation();
		    System.out.println("hhh email"+email);
			MessageEntity mess =new MessageEntity(email,con.Getonversatons(title));
			check1=mess.getMessage(email,con.Getonversatons(title));
			if (check1==true)
			{
			mess.saveMessage();
			}
			else 
			{object.put("Status", "Failed");}
		}
		else 
		{
			object.put("Status", "Failed");
		}
        System.out.println(object.toString());
		return object.toString();
	}
	
	

	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/NotifyService")
	public String NotifyService(@FormParam("Title") String title,@FormParam("msg") String msg,@FormParam("mail") String emailuser) 
	{
		ArrayList<String>Id= new ArrayList();
		ArrayList<ObserveUser>emails= new ArrayList();
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		//boolean check=false,check1=false;
	    //System.out.println("hhh email"+email);
		ConversationEntity con = new ConversationEntity(title);
		Id=con.GetIDS(title);
		for (int i=0;i<Id.size();i++)
		{
		 System.out.println("Id = "+Id.get(i));	
		}
		MessageEntity mess =new MessageEntity("","");
		emails=mess.GetEmails(Id,emailuser);
		for (int i=0;i<emails.size();i++)
		{
		 System.out.println("email = "+emails.get(i).getemail());	
		}
		UserController ob = new UserController();
		ob.AttachNotification(emails);
		conversation o=new conversation();
		o.Notify(emails,msg,title);
	//	System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
	    object.put("Status", "OK");
		//	con.saveconversation();
    	System.out.println(object.toString());
		return object.toString();
	}
	
	

	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/replyService")
	public String replyService(@FormParam("Title") String title,@FormParam("msg") String msg,@FormParam("mail") String emailuser) 
	{
		ArrayList<String>Id= new ArrayList();
		ArrayList<ObserveUser>emails= new ArrayList();
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		//boolean check=false,check1=false;
	    //System.out.println("hhh email"+email);
		ConversationEntity con = new ConversationEntity(title);
		Id=con.GetIDS(title);
		for (int i=0;i<Id.size();i++)
		{
		 System.out.println("Id = "+Id.get(i));	
		}
		MessageEntity mess =new MessageEntity("","");
		emails=mess.GetEmails(Id,emailuser);
		for (int i=0;i<emails.size();i++)
		{
		 System.out.println("email = "+emails.get(i).getemail());	
		}
		UserController ob = new UserController();
		ob.AttachNotification(emails);
		conversation o=new conversation();
		o.Notify(emails,msg,title);
	//	System.out.println("bbbbbbbbbbb habla ="+check);
		JSONObject object = new JSONObject();
	    object.put("Status", "OK");
		//	con.saveconversation();
    	System.out.println(object.toString());
		return object.toString();
	}
	
	

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@POST
	@Path("/NotifiatioService")
	public String NotifiatinService(@FormParam("email") String email) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		ArrayList<Notification>Notification1= new ArrayList();
		conversation o=new conversation();
		Notification1=o.NotifyAll(email);
		System.out.print("ssssssssssssssssssssssssssssssssssssssssssssss");
		JSONArray array = new JSONArray();		
		for (int i = 0;i<Notification1.size();i++)
		{
			JSONObject obj = new JSONObject();
			obj.put("type",Notification1.get(i).getTypeMessage());
			obj.put("emailto", Notification1.get(i).getEmailto());
			
			 obj.put("msg",Notification1.get(i).getMessage());
			 array.add(obj);
		}
		return array.toJSONString();
	}
	
	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@POST
	@Path("/NotifiationService")
	public String NotifiatiService() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		String messages="";
		String email=user.currentactive.getEmail();
		System.out.print("ana hna f el service ");
		NotificationEntity o = new NotificationEntity("","","");
		
		ArrayList<Notification>Notification1= new ArrayList();
		ArrayList<Notification>Messages= new ArrayList();
		Notification1=o.GetNotifications(email);
		ArrayList<String>Messagecon= new ArrayList();
		
		/*conversation o=new conversation();
		Notification1=o.NotifyAll(email);
		*/
		for (int i=0;i<Notification1.size();i++)
		{
			System.out.print("all el msg "+Notification1.get(i).getMessage());
			Command C =(Command) Class.forName(Notification1.get(i).getTypeMessage()).newInstance();
			
			Messages=C.execute(Notification1.get(i));
		//	System.out.println(C.execute(Notification1.get(i)).get(0));
			break;
		}
		String title="";
		JSONArray array = new JSONArray();		
		
		for(int i=0;i<Messages.size();i++)
		{
        //    System.out.print(Messages.get(i)+ " a ba2a :(  ");
            Messagecon.add("this message from  : "+Messages.get(i).getEmailFrom()+"  send  "+Messages.get(i).getMessage());
		}
		
		title=Messages.get(0).getTitle();
		for (int i=0;i<Messagecon.size();i++)
		{
			JSONObject obj = new JSONObject();
			obj.put("msg",Messagecon.get(i));
			array.add(obj);
		}
		JSONObject obj = new JSONObject();
		obj.put("j",title);
		array.add(obj);
	
	
	return array.toJSONString();
	}
	
	

	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/CreatePostService")
	public String CreatePostService(@FormParam("Content") String Content,@FormParam("Tag") String Tag,@FormParam("email") String email,@FormParam("Feeling") String Feeling,@FormParam("privacy") String privacy,@FormParam("Hash") String Hash) 
	{   email=user.currentactive.getEmail();
	JSONObject object = new JSONObject();
	
		if (privacy.equals("custom"))
		{
			ArrayList<String>Tags=new ArrayList();
			String text=Tag;
			StringTokenizer sub = new StringTokenizer(text, "@");
            while (sub.hasMoreElements()) 
            {
                String  temp =sub.nextToken();
                Tags.add(temp); 
            }
            for (int i=0;i<Tags.size();i++)
			{
				
	            PostBuilder post = new UserPost();
				PostEntity pos = new PostEntity();
				System.out.println("tag in server "+Tags.get(i)+" email "+email);
				
				if (Tags.get(i).equals("")||Tags.get(i).equals(null))
				{
					pos.SetEmailto(email);
				}
				
				else
				{ 
					pos.SetEmailto(Tags.get(i));
				}
				System.out.println("tag in server "+pos.getemailto()+" email "+email);
				boolean check=pos.CheckTag(pos.getemailto(),email);
				System.out.println(" check "+check);
			
				pos.SetContent(Content);   
			    pos.Setnumberoflikes("0");
			    pos.SetOwner(email);
			    pos.SetFeeling(Feeling);
			    pos.Settitle("");
			    pos.SetTypePost("User");
			    pos.SetHashTag(Hash);
			    post.createpost(pos);
				if (check==true)
				{
				//pos.savePost();
					post.writepost(pos,privacy);
				object.put("Status", "OK");
				}
				else 
				{
					object.put("Status", "Failed");
				}
				
			}
		}
		else 
		{
			PostBuilder post = new UserPost();
			//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
			PostEntity pos = new PostEntity();
			System.out.println("tag in server "+Tag+" email "+email);
			if (Tag.equals("")||Tag.equals(null))
			{
				pos.SetEmailto(email);
			}
			
			else
			{ 
				pos.SetEmailto(Tag);
			}
			System.out.println("tag in server "+pos.getemailto()+" email "+email);
			boolean check=pos.CheckTag(pos.getemailto(),email);
			System.out.println(" check "+check);
			pos.SetContent(Content);   
		    pos.Setnumberoflikes("0");
		    pos.SetOwner(email);
		    pos.SetFeeling(Feeling);
		    pos.Settitle("");
		    pos.SetTypePost("User");
		    pos.SetHashTag(Hash);
		   // pos.SetHashTag("1");
			post.createpost(pos);
			if (check==true)
			{
			//pos.savePost();
				post.writepost(pos,privacy);
			object.put("Status", "OK");
			}
			else 
			{
				object.put("Status", "Failed");
			}
			
		}
	
		return object.toString();
	}
	
	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/CreatePagePostService")
	public String CreatePagePostService(@FormParam("Name") String Name,@FormParam("email") String email,@FormParam("Content") String Content,@FormParam("privacy") String privacy) 
	{
		email=user.currentactive.getEmail();
		PostBuilder post = new PagePost();
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		PostEntity pos = new PostEntity();
		//System.out.println("tag in server "+Tag+" email "+email);
	/*	if (Tag.equals(""))
		{
			pos.SetEmailto(email);
		}
		
		else
		{ 
			pos.SetEmailto(Tag);
		}*/
		//System.out.println("tag in server "+pos.getemailto()+" email "+email);
		boolean check=pos.CheckName(Name,email);
		//System.out.println(" check "+check);
		pos.SetContent(Content);   
	    pos.Setnumberoflikes("0");
	    pos.SetOwner(email);
	    pos.Settitle(Name);
	    pos.SetFeeling("");
		pos.SetEmailto("");
	    pos.SetTypePost("Page");
	    post.createpost(pos);
		
	    JSONObject object = new JSONObject();
		if (check==true)
		{
		//pos.savePost();
		post.writepost(pos,privacy);
		object.put("Status", "OK");
		}
		else 
		{
			object.put("Status", "Failed");
		}
		return object.toString();
	}
	
	
	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/CreatePageService")
	public String CreatePageService(@FormParam("Name") String Name,@FormParam("Type") String Type,@FormParam("email") String email,@FormParam("category") String category) 
	{
		email=user.currentactive.getEmail();
		
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		PageEntity page = new PageEntity();

		/*if (Tag.equals(null))
		{ 
			pos.SetEmailto(email);
		}
		else
		{ 
			pos.SetEmailto(Tag);
		}
		*/
		boolean check=page.CheckPage(Name);
	    page.SetCategory(category);   
	    page.Setlike("0");
	    page.SetOwner(email);
	    page.SetType(Type);
	    page.SetName(Name);
		JSONObject object = new JSONObject();
		if (check==true)
		{
		page.savePage();
		object.put("Status", "OK");
		}
		else 
		{
			object.put("Status", "Failed");
		}
		return object.toString();
	}
	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/LikeService")
	public String LikeService(@FormParam("Name") String Name,@FormParam("email") String email) 
	{
		email=user.currentactive.getEmail();
		
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		LikePageEntity like = new LikePageEntity();

		/*if (Tag.equals(null))
		{ 
			pos.SetEmailto(email);
		}
		else
		{ 
			pos.SetEmailto(Tag);
		}
		*/
		boolean check=like.CheckLikePage(Name);
	   // page.SetCategory(category);   
	    like.Setlike("1");
	    like.Setemail(email);
	    like.SetName(Name);
		JSONObject object = new JSONObject();
		if (check==true)
		{
		like.saveLikePage();
		object.put("Status", "OK");
		}
		else 
		{
			object.put("Status", "Failed");
		}
		return object.toString();
	}
	

	
		
	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	
	@POST
	@Path("/TimeLineService")
	public String TimeLineService() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		String messages="";
		String email=user.currentactive.getEmail();
		System.out.print("ana hna f el service TIMMELINE");
		JSONArray array = new JSONArray();
		
		
		PostEntity po= new PostEntity ();
		ArrayList<Postpram>Posts= new ArrayList();
	    Posts=po.ViewPublic(email);	
	     po.KONumberOfHashtag();
	     System.out.print("ana hna f el service "+Posts);
			
	 	for (int i=0;i<Posts.size();i++)
		{
			JSONObject obj = new JSONObject();
			obj.put("id",Posts.get(i).getID());
			obj.put("content", Posts.get(i).getcontent());
			if(Posts.get(i).gettitle().equals("null")||Posts.get(i).gettitle().equals(""))
			{
			 obj.put("email",Posts.get(i).getemail());
			 
			}
			else 
			{

		    obj.put("email",Posts.get(i).gettitle());
		    
			}
			obj.put("seen",Posts.get(i).getSeen());
			obj.put("hash",Posts.get(i).getHash());
			
		    
			
			//obj.put("content", Posts.get(i).getcontent());
			array.add(obj);
		}
		//String title="";
//		for(int i=0;i<Posts.size();i++)
//		{
//            System.out.print(Posts.get(i)+ " a ba2a :(  ");
//			title+=Posts.get(i)+"$";
//		}
//		//messages=messages+title+"#";
		//JSONObject object = new JSONObject();
		//System.out.print(title);
		//object.put("post", title);
		return array.toJSONString();
		//return object.toString();
		//return messages;
	}
	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/likepostService")
	public String LikeService(@FormParam("ID") String Name) 
	{
		System.out.print("y beh 2 "+Name);
		//System.out.println("aho y bntyyy  "+user.currentactive.getEmail()+" title "+title);
		
//		LikePageEntity like = new LikePageEntity();
		PostEntity o = new PostEntity();
		boolean check =o.Likepost(Name);
		/*if (Tag.equals(null))
		{ 
			pos.SetEmailto(email);
		}
		else
		{ 
			pos.SetEmailto(Tag);
		}
		*/
		JSONObject object = new JSONObject();
		if (check==true)
		{
		  object.put("Status", "OK");
		}
		else 
		{
			object.put("Status", "Failed");
		}
		return object.toString();
	}

	
	
	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param emailto
	 *            provided friend emailto
	 * @param emailfrom
	 *            provided friend emailfrom
	 * @param status
	 *            provided status
	 * @return Status json
	 */
	@POST
	@Path("/ViewHashTagService")
	public String ViewHashTagService(@FormParam("HashTag") String HashTag) 
	{
		System.out.println("ana lk 3la tooooooooooooooooool f e ;service  "+HashTag);
		
		JSONArray array = new JSONArray();
		PostEntity po= new PostEntity ();
		ArrayList<Postpram>Posts= new ArrayList();
	    Posts=po.CheckHashTag(HashTag);	
	    int l= po.Number(HashTag);
	   
	    System.out.println("L= "+l);
	 	for (int i=0;i<Posts.size();i++)
		{System.out.println("a y 3am d5al aho ");
			JSONObject obj = new JSONObject();
			obj.put("id",Posts.get(i).getID());
			obj.put("content", Posts.get(i).getcontent());
			obj.put("hash",Posts.get(i).getHash());
			obj.put("email", Posts.get(i).getemail());
			//obj.put("m",""+l);
			System.out.println("L= "+l);
		    array.add(obj);
		}
//	 	JSONObject obj = new JSONObject();
	 	return array.toJSONString();
	}
}	
=======
}	
>>>>>>> 267eafb6cfdccb254acfd8b39ceaaacfc1567352
