package sv.gob.bandesal.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.gob.bandesal.blog.services.IBlogService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	IBlogService blogservice;
	
	@GetMapping
	public String home() {
		return "views/home";
	}

}
