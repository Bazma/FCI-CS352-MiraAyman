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
	 
	 
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public static void SetName(String n)
	 {
		 currentactive.uname=n;
	 }
	 
	 /**
	  * function Setmail set mail to object user
	  * @param mail
	  */
	 public static void SetEmail(String mail) 
	 {
		currentactive.uemail=mail;
	 }
	
	 /**
	  * function Setpass set pass to object user
	  * @param pass
	  */
	public static void SetPass(String pass) 
	{
		currentactive.upassword=pass;
	}
	 
	/**
	 * function getName get Name to object user
	 * @return uname
	 */
	public static String getName()
	{
		return currentactive.uname;
	}
	
	/**
	 * function getEmail get email to object user
	 * @return uemail
	 */
	public static String getEmail() 
	{
		return currentactive.uemail;
	}
	
	/**
	 *function getPass get pass to object user
	 * @return upassword
	 */
	public static String getPass()
	{
		return currentactive.upassword;
	}
	
	/**
	 * function getcuractive get current active user 
	 * @return  currentactive
	 */
	public static user getcuractive()
	{
		return currentactive;
	}
	
	/**
	 * this function make the current active user = null
	 */
	public static void NULLUser() 
	{
		currentactive.uemail=null;
		currentactive.uname=null;
		currentactive.upassword=null;
	}

	
}
