package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Producto;
import com.cibertec.proyectoDAWeb_ii.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository repository;
	
	public List<Producto> GetAll(){
		return (List<Producto>)repository.findAll();
	}
	
	public Producto Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Producto Save(Producto obj) {
		return repository.save(obj);
	}
	
	public void Delete(int id) {
		repository.deleteById(id);
	}
	
	public List<Producto> GetAllProductsByDescription(String filtro){
		return repository.findByDescProducto(filtro);
	}
}
