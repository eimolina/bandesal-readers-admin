package sv.gob.bandesal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.gob.bandesal.blog.domain.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
