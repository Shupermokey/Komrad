package com.kondar.kondar.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kondar.kondar.Entity.Posts;
import com.kondar.kondar.Entity.Relationship;
import com.kondar.kondar.Entity.User;
import com.kondar.kondar.Repo.PostRepo;
import com.kondar.kondar.Repo.RelationshipRepo;
import com.kondar.kondar.Repo.UserRepo;

@Controller
public class MainPageContoller {
	
	@Autowired UserRepo userRepo;
	
	@Autowired PostRepo postRepo;
	
	@Autowired RelationshipRepo relRepo;
	
	ModelAndView mv = new ModelAndView();
	
	@RequestMapping("/")
	public String loadMainPage() {
		return "MainPage";
	}
	
	@RequestMapping("/logedIn")
	public ModelAndView loadPerson(User user, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		User valid;
		System.out.println("Logged In");
		System.out.println(user);
		if((valid = userRepo.findUserByuserName(user.getUserName()))!=null) {
			if(valid.getPassword().equals(user.getPassword())) {
			
			User currentUser = userRepo.findUserByuserName(user.getUserName());
			session.setAttribute("currentUser", currentUser);
			System.out.println(currentUser);
			mv.setViewName("UserHomePage");
			}
			else {
				mv.addObject("CreateUserMessage", "Password doesn't match");
				mv.setViewName("MainPage");
			}
		}
		else {
			mv.addObject("CreateUserMessage", "There is no user");
			mv.setViewName("MainPage");
		}
		return mv;
	}
	
	@RequestMapping("/feed")
	public String returnToFeed() {
		return "UserHomePage";
	}
	
	
	@RequestMapping("/createUser")
	public ModelAndView createUser(User user) {
		ModelAndView mv = new ModelAndView();
		  if(userRepo.findUserByuserName(user.getUserName())!=null) {
		  mv.addObject("CreateUserMessage", "User Already Created");
		  mv.setViewName("CreateUser"); return mv;
		  //System.out.println("User already created");
		  
		  } else {
			userRepo.save(user);
			Relationship rel = new Relationship(userRepo.findUserByuserName(user.getUserName()).getUid(),userRepo.findUserByuserName(user.getUserName()).getUid() , 2, 0 );
			relRepo.save(rel);
			mv.addObject("CreateUserMessage", "User Created");
			mv.setViewName("MainPage");
			return mv;
			}
	}
			
		
		
	@RequestMapping("/createPage")
	public String loadCreatePage() {
		return "CreateUser";
	}
	
	@RequestMapping("/logout")
	public String loggoutPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("currentUser");
		return "MainPage";
	}
	
	
	@RequestMapping("/post")
	public String createPost(String myPost, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser!=null) {
			Posts post = new Posts(currentUser.getUid(),myPost);
			postRepo.save(post);
			}
		
		return "UserHomePage";
	}
	
	@RequestMapping("/profilePage")
	public String userProfile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser!=null) {
			List<Posts> allPosts = postRepo.findAllByuid(currentUser.getUid());
			session.setAttribute("userPosts", allPosts);
			}
		return "userProfile";
	}
	
	@RequestMapping("/findFriends")
	public String findFriends(HttpServletRequest req, String friend) {
		HttpSession session = req.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		List<User> users = userRepo.findAll();
		ArrayList<Relationship> u = new ArrayList<Relationship>();
		for(int i = 0; i<users.size(); i++) {
			if(relRepo.findByUserOneIdAndUserTwoIdd(currentUser.getUid(), users.get(i).getUid()) != null ) {
				u.add(relRepo.findByUserOneIdAndUserTwoIdd(currentUser.getUid(), users.get(i).getUid()));
			}
			else {
				u.add(new Relationship(currentUser.getUid(), users.get(i).getUid(), 0, 0 ));
			}
		}
		
		session.setAttribute("Relationships", u);
		session.setAttribute("users", users);
		return "friendSearchPage";
	}
	
	@RequestMapping("/addFriend")
	public String addFriend(HttpServletRequest req, String userName) {
		if(relRepo.findByUserOneIdAndUserTwoIdd(userRepo.findUserByuserName(userName).getUid(), userRepo.findUserByuserName(userName).getUid())!=null){
			Relationship delRel = relRepo.findByUserOneIdAndUserTwoIdd(userRepo.findUserByuserName(userName).getUid(), userRepo.findUserByuserName(userName).getUid());
			if(delRel.getUserOneId()==delRel.getUserTwoIdd()) {
				System.out.println(delRel);
				relRepo.delete(relRepo.findByUserOneIdAndUserTwoIdd(userRepo.findUserByuserName(userName).getUid(), userRepo.findUserByuserName(userName).getUid()));
				System.out.println("____________________________________________");
				
				//relRepo.deleteByUserOneIdAndUserTwoIdd(userRepo.findUserByuserName(userName).getUid(), userRepo.findUserByuserName(userName).getUid());
		}
		}
		HttpSession session = req.getSession();
		List<User> users = userRepo.findAll();
		session.setAttribute("friendList", users);
		User currentUser = (User)session.getAttribute("currentUser");
		Relationship rel;
		int uniqueID2 = userRepo.findUserByuserName(userName).getUid();
		int uniqueID1 = currentUser.getUid();
		if(relRepo.findByUserOneIdAndUserTwoIdd(uniqueID1,uniqueID2)!= null){
			 rel = relRepo.findByUserOneIdAndUserTwoIdd(uniqueID1, uniqueID2);
		}
		else {
			System.out.println("New Rel");
			 rel = new Relationship();
		}
		if(currentUser!=null) {
		rel.setUserOneId(currentUser.getUid());
		
		}
		//find uid of the friend
		if(userName!=null) {
			if(userRepo.findUserByuserName(userName)!=null) {
		User friendUser = (User)userRepo.findUserByuserName(userName);
		rel.setUserTwoIdd(friendUser.getUid());
		
		
		}
		}
		
		
		System.out.println(rel.toString());
		if(rel.getStatus()==0) {
			
			rel.setStatus(1);
			relRepo.save(rel);
		}
		else if(rel.getStatus()==1) {
			System.out.println("can't save on a pending");
		}

		 

		session.setAttribute("currentUserRelationships", relRepo.findAllByUserOneId(currentUser.getUid()) );
		session.setAttribute("Relationships", relRepo.findAll());
		System.out.println(relRepo.findAllByUserOneId(currentUser.getUid()));
		
		return "friendSearchPage";  
	}
	
	
	
}
