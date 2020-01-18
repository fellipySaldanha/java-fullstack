package com.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.domain.Client;
import com.java.repositories.ClientRepository;
import com.java.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(client.getId(), client.getEmail(), client.getPassword(), client.getProfile());
	}

}
