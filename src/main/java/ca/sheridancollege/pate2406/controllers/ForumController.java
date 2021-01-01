package ca.sheridancollege.pate2406.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.pate2406.beans.CommentData;
import ca.sheridancollege.pate2406.beans.DiscussionData;
import ca.sheridancollege.pate2406.beans.UserRegistration;
import ca.sheridancollege.pate2406.database.DatabaseAccess;

@Controller
public class ForumController {
	Long i = (long) 0;
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@GetMapping("/register")
	public String register(Model model, UserRegistration user) {
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String processRegister(@ModelAttribute UserRegistration user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		User newuser = new User(user.getUsername(), encodedPassword, authorities);
		jdbcUserDetailsManager.createUser(newuser);
		return "redirect:/";
	}
	
	// Now all the mappings for CRUD
	@GetMapping("/secure/addthreads")
	public String getAddthreads( Model model, DiscussionData thread, CommentData cData){
		model.addAttribute("cData",cData);
		model.addAttribute("thread",thread);
		return "/secure/addthreads.html";
	}
	
	@PostMapping("/secure/viewthreads") //it is for inserting the thread
	public String postIndex(Model model,@ModelAttribute DiscussionData thread) {
		da.addThread(thread);
		ArrayList<DiscussionData> list = da.getAllthreads();
		model.addAttribute("thread", list);
		return "redirect:/secure/viewthreads";
	}

		@GetMapping("/secure/viewthreads") //it is for getting all the threads
		public String home(Model model, CommentData cData) {
			ArrayList<DiscussionData> list = da.getAllthreads();
			ArrayList<CommentData> listComment = da.getAllthreadsComment();
			model.addAttribute("threadComment", listComment);
			model.addAttribute("cData",cData);
			model.addAttribute("thread", list);
			return "/secure/viewthreads.html";
		}
		
		@GetMapping("/secure/addComments/{id}")
		public String getComment(@PathVariable int id, CommentData cData, Model model) {
			
			i = (long) id;
			System.out.print(i);
			model.addAttribute("cData", cData);
			return "/secure/addComments.html";
		}
		
		@PostMapping("/modify")
		public String PostComment(Long id, @ModelAttribute CommentData cData, Model model) {
			id = i;
			da.AddCommentDataById(id, cData);
			return "redirect:/secure/viewthreads";
		}
}
