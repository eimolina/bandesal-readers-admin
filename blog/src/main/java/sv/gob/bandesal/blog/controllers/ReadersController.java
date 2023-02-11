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
import sv.gob.bandesal.blog.domain.Reader;
import sv.gob.bandesal.blog.services.IReaderService;

@Controller
@RequestMapping("/readers")
public class ReadersController {

	@Autowired
	IReaderService readerService;

	@GetMapping
	public String readers(Model model, HttpServletRequest request) {
		model.addAttribute("readers", readerService.getAllReaders());
		return "views/reader/readers";
	}

	@GetMapping(value = "/new")
	public String save(Model model) {
		if (!model.containsAttribute("reader")) {
			model.addAttribute("reader", readerService.getEmptyModel());
		}
		return "views/reader/new-reader";
	}
	
	@PostMapping(value = "/new")
	public ModelAndView save(@Valid @ModelAttribute("reader") Reader reader, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			redirectAttrs.addFlashAttribute("reader", reader);
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.reader", result);
			model.addAttribute("reader", reader);
			return new ModelAndView("redirect:new");
		}
		
		redirectAttrs.addFlashAttribute("message", "Se guardo correctamente");
		readerService.saveReader(reader);
		return new ModelAndView("redirect:new");
	}
	
	@GetMapping(value = "/{id}")
	public ModelAndView editreader(@PathVariable(name = "id", required = true) Long id, Model model) {
		Reader reader = readerService.getReader(id);
		ModelAndView mav = new ModelAndView("views/reader/new-reader");
		if(reader != null) {
			mav.addObject("reader", reader);
			return mav;
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Unable to find resource");
	}
	
	@GetMapping(value = "/remove/{id}")
	public ModelAndView remove(@PathVariable(name = "id", required = true) Long id, Model model) {
		Reader reader = readerService.getReader(id);
		ModelAndView mav = new ModelAndView("views/reader/remove-reader");
		if(reader != null) {
			mav.addObject("reader", reader);
			return mav;
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Unable to find resource");
	}
	
	@PostMapping(value = "/remove")
	public ModelAndView remove(@RequestParam(name = "id") Long id) {
		Reader reader = readerService.getReader(id);
		if(reader != null) {
			readerService.removeReader(reader);
			return new ModelAndView("redirect:/readers");
		}
		throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Unable to find resource");
	}

	
}
