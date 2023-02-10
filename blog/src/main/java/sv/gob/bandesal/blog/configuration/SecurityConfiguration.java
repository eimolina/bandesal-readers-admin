package sv.gob.bandesal.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import lombok.RequiredArgsConstructor;
import sv.gob.bandesal.blog.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserServiceImpl userDetailService;

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().csrfTokenRepository(csrfTokenRepository());
		http.authorizeHttpRequests().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico")
				.permitAll();
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").permitAll().defaultSuccessUrl("/home", true))
				.logout((logout) -> logout.permitAll().logoutSuccessUrl("/login").deleteCookies("JSESSIONID"))
				.rememberMe((remember_me) -> remember_me.key("61a2f34b98a46a3bac901b34fa8e41d7"))
				.authenticationProvider(authenticationProvider());

		return http.build();
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}
