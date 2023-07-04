package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Usuario;
import com.cibertec.proyectoDAWeb_ii.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> GetAll(){
		return (List<Usuario>)repository.findAll();
	}
	
	public Usuario Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Usuario Save(Usuario obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
}
