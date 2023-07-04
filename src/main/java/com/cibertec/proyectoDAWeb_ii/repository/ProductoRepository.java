package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.proyectoDAWeb_ii.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	List<Producto> findByDescProducto(String filtro);
}
