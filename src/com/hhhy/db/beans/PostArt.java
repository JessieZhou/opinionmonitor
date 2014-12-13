package com.hhhy.db.beans;

import java.io.Serializable;

public class PostArt implements Serializable{
	private String title;
	private String url;
	
	public PostArt(){}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
}
