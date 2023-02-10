package sv.gob.bandesal.blog.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.gob.bandesal.blog.domain.ApplicationUser;
import sv.gob.bandesal.blog.repository.ApplicationUserRepository;
import sv.gob.bandesal.blog.security.SecurityUserDetails;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Long saveUser(ApplicationUser user) {
		String passwd = user.getPassword();
		String encodedPasswod = bCryptPasswordEncoder.encode(passwd);
		user.setPassword(encodedPasswod);
		user = applicationUserRepository.save(user);
		return user.getId();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser user = applicationUserRepository.findByUsernameAndStatus(username);
		Collection<SimpleGrantedAuthority> authorities = user.getUserroles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getRolename())).collect(Collectors.toList());
		return new SecurityUserDetails(user.getName(), user.getUsername(), user.getPassword(), user.getStatus(),
				authorities);
	}

}
