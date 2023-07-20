package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="roles")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol")
	private int idRol;

	@Column(name="desc_rol")
	private String descRol;

	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;
	
	public Rol(String descRol, List<Usuario> usuarios) {
	this.descRol = descRol;
	this.usuarios = usuarios;
	}

	public Rol(String descRol) {
		this.descRol = descRol;
	}
	
}