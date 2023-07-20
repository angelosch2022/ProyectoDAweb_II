package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.VwInventario;
import java.util.Date;


public interface VwInventarioRepository extends JpaRepository<VwInventario, Integer>{
	
	@Query(value = "SELECT x.* FROM Vw_Inventarios x where x.producto like %:producto% or x.cliente like %:cliente%",
			countQuery =  "select count(*) FROM Vw_Inventarios where producto like %:producto% or cliente like %:cliente%",
			nativeQuery = true)
	Page<VwInventario> findByProductoOrCliente(String producto, String cliente, Pageable pageable);
	
	
	List<VwInventario> findByIdProducto(int idProducto);
	
	@Query(value = "SELECT x.* FROM Vw_Inventarios x where x.id_producto=:idProducto and Date(x.fecha)=:fecha_Inventario", nativeQuery = true)
	List<VwInventario> findByFechaInventario(int idProducto, Date fecha_Inventario);
	
	
	Page<VwInventario> findByIdProducto(int idProducto, Pageable pageable);
	
	@Query(
			value = "SELECT x.* FROM Vw_Inventarios x where x.id_producto=:idProducto and Date(x.fecha)=:fecha_Inventario", 
			countQuery = "SELECT count(*) FROM Vw_Inventarios x where x.id_producto=:idProducto and Date(x.fecha)=:fecha_Inventario",
			nativeQuery = true)
	Page<VwInventario> findByFechaInventario(int idProducto, Date fecha_Inventario, Pageable pageable);
	
}
