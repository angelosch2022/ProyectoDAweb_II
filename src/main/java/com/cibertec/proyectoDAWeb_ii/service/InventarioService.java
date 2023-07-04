package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Inventario;
import com.cibertec.proyectoDAWeb_ii.repository.InventarioRepository;

@Service
public class InventarioService {
	@Autowired
	private InventarioRepository repository;
	
	public List<Inventario> GetAll(){
		return (List<Inventario>)repository.findAll();
	}
	
	public Inventario Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Inventario Save(Inventario obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
}
