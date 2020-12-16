package com.kondar.kondar.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Posts {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int pid;
	
	int uid;
	String post;
	
	public Posts() {}
	
	public Posts(int uid, String post) {
		super();
		this.uid = uid;
		this.post = post;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
	

}
