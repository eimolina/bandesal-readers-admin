package sv.gob.bandesal.blog.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException authException) throws IOException, ServletException {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", new Date().getTime());
		response.put("status", HttpServletResponse.SC_FORBIDDEN);
		response.put("message", "ACCESS_DENIED");
		response.put("detail", authException.getMessage());
		httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(httpServletResponse.getOutputStream(), response);
		httpServletResponse.getOutputStream().flush();
	}

}
