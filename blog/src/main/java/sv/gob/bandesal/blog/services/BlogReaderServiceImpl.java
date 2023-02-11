package sv.gob.bandesal.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.bandesal.blog.domain.BlogReader;
import sv.gob.bandesal.blog.domain.BlogReader.BlogReaderId;
import sv.gob.bandesal.blog.dto.BlogReadersDto;
import sv.gob.bandesal.blog.repository.BlogReaderRepository;

@Service
public class BlogReaderServiceImpl implements IBlogReaderService {

	@Autowired
	BlogReaderRepository blogReaderRepository;

	@Override
	public List<BlogReadersDto> getAllBlogReaders() {
		return blogReaderRepository.getAllGroupByBlogId();
	}

	@Override
	public BlogReader getEmptyModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogReader getBlogReader(BlogReaderId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogReader saveBlogReader(BlogReader blogreader) {
		blogReaderRepository.save(blogreader);
		return blogreader;
	}

	@Override
	public void removeBlogReader(BlogReader blogreader) {
		blogReaderRepository.delete(blogreader);
	}

}
