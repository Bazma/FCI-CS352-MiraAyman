package com.FCI.SWE.Models;

import java.util.ArrayList;

public class Reciver {
	
	private String email;
	
	 
	 /**
	  * function Setmail set mail to object user
	  * @param mail
	  */
	 public  void SetEmail(String mail) 
	 {
		this.email=mail;
	 }
	

		/**
		 * function getEmail get email to object user
		 * @return uemail
		 */
		public String getEmail() 
		{
			return email;
		}
			
		public ArrayList<Notification> ReplyMessage(Notification ob)
		{
			
		  	NotificationEntity obj = new NotificationEntity("","",""); 
			ArrayList<Notification> Mes=new ArrayList();
			Mes=obj.GetMessageOfConversation(user.currentactive.getEmail(),ob.getTitle());
			for (int i=0;i<Mes.size();i++)
			{
				System.out.println("el msg f el coommand "+Mes.get(i).getMessage());
			}
			return Mes;
		}
		
		public ArrayList<Notification> AcceptRequestFriend(Notification ob )
		{
			return null;
		}

}
  