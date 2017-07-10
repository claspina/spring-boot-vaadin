package net.claspina.demo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import net.claspina.demo.backend.UserRepository;
import net.claspina.demo.backend.data.entity.User;
import net.claspina.demo.backend.data.entity.UserAuthority;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {

			List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
			for(UserAuthority role : user.getAuthorities()){
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthorityPK().getAuthority()));
			}

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}
	}
}