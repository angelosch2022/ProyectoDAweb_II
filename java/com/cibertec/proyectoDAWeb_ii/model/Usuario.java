package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Data
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ape_usuario")
	private String apeUsuario;

	@Column(name="email_usuario")
	private String username;

	@Id
	@Column(name="id_usuario")
	private int idUsuario;

	@Column(name="nom_usuario")
	private String nomUsuario;

	@Column(name="password_usuario")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "roles_usuarios",
			joinColumns = {@JoinColumn(name ="id_usuario", referencedColumnName = "id_usuario")},
			inverseJoinColumns = {@JoinColumn(name = "id_rol", referencedColumnName = "id_rol")}
			)
	private List<Rol> roles = new ArrayList<>();
	
	public Usuario( String ape_usuario, String email_usuario, String nom_usuario, String password_usuario, List<Rol> roles) {
		this.apeUsuario= ape_usuario;
		this.username = email_usuario;
		this.nomUsuario = nom_usuario;
		this.password = password_usuario;
		this.roles = roles;
		
	}

}