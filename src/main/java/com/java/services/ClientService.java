package com.java.services;

import java.util.List;
import java.util.Optional;

import com.java.domain.Client;
import com.java.repositories.ClientRepository;
import com.java.resources.ClientDTO;
import com.java.services.exceptions.DataIntegrityException;
import com.java.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Client findById(Integer id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));		
	}
	
	public Client update(Client client) {
		Client newClient = this.findById(client.getId());
		updateDate(newClient, client);
		return repository.save(newClient);
	}

	private void updateDate(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}

	public void deleteById(Integer id) {
		this.findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há entidades relacionadas");
		}		
	}

	public List<Client> findAll() {
		return repository.findAll();		
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO clientDto){
		return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null);
	}
}
