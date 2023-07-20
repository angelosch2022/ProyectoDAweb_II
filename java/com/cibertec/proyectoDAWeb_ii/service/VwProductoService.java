package com.cibertec.proyectoDAWeb_ii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return repository.findByProductsOrClientes(filtro, filtro);
	}	
	
	public List<VwProducto> GetProductsByIdCustomerAndDescProduct(int id, String producto){
		return repository.findByIdClienteAndProducto(id, producto);
	}
	
	public List<VwProducto> GetProductsByIdCustomer(int id){
		return repository.findByIdCliente(id);
	}
		
	
	
	/******PAGINACION********/
	public Page<VwProducto> GetAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Page<VwProducto> GetAllInventoryByProductOrCustomer(String filtro, Pageable pageable){
		return repository.findByProductsOrClientes(filtro, filtro, pageable);
	}
	
	public Page<VwProducto> GetProductsByIdCustomerAndDescProduct(int id, String producto, Pageable pageable){
		return repository.findByIdClienteAndProducto(id, producto, pageable);
	}
	
	public Page<VwProducto> GetProductsByIdCustomer(int id, Pageable pageable){
		return repository.findByIdCliente(id, pageable);
	}
	
}
