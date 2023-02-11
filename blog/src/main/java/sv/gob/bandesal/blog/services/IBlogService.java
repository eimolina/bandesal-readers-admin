package sv.gob.bandesal.blog.services;

import java.util.List;

import sv.gob.bandesal.blog.domain.Blog;

public interface IBlogService {

	public List<Blog> getAllBlogs();
	public Blog getEmptyModel();
	public Blog getBlog(Long id);
	public Blog saveBlog(Blog blog);
	public void removeBlog(Blog blog);
}
