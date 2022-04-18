package hu.luterdav.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.luterdav.model.SchoolUser;
import hu.luterdav.repositories.SchoolUserRepository;



@Service
public class SchoolUserDetailsService implements UserDetailsService {
	
	@Autowired
	SchoolUserRepository schoolUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SchoolUser schoolUser = schoolUserRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		
		
		return new User(username, schoolUser.getPassword(), 
				schoolUser.getRoles().stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
	}

}
