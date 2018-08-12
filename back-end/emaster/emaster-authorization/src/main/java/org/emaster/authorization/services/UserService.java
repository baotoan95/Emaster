package org.emaster.authorization.services;

import java.util.Arrays;
import java.util.List;

import org.emaster.authorization.dal.UserDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
	@Autowired
	private UserDAL userDAL;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Login with username {}", username);
		try {
			UserDto user = userDAL.findByUserEmail(username);
			if(user == null){
				throw new UsernameNotFoundException("Invalid username or password.");
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
		} catch (PortalException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
}