package com.cibertec.proyectoDAWeb_ii.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.VwInventario;
import com.cibertec.proyectoDAWeb_ii.repository.VwInventarioRepository;

@Service
public class VwInventarioService {
	@Autowired
	private VwInventarioRepository repository;
	
	public Page<VwInventario> GetAll( Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public VwInventario Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Page<VwInventario> GetAllInventoryByProductOrCustomer(String filtro, Pageable pageable){
		return repository.findByProductoOrCliente(filtro, filtro, pageable);
	}
	
	
	
	
	public List<VwInventario> GetAllInventoryByIdProduct(int id){
		return repository.findByIdProducto(id);
	}
	
	
	public List<VwInventario> GetAllInventoryByIdAndDate(int idProducto, Date fecha){
		return repository.findByFechaInventario(idProducto, fecha);
	}
	
	
	
	
	
	
	public Page<VwInventario> GetAllInventoryByIdProduct(int id, Pageable pageable){
		return repository.findByIdProducto(id, pageable);
	}
	
	
	public Page<VwInventario> GetAllInventoryByIdAndDate(int idProducto, Date fecha, Pageable pageable){
		return repository.findByFechaInventario(idProducto, fecha, pageable);
	}
	
}
