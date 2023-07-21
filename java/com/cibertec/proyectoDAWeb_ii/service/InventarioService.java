package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.proyectoDAWeb_ii.model.Inventario;
import com.cibertec.proyectoDAWeb_ii.model.Producto;
import com.cibertec.proyectoDAWeb_ii.repository.InventarioRepository;
import com.cibertec.proyectoDAWeb_ii.repository.ProductoRepository;

@Service
public class InventarioService {
	@Autowired
	private InventarioRepository repository;
	
	@Autowired
	private ProductoRepository prodRepository;
	
	public List<Inventario> GetAll(){
		return (List<Inventario>)repository.findAll();
	}
	
	public Inventario Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	@Transactional()
	public Inventario Save(Inventario inv, Producto prod) {
				
		prodRepository.save(prod);
		var inventario = repository.save(inv);
		
		return inventario;
	}
	
	@Transactional
	public void Delete(int id) {
				
		var producto = repository.findById(id).orElse(null).getProducto();
		
		repository.deleteById(id);
		
		var inventarios = repository.findByIdProducto(producto.getIdProducto());
		
		if(inventarios.size() == 0) {
			producto.setIsInventariado(false);			
			prodRepository.save(producto);	
		}			
	}
}
