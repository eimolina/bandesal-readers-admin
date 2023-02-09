package sv.gob.bandesal.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.bandesal.blog.domain.Blog;
import sv.gob.bandesal.blog.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	BlogRepository blogRepository;

	@Override
	public Blog saveBlog(Blog blog) {
		blogRepository.save(blog);
		return blog;
	}
	
	
}
