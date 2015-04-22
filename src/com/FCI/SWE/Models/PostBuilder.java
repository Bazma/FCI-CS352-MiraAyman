package com.FCI.SWE.Models;

public abstract class PostBuilder {
	public PostEntity post;
	public PostEntity getpost()
	{
		return post;
	}
	public void createpost(PostEntity p)
	{
		post=p;
	}
	public abstract void writepost(PostEntity p,String privacy);
}
