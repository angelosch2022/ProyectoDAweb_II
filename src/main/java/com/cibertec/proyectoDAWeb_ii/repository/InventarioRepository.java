package com.cibertec.proyectoDAWeb_ii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.proyectoDAWeb_ii.model.Inventario;


public interface InventarioRepository extends JpaRepository<Inventario, Integer>{
	
}
