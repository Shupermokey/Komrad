package com.kondar.kondar.Entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.core.annotation.AliasFor;

@Entity
public class Relationship {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	int relId;
	int userOneId;
	int userTwoIdd;
	int status;
	int action_user_id;
	
	public Relationship() {
		
	}

	
	public Relationship(int userOneId, int userTwoIdd, int status, int action_user_id) {

		this.userOneId = userOneId;
		this.userTwoIdd = userTwoIdd;
		this.status = status;
		this.action_user_id=action_user_id;
	}



	public int getAction_user_id() {
		return action_user_id;
	}


	public void setAction_user_id(int action_user_id) {
		this.action_user_id = action_user_id;
	}


	public int getRelId() {
		return relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
	}

	public int getUserOneId() {
		return userOneId;
	}

	public void setUserOneId(int userOneId) {
		this.userOneId = userOneId;
	}

	public int getUserTwoIdd() {
		return userTwoIdd;
	}

	public void setUserTwoIdd(int userTwoIdd) {
		this.userTwoIdd = userTwoIdd;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Relationship [relId=" + relId + ", userOneId=" + userOneId + ", userTwoIdd=" + userTwoIdd + ", status="
				+ status + "]";
	}
	
	
	
	

}
