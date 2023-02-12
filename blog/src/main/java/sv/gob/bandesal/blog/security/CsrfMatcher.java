package sv.gob.bandesal.blog.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;

public class CsrfMatcher implements RequestMatcher{

	@Override
	public boolean matches(HttpServletRequest request) {
        if (request.getRequestURL().indexOf("api") != -1)
            return false;
        return true;
	}

}
