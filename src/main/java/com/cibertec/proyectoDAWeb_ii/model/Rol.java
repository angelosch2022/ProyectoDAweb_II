package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Data
@Table(name="roles")
//@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol")
	private int idRol;

	@Column(name="desc_rol")
	private String descRol;

	@Column(name="id_usuario")
	private int idUsuario;

}