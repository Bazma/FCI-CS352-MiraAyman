package com.FCI.SWE.Models;


public class user {
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
 public void SetName(String n) {
		n=this.uname;
	}
	public void SetEmail(String mail) {
		mail=this.uemail;

	}

	public void SetPass(String pass) {
		pass = this.upassword;
	}
 
 public String getName() {
		return uname;
	}

	public String getEmail() {
		return uemail;
	}

	public String getPass() {
		return upassword;
	}
	public user getcuractive() {
		return currentactive;
	}

public static void NULLUser() 
{
		currentactive.uemail=null;
		currentactive.uname=null;
		currentactive.upassword=null;
}

	
}
