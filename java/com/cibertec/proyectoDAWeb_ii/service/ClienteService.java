package com.cibertec.proyectoDAWeb_ii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Cliente;
import com.cibertec.proyectoDAWeb_ii.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	public Cliente Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Cliente Save(Cliente obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
	
	public Page<Cliente> GetAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Page<Cliente> GetAllByName(String nombreCliente, Pageable pageable){
		return repository.findByNomClienteContainingIgnoreCase(nombreCliente, pageable);
	}
}
