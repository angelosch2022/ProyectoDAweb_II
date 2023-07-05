package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.proyectoDAWeb_ii.model.VwInventario;
import java.util.Date;


public interface VwInventarioRepository extends JpaRepository<VwInventario, Integer>{
	
	List<VwInventario> findByProductoOrCliente(String producto, String Cliente);
	
	List<VwInventario> findByIdProducto(int idProducto);
	
	
	List<VwInventario> findByFechaInventario(Date fecha_Inventario);
	
}
