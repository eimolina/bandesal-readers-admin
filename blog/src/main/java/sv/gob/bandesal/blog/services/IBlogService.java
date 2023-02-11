package sv.gob.bandesal.blog.services;

import java.util.List;

import sv.gob.bandesal.blog.domain.Blog;
import sv.gob.bandesal.blog.dto.BlogReadersDto;

public interface IBlogService {

	public List<Blog> getAllBlogs();
	public List<BlogReadersDto> getAllBlogReaders();
	public List<Blog> getBlogsWithoutReaders();
	public Blog getEmptyModel();
	public Blog getBlog(Long id);
	public Blog saveBlog(Blog blog);
	public void removeBlog(Blog blog);
}
