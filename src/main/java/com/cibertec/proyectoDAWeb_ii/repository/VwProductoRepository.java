package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.VwProducto;

public interface VwProductoRepository extends JpaRepository<VwProducto, Integer>{
	
	List<VwProducto> findByProductoOrCliente(String Producto, String Cliente);
	
	/*@Query(value = "SELECT x FROM VwProducto x where x.idCliente=:id", nativeQuery = true)
	List<VwProducto> findByIdCliente(int id);*/
	
	List<VwProducto> findByIdCliente(int idCliente);
	
	@Query(value = "SELECT x.* FROM Vw_Productos x where x.id_cliente=:idCliente and x.producto like %:producto%", nativeQuery = true)
	List<VwProducto> findByIdClienteAndProducto(int idCliente, String producto);
	
	List<VwProducto> findByProducto(String producto);
}
