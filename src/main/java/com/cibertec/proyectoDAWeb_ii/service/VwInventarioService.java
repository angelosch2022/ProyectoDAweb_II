package com.cibertec.proyectoDAWeb_ii.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.VwInventario;
import com.cibertec.proyectoDAWeb_ii.repository.VwInventarioRepository;

@Service
public class VwInventarioService {
	@Autowired
	private VwInventarioRepository repository;
	
	public List<VwInventario> GetAll(){
		return (List<VwInventario>)repository.findAll();
	}
	
	public VwInventario Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<VwInventario> GetAllInventoryByProductOrCustomer(String filtro){
		return repository.findByProductoOrCliente(filtro, filtro);
	}
	
	
	
	
	public List<VwInventario> GetAllInventoryByIdProduct(int id){
		return repository.findByIdProducto(id);
	}
	
	
	public List<VwInventario> GetAllInventoryByDate(Date fecha){
		return repository.findByFechaInventario(fecha);
	}
	
}
