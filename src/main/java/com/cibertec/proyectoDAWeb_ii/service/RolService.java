package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Rol;
import com.cibertec.proyectoDAWeb_ii.repository.RolRepository;

@Service
public class RolService {
	@Autowired
	private RolRepository repository;
	
	public List<Rol> GetAll(){
		return (List<Rol>)repository.findAll();
	}
	
	public Rol Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Rol Save(Rol obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
}
