package com.FCI.SWE.Models;

public class user
{
	 private String uname;
	 private String upassword;
	 private String uemail;
	 public static user currentactive = new user();
	 
	 private user()
	 {
		 this.uname=null;
		 this.upassword=null;
		 this.uemail=null;
	 }
	  
	 public static void SetName(String n)
	 {
		 currentactive.uname=n;
	 }
	 
	 public static void SetEmail(String mail) 
	 {
		currentactive.uemail=mail;
	 }
	
	public static void SetPass(String pass) 
	{
		currentactive.upassword=pass;
	}
	 
	public static String getName()
	{
		return currentactive.uname;
	}
	
	public static String getEmail() 
	{
		return currentactive.uemail;
	}
	
	public static String getPass()
	{
		return currentactive.upassword;
	}
		
	public static user getcuractive()
	{
		return currentactive;
	}
	
	public static void NULLUser() 
	{
		currentactive.uemail=null;
		currentactive.uname=null;
		currentactive.upassword=null;
	}

	
}
