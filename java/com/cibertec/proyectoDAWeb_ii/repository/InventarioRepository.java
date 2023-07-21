package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.Inventario;


public interface InventarioRepository extends JpaRepository<Inventario, Integer>{
	
	@Query(value = "SELECT * FROM inventarios where id_producto = :idProducto", nativeQuery = true)
	public List<Inventario> findByIdProducto(int idProducto);
	
}
