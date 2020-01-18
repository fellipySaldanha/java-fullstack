package com.java.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.java.domain.Client;
import com.java.dto.ClientDTO;
import com.java.dto.ClientNewDto;
import com.java.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;
	
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> findById(@PathVariable Integer id){
    	Client client = service.findById(id);
    	return ResponseEntity.ok().body(client);
    			
	}	
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDto, @PathVariable Integer id){
        Client client = service.fromDTO(clientDto);
    	client.setId(id);
    	client = service.update(client);
    	return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	service.deleteById(id);
    	return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll(){
    	List<Client> categories = service.findAll();
    	List<ClientDTO> listDtos = categories.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    	return ResponseEntity.ok().body(listDtos);    			
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page, 
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
        @RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
        @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Client> categories = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> listDtos = categories.map(client -> new ClientDTO(client));
    	return ResponseEntity.ok().body(listDtos);    			
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDto clientDto){
        Client client = service.fromDTO(clientDto);
    	client = service.insert(client);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
    	return ResponseEntity.created(uri).build();
    }
    
}