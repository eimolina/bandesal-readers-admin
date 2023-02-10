package sv.gob.bandesal.blog.services;

import sv.gob.bandesal.blog.domain.ApplicationUser;

public interface IUserService {
	public Long saveUser(ApplicationUser user);
}
