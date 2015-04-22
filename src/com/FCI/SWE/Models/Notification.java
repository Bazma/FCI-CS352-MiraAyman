package com.FCI.SWE.Models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Notification {
	private String emailto;
	private String emailfrom;
	private String Message;
	private String TypeMessage;
	private String title; 
	
	

	
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetEmailto(String n)
	 {
		 this.emailto=n;
	 }
	 
	 /**
	  * function Setmail set mail to object user
	  * @param mail
	  */
	 public  void SetEmailFrom(String n)
	 {
		 this.emailfrom=n;
	 }
	 
	 public  void SetTypeMessage(String m) 
	 {
		this.TypeMessage=m;
	 }
	
	 /**
	  * function Setpass set pass to object user
	  * @param pass
	  */
	public  void SetMessage(String p) 
	{
		this.Message=p;
	}
	 
	 /**
	  * function Setpass set pass to object user
	  * @param pass
	  */
	public  void Settitle(String p) 
	{
		this.title=p;
	}
	 
	/**
	 * function getName get Name to object user
	 * @return uname
	 */
	public  String getEmailto()
	{
		return emailto;
	}
	
	/**
	 * function getName get Name to object user
	 * @return uname
	 */
	public  String getEmailFrom()
	{
		return emailfrom;
	}
	
	/**
	 * function getEmail get email to object user
	 * @return uemail
	 */
	public  String getMessage() 
	{
		return Message;
	}
	
	/**
	 * function getEmail get email to object user
	 * @return uemail
	 */
	public  String getTypeMessage() 
	{
		return TypeMessage;
	}
	
	/**
	 *function getPass get pass to object user
	 * @return upassword
	 */
	public String getTitle()
	{
		return title;
	}
	public Notification getNotificationRow(String json) throws org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		
			JSONObject object = (JSONObject) parser.parse(json);
			Notification obj= new Notification();
			obj.SetEmailto(object.get("emailto").toString());
			obj.SetEmailFrom( object.get("emailfrom").toString());
			obj.SetMessage(object.get("Message").toString());
			obj.SetTypeMessage(object.get("TypeMessage").toString());
			obj.Settitle(object.get("title").toString());
			
			//Notification.setId(Long.parseLong(object.get("id").toString()));
			return obj;
	}

	

}
