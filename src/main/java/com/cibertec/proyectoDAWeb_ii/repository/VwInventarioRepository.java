package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.VwInventario;
import java.util.Date;


public interface VwInventarioRepository extends JpaRepository<VwInventario, Integer>{
	
	List<VwInventario> findByProductoOrCliente(String producto, String Cliente);
	
	List<VwInventario> findByIdProducto(int idProducto);
	
	@Query(value = "SELECT x.* FROM Vw_Inventarios x where x.id_producto=:idProducto and Date(x.fecha)=:fecha_Inventario", nativeQuery = true)
	List<VwInventario> findByFechaInventario(int idProducto, Date fecha_Inventario);
	
}
