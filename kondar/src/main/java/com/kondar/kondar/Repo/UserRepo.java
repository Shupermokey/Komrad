package com.kondar.kondar.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kondar.kondar.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findUserByUid(Integer i);
	public User findUserByuserName(String username);

}
