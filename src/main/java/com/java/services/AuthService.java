package com.java.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.domain.Client;
import com.java.repositories.ClientRepository;
import com.java.services.exceptions.ObjectNotFoundException;


@Service
public class AuthService {

	@Autowired
	private ClientRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Client client = clienteRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		client.setPassword(passwordEncoder.encode(newPass));

		clienteRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { 
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { 
			return (char) (rand.nextInt(26) + 65);
		}
		else { 
			return (char) (rand.nextInt(26) + 97);
		}
	}
}