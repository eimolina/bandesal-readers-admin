package sv.gob.bandesal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sv.gob.bandesal.blog.domain.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	
	@Query("SELECT x FROM ApplicationUser x JOIN FETCH UserRole y WHERE x.username = :username and x.status = 'ALTA'")
	ApplicationUser findByUsernameAndStatus(@Param("username") String username);

}
