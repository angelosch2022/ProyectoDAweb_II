package com.cibertec.proyectoDAWeb_ii.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Rol;
import com.cibertec.proyectoDAWeb_ii.model.Usuario;
import com.cibertec.proyectoDAWeb_ii.repository.RolRepository;
import com.cibertec.proyectoDAWeb_ii.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RolRepository rolRepository;
	
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
	
	@Transactional
	public void Delete(Usuario obj) {
		//rolRepository.DeleteRolesByIdUsuario(obj.getIdUsuario());		
		repository.delete(obj);
	}
	
	public Usuario GetByUserName(String email){
		
		return repository.findByUsername(email);
	}
	
	public List<Usuario> GetAllByName(String filtro){
		return repository.findByNomUsuarioOrApeUsuario(filtro);
	}
	
	

}
