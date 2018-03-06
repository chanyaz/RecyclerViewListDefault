package com.solmin.sample.components.list.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@Generated("com.robohorse.robopojogenerator")
@JsonObject
public class DataItem{

	@SerializedName("num_comments")
	@JsonField(name ="num_comments")
	private int numComments;

	@SerializedName("price")
	@JsonField(name ="price")
	private int price;

	@SerializedName("photo")
	@JsonField(name ="photo")
	private String photo;

	@SerializedName("id")
	@JsonField(name ="id")
	private String id;

	@SerializedName("num_likes")
	@JsonField(name ="num_likes")
	private int numLikes;

	@SerializedName("title")
	@JsonField(name ="title")
	private String title;

	@SerializedName("status")
	@JsonField(name ="status")
	private String status;

	public void setNumComments(int numComments){
		this.numComments = numComments;
	}

	public int getNumComments(){
		return numComments;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNumLikes(int numLikes){
		this.numLikes = numLikes;
	}

	public int getNumLikes(){
		return numLikes;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"num_comments = '" + numComments + '\'' + 
			",price = '" + price + '\'' + 
			",photo = '" + photo + '\'' + 
			",id = '" + id + '\'' + 
			",num_likes = '" + numLikes + '\'' + 
			",title = '" + title + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}