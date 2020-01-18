package com.java.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.domain.Address;
import com.java.domain.City;
import com.java.domain.Client;
import com.java.domain.enums.ClientType;
import com.java.dto.ClientDTO;
import com.java.dto.ClientNewDto;
import com.java.repositories.AddressRepository;
import com.java.repositories.ClientRepository;
import com.java.services.exceptions.DataIntegrityException;
import com.java.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	@Autowired
	private ClientRepository repository;

	@Autowired
	private AddressRepository addressRepository;
	
	public Client findById(Integer id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));		
	}

	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		client = repository.save(client);
		addressRepository.saveAll(client.getAdresses());
		return client;
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
			throw new DataIntegrityException("Não é possível excluir pois há pedidos relacionadas");
		}		
	}

	public List<Client> findAll() {
		return repository.findAll();		
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Client fromDTO(ClientNewDto clientDto){
		Client client = new Client(null, clientDto.getName(), clientDto.getEmail(), clientDto.getCpfOrCnpj(), ClientType.toEnum(clientDto.getType()), encodePassword.encode(clientDto.getPassword()));
		City city = new City(clientDto.getCityId(), null, null);
		Address address = new Address(null, clientDto.getPlace(), clientDto.getNumber(), clientDto.getComplement(), clientDto.getNeighborhood(), clientDto.getZipCode(), client, city);
		client.getAdresses().add(address);
		client.getPhones().add(clientDto.getPhone01());

		if (clientDto.getPhone02() != null){
			client.getPhones().add(clientDto.getPhone02());
		}

		if (clientDto.getPhone03() != null){
			client.getPhones().add(clientDto.getPhone03());
		}

		return client;
	}

	public Client fromDTO(ClientDTO clientDto){
		return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null, null);
	}
}
