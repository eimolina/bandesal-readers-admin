package sv.gob.bandesal.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sv.gob.bandesal.blog.domain.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
	
	@Query("Select distinct x from Reader x left join x.blogsreaders y where y.blog.id is null or y.blog.id != :blogid")
	public List<Reader> getReadersNotInBlog(@Param("blogid") Long id);

}
