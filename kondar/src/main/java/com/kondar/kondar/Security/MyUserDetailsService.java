package com.kondar.kondar.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kondar.kondar.Entity.User;
import com.kondar.kondar.Entity.UserPrinciple;
import com.kondar.kondar.Repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findUserByuserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new UserPrinciple(user);
	}

}
