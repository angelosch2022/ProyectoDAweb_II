package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Cliente;
import com.cibertec.proyectoDAWeb_ii.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> GetAll(){
//		List<Cliente> clientes = new ArrayList<Cliente>();
//		repository.findAll().forEach(x -> clientes.add(x));
//		
		return repository.findAll();
	}
	
	public Cliente Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Cliente Save(Cliente obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
	
	public List<Cliente> GetAllByName(String nombre){
		return repository.findByNomClienteContainingIgnoreCase(nombre);
	}

	
	
	public Page<Cliente> buscarPaginacion(int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		return repository.findAll(pageable);
	}
	
	public Page<Cliente> buscarPaginacionConFiltro(String nombreCliente, int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		return repository.findByNomClienteContainingIgnoreCase(nombreCliente, pageable);
	}
}
