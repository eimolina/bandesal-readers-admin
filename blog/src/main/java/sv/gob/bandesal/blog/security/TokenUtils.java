package sv.gob.bandesal.blog.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

	private final static String ACCESS_TOKEN_SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

	public static String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public static String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public static String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public static boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private static boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private static Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private static Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private static Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
