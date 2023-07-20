package com.cibertec.proyectoDAWeb_ii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.proyectoDAWeb_ii.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query(value="select x.* from usuarios x where x.nom_usuario like %:filtro% or x.ape_usuario like %:filtro%", nativeQuery = true)
	List<Usuario> findByNomUsuarioOrApeUsuario(String filtro);

	@Modifying
	@Query(value="delete from usuarios where id_usuario = :id", nativeQuery = true)
	void deleteUsuarioById(int id);
	
	Usuario findByUsername(String email_usuario);

}
