package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{

	@Modifying
	@Query(value = "delete from roles where id_usuario = :idUsuario", nativeQuery = true)
	void DeleteRolesByIdUsuario(int idUsuario);
	
	Rol findByDescRol(String descRol);
	
}
