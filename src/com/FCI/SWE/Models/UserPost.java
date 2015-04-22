package com.FCI.SWE.Models;

public class UserPost extends PostBuilder{

	public void writepost(PostEntity p, String privacy)
	{
		if (privacy.equals("public"))
	    {
	    	privacypost pr = new publicpost();
	    	pr.setprivacy();
	    	p.Setprivacy(pr);
	    }
	    else if (privacy.equals("private"))
	    {
	    	privacypost pr = new privatepost();
	    	pr.setprivacy();
	    	p.Setprivacy(pr);
	    
//	    	privacypost p = new privatepost();
//	    	p.setprivacy("private");
//	    	pos.Setprivacy(p);
	    	
	    }
	    else if (privacy.equals("custom"))	
	    {
	    	privacypost pr = new custompost();
	    	pr.setprivacy();
	    	p.Setprivacy(pr);
	    
//	    	privacypost p = new custompost();
//	    	p.setprivacy("custom");
//	    	pos.Setprivacy(p);	
	    }
	    
		p.savePost(privacy);		
	}
}
