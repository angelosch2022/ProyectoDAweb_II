package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Data
@Table(name="usuarios")
//@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ape_usuario")
	private String apeUsuario;

	@Column(name="email_usuario")
	private String emailUsuario;

	@Id
	@Column(name="id_usuario")
	private int idUsuario;

	@Column(name="nom_usuario")
	private String nomUsuario;

	@Column(name="password_usuario")
	private String passwordUsuario;

}