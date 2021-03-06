package hu.luterdav.security;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import hu.luterdav.config.SchoolConfigProperties;


@Service
public class JwtService {
	
	private static final String AUTH = "auth";
	
	@Autowired
	SchoolConfigProperties config;
	
	private String issuer;
	private Algorithm alg;
	
	@PostConstruct
	public void init() {
		issuer = config.getJwtData().getIssuer();
		
		try {
			alg = (Algorithm) Algorithm.class.getMethod(config.getJwtData().getAlg(), String.class)
					.invoke(Algorithm.class, config.getJwtData().getSecret());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public String creatJwtToken(UserDetails principal) {
		return JWT.create()
			.withSubject(principal.getUsername())
			.withArrayClaim(AUTH, principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
			.withExpiresAt(new Date(System.currentTimeMillis() + config.getJwtData().getDuration().toMillis()))
			.withIssuer(issuer)
			.sign(alg);
		
	}

	public UserDetails parseJwt(String jwtToken) {
		
		DecodedJWT decodedJwt = JWT.require(alg)
			.withIssuer(issuer)
			.build()
			.verify(jwtToken);
		return new User(decodedJwt.getSubject(), "dummy", 
				decodedJwt.getClaim(AUTH).asList(String.class)
				.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
				);
		
	}

}
