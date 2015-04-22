package com.FCI.SWE.Models;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PostEntity {
	
	public String owner;
	public String content ;
	public String numberoflikes;
	public String emailto;
	public String Feeling;
	public privacypost privacy;
	public String typepost;
	public String title;
	public String NumberOfSeen;
	public String HashTag;
	public String NumbersOfHashTag;
	
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetNumbersOfHashTag(String n)
	 {
		 this.NumbersOfHashTag=n;
	 }
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetHashTag(String n)
	 {
		 this.HashTag=n;
	 }
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetNumberOfSeen(String n)
	 {
		 this.NumberOfSeen=n;
	 }

	
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
	 public  void Setprivacy(privacypost n)
	 {
		 this.privacy=n;
	 }

	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetTypePost(String n)
	 {
		 this.typepost=n;
	 }
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetFeeling(String n)
	 {
		 this.Feeling=n;
	 }
	/**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetOwner(String n)
	 {
		 this.owner=n;
	 }
	 /**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetContent(String n)
	 {
		 this.content=n;
	 }
	 /**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void Setnumberoflikes(String n)
	 {
		 this.numberoflikes=n;
	 }
	 /**
	 * function Setname set name to object user
	 * @param n
	 */
	 public  void SetEmailto(String n)
	 {
		 this.emailto=n;
	 }
		
	 
		/**
		 * function getName get name to object user
		 * @return name
		 */
		public String getNumberOfSeen() 
		{
			return NumberOfSeen;
		}

		/**
		 * function getName get name to object user
		 * @return name
		 */
		public String getHashtag() 
		{
			return HashTag;
		}
		
		

		/**
		 * function getName get name to object user
		 * @return name
		 */
		public String getNumbersOfHashTag() 
		{
			return NumbersOfHashTag;
		}
	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String gettypepost() 
	{
		return typepost;
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
	public String getFeeling() 
	{
		return Feeling;
	}
	

	/**
	 * function getName get name to object user
	 * @return name
	 */
	public privacypost getprivacy() 
	{
		return privacy;
	}
	

	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getowner() 
	{
		return owner;
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
	public String getnumberoflikes() 
	{
		return numberoflikes;
	}
	
	/**
	 * function getName get name to object user
	 * @return name
	 */
	public String getemailto() 
	{
		return emailto;
	}
	
	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static PostEntity getpost(String json)
	{
		PostEntity obj= new PostEntity(); 
		JSONParser parser = new JSONParser();
		try
		{
			JSONObject object = (JSONObject) parser.parse(json);
			obj.SetOwner(object.get("owner").toString());
			obj.SetContent(object.get("content").toString());
			obj.SetEmailto(object.get("emailto").toString());
			obj.Setnumberoflikes(object.get("numberoflikes").toString());
			obj.SetFeeling(object.get("feeling").toString());
			//obj.Setprivacy(object.get("privacy").toString());
			obj.SetTypePost(object.get("typepost").toString());
			obj.Settitle(object.get("title").toString());
			obj.SetNumberOfSeen(object.get("Seen").toString());
			obj.SetNumbersOfHashTag(object.get("NumHash").toString());
			return obj;
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for friend in datastore
	 * @return boolean if friend is found correctly or not
	 */
	public static boolean CheckTag(String Tag,String email) 
	{
	   
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         Query gaeQuery = new Query("friend");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
					//System.out.println(entity.getProperty("email").toString());
					if ((entity.getProperty("emailfrom").toString().equals(Tag)&&entity.getProperty("emailto").toString().equals(email)&&entity.getProperty("status").toString().equals("1"))||(email.equals(Tag)))
					   {
						System.out.println("ana lk 3la tooooooooooooooooool ");
					        return true;
					        
						
					   }
			      }
		    
     return false;
}

	
	/**
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for friend in datastore
	 * @return boolean if friend is found correctly or not
	 */
	public static ArrayList<Postpram> ViewPublic(String email) 
	{
	 //  email=user.currentactive.getEmail();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		ArrayList<Postpram>List=new ArrayList();
        Query gaeQuery = new Query("post");
	    PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		  {
			 if (entity.getProperty("title").toString().equals("")||entity.getProperty("title").toString().equals(null))
				{
					System.out.println("ya beeeeh ba2a "+entity.getProperty("privacy").toString());
				 if (entity.getProperty("privacy").toString().equals("custom"))
		    		{
							if (entity.getProperty("emailto").toString().equals(email))
							{		System.out.println("ya beeeeh ba2a h save custom aho ");
							
								Postpram o = new Postpram();
								o.SetID(""+entity.getKey().getId());
								o.SetContent(entity.getProperty("content").toString());
								o.Settitle(entity.getProperty("title").toString());
								o.SetEmail(entity.getProperty("owner").toString());
								o.SetSeen(entity.getProperty("Seen").toString());
								o.SetHash(entity.getProperty("Hashtag").toString());
	            			    List.add(o);
							}
						
					}	
				 else if (entity.getProperty("privacy").toString().equals("public"))
					   {System.out.println("ya beeeeh ba2a h save public aho ");
						
						Postpram o = new Postpram();
						o.SetID(""+entity.getKey().getId());
						o.SetContent(entity.getProperty("content").toString());
						o.Settitle(entity.getProperty("title").toString());
						o.SetEmail(entity.getProperty("owner").toString());
						o.SetSeen(entity.getProperty("Seen").toString());
						o.SetHash(entity.getProperty("Hashtag").toString());
					    List.add(o);	
					   }
					else if(entity.getProperty("privacy").toString().equals("private"))
					{
						if (entity.getProperty("emailto").toString().equals(email))
						{System.out.println("ya beeeeh ba2a h save private aho ");
						
							Postpram o = new Postpram();
							o.SetID(""+entity.getKey().getId());
							o.SetContent(entity.getProperty("content").toString());
							o.Settitle(entity.getProperty("title").toString());
							o.SetEmail(entity.getProperty("owner").toString());
							o.SetSeen(entity.getProperty("Seen").toString());
							o.SetHash(entity.getProperty("Hashtag").toString());
            			     List.add(o);
						}
					
					}
					else
					{
						
					}
		    		
		     }
			else
			  {
	    		   if (entity.getProperty("privacy").toString().equals("public"))
				   {System.out.println("ya beeeeh ba2a h save public like aho ");
					
	    			   Postpram o = new Postpram();
						o.SetID(""+entity.getKey().getId());
						o.SetContent(entity.getProperty("content").toString());
						o.Settitle(entity.getProperty("title").toString());
						o.SetEmail(entity.getProperty("title").toString());
						o.SetSeen(entity.getProperty("Seen").toString());
						o.SetHash(entity.getProperty("Hashtag").toString());
        			     List.add(o);	
				   }
	    		   else if (entity.getProperty("privacy").toString().equals("private"))
	    		   {
	    			   Query gaeQuery1 = new Query("LikePage");
	    			   PreparedQuery pq1 = datastore.prepare(gaeQuery1);
	    		       for (Entity entity1 : pq1.asIterable()) 
	    			     {
	    					//System.out.println(entity.getProperty("email").toString());
	    					if (entity1.getProperty("like").toString().equals("1")&&entity.getProperty("title").toString().equals(entity1.getProperty("Name"))&&entity1.getProperty("email").toString().equals(email))
	    					   {System.out.println("ya beeeeh ba2a h save private like  aho ");
								
	    						Postpram o = new Postpram();
	    						o.SetID(""+entity.getKey().getId());
	    						o.SetContent(entity.getProperty("content").toString());
	    						o.Settitle(entity.getProperty("title").toString());
	    						o.SetEmail(entity.getProperty("title").toString());
	    						o.SetSeen(entity.getProperty("Seen").toString());
	    						o.SetHash(entity.getProperty("Hashtag").toString());
	   					         List.add(o);
	    					        
	    						
	    					   }
	    			 
	    		         }
	    		       
	    		   
	    		   }
			  }
	  }
		
		
	//Seen1(List);	
	//ShowSeen1(List,email);
	for(int i=0;i<List.size();i++)
	{
		System.out.println(List.get(i).getSeen()+" ");
	}	
     return List;
}

	
	public static void ShowSeen1(ArrayList<Postpram>List,String email)
	{
		int i=0;
			DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();	
	        Query gaeQuery2 = new Query("post");
		    PreparedQuery pq2 = datastore2.prepare(gaeQuery2);
			
		    for (Entity entity2 : pq2.asIterable()) 
			{ 
		    	if(entity2.getProperty("typepost").toString().equals("Page")&&entity2.getProperty("owner").toString().equals(email))
			    {
			    	List.get(i).SetSeen(entity2.getProperty("Seen").toString());
			   //	System.out.println("ana fe ijiiu"+List.get(i).getSeen());
			    	 
			    }
			    else
			    {
			    	List.get(i).SetSeen("Not allow To Seen :P ");
			    }
			    i++;
			}	
			
		}
	
	public static void Seen1(ArrayList<Postpram>List)
	{
		int i=0;
			DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();	
	        Query gaeQuery2 = new Query("post");
		    PreparedQuery pq2 = datastore2.prepare(gaeQuery2);
			for (Entity entity2 : pq2.asIterable()) 
			{ 
				String r = ""+entity2.getKey().getId();
			    if( r.equals(List.get(i).getID()))
			    {
			    	String z=entity2.getProperty("Seen").toString();
			    	
			    	int x=Integer.parseInt(z);
					x=x+1;
					String y = x+"";
					entity2.setProperty("Seen", y);
			        datastore2.put(entity2);
			    }
			    i++;
			}	
		}
	
	
	/**
	 * This method will be used to save Notification object in datastore
	 * 
	 * @return boolean if Notification is saved correctly or not
	 */
	public Boolean savePost(String privacy) 
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query gaeQuery = new Query("post");
		PreparedQuery pq = datastore.prepare(gaeQuery); 
		Entity employee = new Entity("post");
		employee.setProperty("owner",this.owner);
		employee.setProperty("content", this.content);
		employee.setProperty("numberoflikes",this.numberoflikes);
		employee.setProperty("emailto",this.emailto);	
		employee.setProperty("feeling",this.Feeling);
		employee.setProperty("privacy",privacy);
		employee.setProperty("typepost",this.typepost);
		employee.setProperty("title",this.title);
		employee.setProperty("Hashtag",this.HashTag);
		employee.setProperty("NumHash","1");
		employee.setProperty("Seen","0");
		datastore.put(employee);
		return true;
		
	}
	
	/**
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for friend in datastore
	 * @return boolean if friend is found correctly or not
	 */
	
	public static boolean CheckName(String Name,String email) 
	{
	   
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         Query gaeQuery = new Query("page");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
					//System.out.println(entity.getProperty("email").toString());
					if (entity.getProperty("Name").toString().equals(Name)&&entity.getProperty("Owner").toString().equals(email))
					   {
						System.out.println("ana lk 3la tooooooooooooooooool ");
					        return true;
					        
						
					   }
			      }
		    
     return false;
}


	
	
	public static boolean Likepost(String id) 
	{
	   
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         Query gaeQuery = new Query("post");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {String x=entity.getKey().getId()+"";
					//System.out.println(entity.getProperty("email").toString());
					if (x.equals(id))
					   {
						entity.setProperty("numberoflikes", "1");
				        datastore.put(entity);	    
			    
						System.out.println("ana lk 3la y albe  tooooooooooooooooool ");
						   
						return true;
					        
						
					   }
			      }
		    
     return true;
}

	

	public static ArrayList<Postpram> CheckHashTag(String Hash) 
	{
		System.out.println("ana lk 3la tooooooooooooooooool +"+Hash);
		
		ArrayList<Postpram>List=new ArrayList();
	        
		int numhashtags=0;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         Query gaeQuery = new Query("post");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
   					    if (entity.getProperty("Hashtag").toString().equals(Hash))
					    {
   					    	System.out.println("ana lk 3la tooooooooooooooooool y albe  ");
   					    	Postpram o = new Postpram();
    						o.SetID(""+entity.getKey().getId());
    						o.SetContent(entity.getProperty("content").toString());
    						o.SetEmail(entity.getProperty("owner").toString());
    						o.SetHash(entity.getProperty("Hashtag").toString());
    						List.add(o);
	
							System.out.println("ana lk 3la tooooooooooooooooool ");
							numhashtags++;
			//				return true;
						
					   }
			      }    
     return List;
}
	public static int Number(String Hash) 
	{
		System.out.println("ana lk 3la tooooooooooooooooool +"+Hash);
		
	//	ArrayList<Postpram>List=new ArrayList();
	        
		int numhashtags=0;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         Query gaeQuery = new Query("post");
			   PreparedQuery pq = datastore.prepare(gaeQuery);
		       for (Entity entity : pq.asIterable()) 
			     {
   					    if (entity.getProperty("Hashtag").toString().equals(Hash))
					    {
   					    	
	
							System.out.println("ana lk 3la tooooooooooooooooool ");
							numhashtags++;
							//return ;
						
					   }
			      }    
     return numhashtags;
}



public static String KONumberOfHashtag() 
{
	//System.out.println("ana lk 3la tooooooooooooooooool +"+Hash);
	int max = 0;
	ArrayList<String>List=new ArrayList();
	ArrayList<Integer>Max=new ArrayList();    
	int numhashtags=0;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
     Query gaeQuery = new Query("post");
		   PreparedQuery pq = datastore.prepare(gaeQuery);
	       for (Entity entity : pq.asIterable()) 
		     {
				if (entity.getProperty("Hashtag").toString().equals(""))
				    {
		   		
	
				   }
				else 
				{
					  System.out.println("ana lk 3la tooooooooooooooooool y albeä ÚäÚäÚä  ");
					    List.add(entity.getProperty("Hashtag").toString());
					  
				}
		      }
	       
	       
	       for (int i=0;i<List.size();i++)
	       {
	    	   for (int j=0;j<List.size();j++)
	    	   {
	    		 if (List.get(i).equals(List.get(j)))  
	    		 {
	    			 numhashtags++; 
	    			 System.out.println("da5l");
	    		 }
	    	   }
	    	   Max.add(numhashtags);
	    	   numhashtags=0;
	       }
	       int index = 0;
	       for (int i=0;i<Max.size();i++)
	       {
	    	   if (Max.get(i)>max)
	    	   {
	    	  // System.out.println("Max List "+List.get(i)+" ");
	    	    //System.out.println(List.get(i)+"Max Max "+Max.get(i)+" ");
	    	    index =i;
	    	    max=Max.get(i);
	    	   }
	       }
	       System.out.println(max+" "+List.get(index));
   	    
	       
 return List.get(index);
}

}
