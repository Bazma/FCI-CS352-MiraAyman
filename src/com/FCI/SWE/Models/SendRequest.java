package com.FCI.SWE.Models;

import java.util.ArrayList;

public class SendRequest extends Command{

	
	public Reciver object = new Reciver();
	
	public ArrayList<Notification> execute(Notification obj)
	{
		ArrayList<Notification>Lis=new ArrayList(); 
		Lis=object.AcceptRequestFriend(obj);
		return Lis; 
	}
}
