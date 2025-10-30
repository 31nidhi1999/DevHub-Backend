package com.devhub.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtils {

    private final CustomeUserDetailsServiceImpl customeUserDetailsServiceImpl;
	@Value("${SECRET_KEY}")
	private String jwtSecret;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	private Key key;

    JwtUtils(CustomeUserDetailsServiceImpl customeUserDetailsServiceImpl) {
        this.customeUserDetailsServiceImpl = customeUserDetailsServiceImpl;
    }
	
	@PostConstruct
	private void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	//generate token for verified user
	public String generateJwtToken(Authentication authentication) {
		CustomUserDetails userPrincipal=(CustomUserDetails)authentication.getPrincipal();
		System.out.println("authorities" + userPrincipal.getAuthorities());
		System.out.println("user_id " + userPrincipal.getUsername());
		return Jwts
				.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date((new Date()).getTime() + jwtExpirationMs))
				.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				.claim("user_id",userPrincipal.getUser().getId())
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
		
	}

	public String getUserNameFromJwtToken(Claims claims) {
		return claims.getSubject();
	}

	public Claims validateJwtToken(String jwtToken) {
		// try {
		Claims claims = Jwts.parserBuilder() 
				.setSigningKey(key) 
				.build()
				.parseClaimsJws(jwtToken)
				.getBody();
		/*
		 * parseClaimsJws - 
		 * throws:UnsupportedJwtException -if the JWT body | payload does not represent any Claims 
		 * JWSMalformedJwtException - if the JWT body | payload is not a valid 
		 * JWSSignatureException - if the JWT signature validation fails
		 * ExpiredJwtException - if the specified JWT is expired 
		 * IllegalArgumentException - if the JWT claims body | payload is null or empty or only whitespace
		 */
		return claims;		
	}
	

	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream().
				map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
	}
	
		public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String)claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}
	
			public Long getUserIdFromJwtToken(Claims claims) {
				System.out.println("claims user id "+ claims.get("user_id"));
				return Long.valueOf((int)claims.get("user_id"));			
			}
			
			public Authentication populateAuthenticationTokenFromJWT(String jwt) {
				
				Claims payloadClaims = validateJwtToken(jwt);
				String email = getUserNameFromJwtToken(payloadClaims);
				List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);	
				Long userId=getUserIdFromJwtToken(payloadClaims);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,userId,
						authorities);
				System.out.println("is authenticated "+token.isAuthenticated());//true
				return token;
		
			}
	
}
