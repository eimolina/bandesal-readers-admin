package sv.gob.bandesal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.gob.bandesal.blog.domain.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{

}
