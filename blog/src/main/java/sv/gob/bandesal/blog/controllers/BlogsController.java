package sv.gob.bandesal.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import sv.gob.bandesal.blog.domain.Blog;
import sv.gob.bandesal.blog.services.IBlogService;

@Controller
@RequestMapping("/blogs")
public class BlogsController {

	@Autowired
	IBlogService blogservice;
	
	@GetMapping
	public String blogs(Model model, HttpServletRequest request) {
		model.addAttribute("blogs", blogservice.getAllBlogs());
		return "views/blog/blogs";
	}
	
	@GetMapping(value = "/new")
	public String save(Model model) {
		if (!model.containsAttribute("blog")) {
			model.addAttribute("blog", blogservice.getEmptyModel());
		}
		return "views/blog/new-blog";
	}
	
	@PostMapping(value = "/new")
	public ModelAndView save(@Valid @ModelAttribute("blog") Blog blog, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {	
			redirectAttrs.addFlashAttribute("blog", blog);
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.blog", result);
			model.addAttribute("blog", blog);
			return new ModelAndView("redirect:new");
		}
		
		redirectAttrs.addFlashAttribute("message", "Se guardo correctamente");
		blogservice.saveBlog(blog);
		return new ModelAndView("redirect:new");
	}
	
	@GetMapping(value = "/{id}")
	public ModelAndView editblog(@PathVariable(name = "id", required = true) Long id, Model model) {
		Blog blog = blogservice.getBlog(id);
		ModelAndView mav = new ModelAndView("views/blog/new-blog");
		if(blog != null) {
			mav.addObject("blog", blog);
			return mav;
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Unable to find resource");
	}
	
	@GetMapping(value = "/remove/{id}")
	public ModelAndView remove(@PathVariable(name = "id", required = true) Long id, Model model) {
		Blog blog = blogservice.getBlog(id);
		ModelAndView mav = new ModelAndView("views/blog/remove-blog");
		if(blog != null) {
			mav.addObject("blog", blog);
			return mav;
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Unable to find resource");
	}
	
	@PostMapping(value = "/remove")
	public ModelAndView remove(@RequestParam(name = "id") Long id) {
		Blog blog = blogservice.getBlog(id);
		if(blog != null) {
			blogservice.removeBlog(blog);
			return new ModelAndView("redirect:/blogs");
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Unable to find resource");
	}
}
