package com.FCI.SWE.Models;

import java.util.ArrayList;

public  abstract class Command {

	
	public abstract ArrayList<Notification> execute(Notification ob);
	
}
