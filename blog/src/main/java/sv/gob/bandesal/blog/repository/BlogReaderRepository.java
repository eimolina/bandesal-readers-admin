package sv.gob.bandesal.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sv.gob.bandesal.blog.domain.BlogReader;
import sv.gob.bandesal.blog.domain.BlogReader.BlogReaderId;
import sv.gob.bandesal.blog.dto.BlogReadersDto;

public interface BlogReaderRepository extends JpaRepository<BlogReader, BlogReaderId> {
	
	@Query("select new sv.gob.bandesal.blog.dto.BlogReadersDto(x.blog.id, x.blog.title, x.blog.description, count(x.reader.id)) from BlogReader x group by x.blog.id, x.blog.title, x.blog.description")
	public List<BlogReadersDto> getAllGroupByBlogId();

}
