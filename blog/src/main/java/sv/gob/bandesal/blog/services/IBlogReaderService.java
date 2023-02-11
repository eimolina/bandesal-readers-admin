package sv.gob.bandesal.blog.services;

import java.util.List;

import sv.gob.bandesal.blog.domain.BlogReader;
import sv.gob.bandesal.blog.domain.BlogReader.BlogReaderId;
import sv.gob.bandesal.blog.dto.BlogReadersDto;

public interface IBlogReaderService {
	public List<BlogReadersDto> getAllBlogReaders();
	public BlogReader getEmptyModel();
	public BlogReader getBlogReader(BlogReaderId id);
	public BlogReader saveBlogReader(BlogReader blogreader);
	public void removeBlogReader(BlogReader blogreader);
}
