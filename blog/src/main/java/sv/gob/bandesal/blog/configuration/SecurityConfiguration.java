package sv.gob.bandesal.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import lombok.RequiredArgsConstructor;
import sv.gob.bandesal.blog.security.JwtAuthenticationEntryPoint;
import sv.gob.bandesal.blog.security.JwtAuthenticationFilter;
import sv.gob.bandesal.blog.security.JwtAuthorizationFilter;
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
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailService)
				.passwordEncoder(passwordEncoder).and().build();
	}

	@Configuration
	@RequiredArgsConstructor
	@Order(1)
	public static class JwtConfigurationAdapter {

		private final JwtAuthorizationFilter jwtAuthorizationFilter;

		@Bean
		public AuthenticationEntryPoint authenticationEntryPoint() {
			return new JwtAuthenticationEntryPoint();
		}

		@Bean
		SecurityFilterChain securityFilterChain1(HttpSecurity http, AuthenticationManager authManager)
				throws Exception {
			JwtAuthenticationFilter JwtAuthenticationFilter = new JwtAuthenticationFilter();
			JwtAuthenticationFilter.setAuthenticationManager(authManager);
			JwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth");

			http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
			http.csrf().disable().securityMatcher("/api/**").authorizeHttpRequests().requestMatchers("/api/**")
					.authenticated().and().httpBasic().and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(JwtAuthenticationFilter)
					.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
			return http.build();
		}
	}

	@Configuration
	@Order(2)
	public static class FormConfigurationAdapter {

		@Bean
		SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {

			http.csrf().csrfTokenRepository(csrfTokenRepository()).and().securityMatcher("/central/**")
					.exceptionHandling().and()
					.authorizeHttpRequests((requests) -> requests
							.requestMatchers("/css/**", "/js/**", "/img/**", "/vendor/**", "/error", "/favicon.ico")
							.permitAll().requestMatchers("/**").authenticated())
					.formLogin((form) -> form.loginPage("/central/login").defaultSuccessUrl("/central/home"))
					.logout((logout) -> logout.logoutUrl("/central/logout").permitAll()
							.logoutSuccessUrl("/central/login").deleteCookies("JSESSIONID"))
					.rememberMe((remember_me) -> remember_me.key("61a2f34b98a46a3bac901b34fa8e41d7"));
			return http.build();
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}
	}

}
