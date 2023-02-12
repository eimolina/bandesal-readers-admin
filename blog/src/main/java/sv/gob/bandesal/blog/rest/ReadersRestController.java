package sv.gob.bandesal.blog.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.gob.bandesal.blog.domain.Reader;
import sv.gob.bandesal.blog.repository.ReaderRepository;

@RestController
@RequestMapping("/api/v1/readers")
public class ReadersRestController {

	@Autowired
	ReaderRepository readerRepository;
	
	@GetMapping
	public List<Reader> getall(){
		return readerRepository.findAll();
	}
	
}
