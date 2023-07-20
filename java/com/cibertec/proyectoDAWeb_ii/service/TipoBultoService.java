package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.TipoBulto;
import com.cibertec.proyectoDAWeb_ii.repository.TipoBultoRepository;

@Service
public class TipoBultoService {
	@Autowired
	private TipoBultoRepository repository;
	
	public List<TipoBulto> GetAll(){
		return (List<TipoBulto>)repository.findAll();
	}
	
	public TipoBulto Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public TipoBulto Save(TipoBulto obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
}
