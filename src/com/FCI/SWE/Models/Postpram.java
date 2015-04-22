package com.FCI.SWE.Models;

public class Postpram {
	
	public String ID;
	public String content;
	public String email;
	public String title;
	public String Seen;
	public String Hash;
	
	
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void Settitle(String n)
	 {
		 this.title=n;
	 }
	 /**
		 * function Setname set name to object user
		 * @param n
		 */
		 public  void SetHash(String n)
		 {
			 this.Hash=n;
		 }
	 
	 public  void SetSeen(String n)
	 {
		 this.Seen=n;
	 }

	 public  void SetContent(String n)
	 {
		 this.content=n;
	 }

	 /**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetEmail(String n)
	 {
		 this.email=n;
	 }
	 /**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetID(String n)
	 {
		 this.ID=n;
	 }
		

	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getID() 
	{
		return ID;
	}
	 
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String gettitle() 
	{
		return title;
	}

	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getHash() 
	{
		return Hash;
	}
	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getcontent() 
	{
		return content;
	}
	
	
	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getemail() 
	{
		return email;
	}
	
	public String getSeen() 
	{
		return Seen;
	}
}
