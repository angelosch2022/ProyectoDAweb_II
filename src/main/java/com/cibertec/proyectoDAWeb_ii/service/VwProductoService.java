package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.VwProducto;
import com.cibertec.proyectoDAWeb_ii.repository.VwProductoRepository;

@Service
public class VwProductoService {
	@Autowired
	private VwProductoRepository repository;
	
	public List<VwProducto> GetAll(){
		return (List<VwProducto>)repository.findAll();
	}
	
	public VwProducto Get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<VwProducto> GetAllInventoryByProductOrCustomer(String filtro){
		return repository.findByProductoOrCliente(filtro, filtro);
	}
	
	
	
	public List<VwProducto> GetProductsByIdCustomerAndDescProduct(int id, String producto){
		return repository.findByIdClienteAndProducto(id, producto);
	}
	
	public List<VwProducto> GetProductsByIdCustomer(int id){
		return repository.findByIdCliente(id);
	}
	
	public List<VwProducto> GetProductsByDescription(String producto){
		return repository.findByProducto(producto);
	}
}
