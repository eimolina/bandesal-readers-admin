package sv.gob.bandesal.blog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class SecurityUserDetails implements UserDetails	 {
	
	private String username;
	private String name;
	private String password;
	private String status;
	private Collection<SimpleGrantedAuthority> authorities;
	
	public SecurityUserDetails(String name, String username, String password, String status, Collection<SimpleGrantedAuthority> authorities) {
		this.name = name;
		this.username = username;
		this.authorities = authorities;
		this.status = status;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return status != null && status.equals("ALTA");
	}
	
	public String getName() {
		return this.name;
	}

}
