package sv.gob.bandesal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.gob.bandesal.blog.domain.UserRole;
import sv.gob.bandesal.blog.domain.UserRole.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId>{

}
