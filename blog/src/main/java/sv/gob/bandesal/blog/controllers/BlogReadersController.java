package sv.gob.bandesal.blog.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import sv.gob.bandesal.blog.domain.Blog;
import sv.gob.bandesal.blog.domain.BlogReader;
import sv.gob.bandesal.blog.domain.BlogReader.BlogReaderId;
import sv.gob.bandesal.blog.domain.Reader;
import sv.gob.bandesal.blog.services.IBlogReaderService;
import sv.gob.bandesal.blog.services.IBlogService;
import sv.gob.bandesal.blog.services.IReaderService;

@Controller
@RequestMapping("/blogsreaders")
public class BlogReadersController {

	@Autowired
	IBlogReaderService blogReaderService;

	@Autowired
	IBlogService blogservice;

	@Autowired
	IReaderService readerService;

	@GetMapping
	public String blogsreaders(Model model) {
		model.addAttribute("blogreaders", blogservice.getAllBlogReaders());
		return "views/blogreader/blogsreaders";
	}

	@GetMapping(value = "/{id}")
	public ModelAndView editblogreaders(@PathVariable(name = "id", required = true) Long id, Model model) {
		Blog blog = blogservice.getBlog(id);
		ModelAndView mav = new ModelAndView("views/blogreader/new-blogreader");
		if (blog != null) {
			mav.addObject("blogreader", blog);
			List<Reader> readers = blog.getBlogsreaders().stream().map(r -> r.getReader()).collect(Collectors.toList());
			mav.addObject("readers", readers);
			mav.addObject("allreaders", readerService.getAllReadersNotInBlog(blog.getId()));
			return mav;
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Unable to find resource");
	}

	@PostMapping(value = "/add")
	public ModelAndView add(@ModelAttribute BlogReader data) {
		BlogReaderId emmbebedId = new BlogReaderId();
		Reader reader = readerService.getReader(data.getReader().getId());
		if (reader != null) {
			emmbebedId.setBid(data.getBlog().getId());
			emmbebedId.setRid(data.getReader().getId());
			data.setId(emmbebedId);
			blogReaderService.saveBlogReader(data);
			return new ModelAndView("redirect:/blogsreaders/" + data.getBlog().getId());
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Unable to find resource");
	}

	@PostMapping(value = "/remove")
	public ModelAndView remove(@ModelAttribute BlogReader data) {
		BlogReaderId emmbebedId = new BlogReaderId();
		Reader reader = readerService.getReader(data.getReader().getId());
		if (reader != null) {
			emmbebedId.setBid(data.getBlog().getId());
			emmbebedId.setRid(data.getReader().getId());
			data.setId(emmbebedId);
			blogReaderService.removeBlogReader(data);
			return new ModelAndView("redirect:/blogsreaders/" + data.getBlog().getId());
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Unable to find resource");
	}
}
