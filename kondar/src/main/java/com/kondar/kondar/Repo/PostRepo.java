package com.kondar.kondar.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kondar.kondar.Entity.Posts;

public interface PostRepo extends JpaRepository<Posts,Integer> {
	
	public List<Posts> findAllByuid(int uid);

}
