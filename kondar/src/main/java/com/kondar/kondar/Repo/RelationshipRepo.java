package com.kondar.kondar.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kondar.kondar.Entity.Relationship;

public interface RelationshipRepo extends JpaRepository<Relationship, Integer> {
	
	//public List<Relationship> findAllByuser_one_id(int uid);
	public List<Relationship> findAllByUserOneId(int uid); 
	
	public Relationship findByUserOneIdAndUserTwoIdd(int uid,int uid2);
	
	public List<Relationship> findAllByUserOneIdAndStatus(int uid,int status);
	
	public void deleteReletionshipByUserOneIdAndUserTwoIdd(int uid, int uid2);

}
