package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Almacen;
import com.cibertec.proyectoDAWeb_ii.repository.AlmacenRepository;

@Service
public class AlmacenService {
	@Autowired
	private AlmacenRepository repository;
	
	public List<Almacen> GetAll(){
		return (List<Almacen>)repository.findAll();
	}
	
	public Almacen Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Almacen Save(Almacen obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
}
