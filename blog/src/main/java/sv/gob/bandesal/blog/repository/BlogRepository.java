package sv.gob.bandesal.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sv.gob.bandesal.blog.domain.Blog;
import sv.gob.bandesal.blog.dto.BlogReadersDto;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	@Query("select x from Blog x left join x.blogsreaders y where y.blog.id is null")
	public List<Blog> getBlogsWithoutReaders();

	@Query("select new sv.gob.bandesal.blog.dto.BlogReadersDto(x.id, x.title, x.description, count(y.reader.id)) from Blog x left join x.blogsreaders y group by x.id, x.title, x.description")
	public List<BlogReadersDto> getAllGroupByBlogId();
}
