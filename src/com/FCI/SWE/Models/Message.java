package com.FCI.SWE.Models;

import java.util.ArrayList;

public class Message extends Command{

	public Reciver object = new Reciver();
	public ArrayList<Notification> execute(Notification ob)
	{
		ArrayList<Notification>Lis=new ArrayList();
		Lis=object.ReplyMessage(ob);
		return Lis; 
	}
	
}
