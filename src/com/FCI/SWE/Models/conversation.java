package com.FCI.SWE.Models;

import java.util.ArrayList;

public class conversation extends Subject
{

	public void Notify(ArrayList<ObserveUser>lis,String msg,String title)
	{
		NotificationEntity ob = new NotificationEntity("com.FCI.SWE.Models.Message",msg,title);
		for (int i=0;i<lis.size();i++)
		{
			System.out.println("list = "+lis.get(i));
			//list.get(i).update();
		}
		ob.setList(lis);
		ob.getlist();
		for (int i=0;i<ob.getlist().size();i++)
		{
			ob.getlist().get(i).update(ob);
		    break;	
		}
		for (int i=0;i<ob.getlist().size();i++)
		{
			System.out.println("Attach = "+ob.getlist().get(i).getemail());
			//list.get(i).update();
		}
		
	}
	
	public ArrayList<Notification> NotifyAll(String users)
	{
		ArrayList<Notification>Notification1=new ArrayList();
		NotificationEntity ob = new NotificationEntity("","","");
		Notification1=ob.GetNotifications(users);
		return Notification1;
	}
	
}
