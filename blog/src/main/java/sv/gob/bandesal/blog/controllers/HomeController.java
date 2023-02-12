package sv.gob.bandesal.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/central/home")
public class HomeController {
	
	@GetMapping
	public String home() {
		return "views/home";
	}
	
}
