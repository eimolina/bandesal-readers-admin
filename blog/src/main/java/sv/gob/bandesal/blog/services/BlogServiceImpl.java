package sv.gob.bandesal.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.bandesal.blog.domain.Blog;
import sv.gob.bandesal.blog.repository.BlogRepository;

@Service
public class BlogServiceImpl implements IBlogService {

	@Autowired
	BlogRepository blogRepository;

	@Override
	public List<Blog> getAllBlogs() {
		return blogRepository.findAll();
	}

	@Override
	public Blog getEmptyModel() {
		return new Blog();
	}

	@Override
	public Blog getBlog(Long id) {
		Optional<Blog> Blog = blogRepository.findById(id);
		return Blog.isPresent() ? Blog.get() : null;
	}

	@Override
	public Blog saveBlog(Blog blog) {
		blogRepository.save(blog);
		return blog;
	}

	@Override
	public void removeBlog(Blog blog) {
		blogRepository.delete(blog);
	}

}
