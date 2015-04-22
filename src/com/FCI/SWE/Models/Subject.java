package com.FCI.SWE.Models;

import java.util.ArrayList;

public  class Subject 
{

	public ArrayList<ObserveUser> list=new ArrayList();
//	public Observer object=new Observer(); 
//	public void setobserver(Observer obj)
//	{
//		this.object=obj;
//	}
//	public abstract void Notify();
	
	/**
	 * function that add  email to list
	 * @param list1
	 */
	public void attachtolist (ArrayList<ObserveUser>list1)
	{
		list.clear();
		for (int i=0;i<list1.size();i++)
		{
			list.add(list1.get(i));
		}
		for (int i=0;i<list.size();i++)
		{
			System.out.println("check cjeckk = "+list.get(i).getemail());
		}
	}
		
}
