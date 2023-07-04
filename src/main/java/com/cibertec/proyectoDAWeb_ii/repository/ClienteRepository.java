package com.cibertec.proyectoDAWeb_ii.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.proyectoDAWeb_ii.model.Cliente;
import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	 
	
	Page<Cliente> findByNomClienteContainingIgnoreCase(String nombreCliente, Pageable pageable);
	
	List<Cliente> findByNomClienteContainingIgnoreCase(String nomCliente);

}
