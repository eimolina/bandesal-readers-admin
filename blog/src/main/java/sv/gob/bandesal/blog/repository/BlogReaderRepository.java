package sv.gob.bandesal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.gob.bandesal.blog.domain.BlogReader;
import sv.gob.bandesal.blog.domain.BlogReaderId;

public interface BlogReaderRepository extends JpaRepository<BlogReader, BlogReaderId> {

}
