package com.kondar.kondar.Entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int uid;
	String userName;
	String password;
	
	
	public User() {}
	
	
	public User(String userName) {
		super();
		this.userName = userName;
	}


	public User(Integer uid, String userName, String password) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.password = password;
	}


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
	


}
