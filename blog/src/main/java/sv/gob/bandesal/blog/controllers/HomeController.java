package sv.gob.bandesal.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.gob.bandesal.blog.services.IBlogService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	IBlogService blogservice;
	
	@GetMapping("/home")
	public String home() {
		return "views/home";
	}
	
	@GetMapping("/readers")
	public String readers() {
		return "views/readers";
	}
	
	@GetMapping("/blogs")
	public String blogs() {
		return "views/blogs";
	}
	
	@GetMapping("/blogsreaders")
	public String blogsreaders() {
		return "views/blogsreaders";
	}

}
