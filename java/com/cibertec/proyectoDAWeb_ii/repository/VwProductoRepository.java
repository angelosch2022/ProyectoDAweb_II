package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.VwProducto;

public interface VwProductoRepository extends JpaRepository<VwProducto, Integer>{
	
	@Query(value = "SELECT x.* FROM Vw_Productos x where x.cliente like %:cliente% or x.producto like %:producto%", nativeQuery = true)
	List<VwProducto> findByProductsOrClientes(String producto, String cliente);

	
	List<VwProducto> findByIdCliente(int idCliente);
	
	@Query(value = "SELECT x.* FROM Vw_Productos x where x.id_cliente=:idCliente and x.producto like %:producto%", nativeQuery = true)
	List<VwProducto> findByIdClienteAndProducto(int idCliente, String producto);
	
	
	Page<VwProducto> findByIdCliente(int idCliente, Pageable pageable);
	
	@Query(
			value = "SELECT x.* FROM Vw_Productos x where x.id_cliente=:idCliente and x.producto like %:producto%",
			countQuery = "SELECT count(*) FROM Vw_Productos x where x.id_cliente=:idCliente and x.producto like %:producto%",
			nativeQuery = true)
	Page<VwProducto> findByIdClienteAndProducto(int idCliente, String producto, Pageable pageable);
	
	@Query(
			value = 
			"SELECT x.* FROM Vw_Productos x where x.cliente like %:cliente% or x.producto like %:producto%", 
			countQuery = "SELECT count(*) FROM Vw_Productos x where x.cliente like %:cliente% or x.producto like %:producto%",
			nativeQuery = true)
	Page<VwProducto> findByProductsOrClientes(String producto, String cliente, Pageable pageable);
	
	
}
