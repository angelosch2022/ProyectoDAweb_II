package com.cibertec.proyectoDAWeb_ii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.Inventario;
import com.cibertec.proyectoDAWeb_ii.model.Producto;

import java.util.List;


public interface InventarioRepository extends JpaRepository<Inventario, Integer>{
	
}
