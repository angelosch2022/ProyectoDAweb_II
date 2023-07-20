package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="contacto_cliente")
	private String contactoCliente;

	@Column(name="correo_cliente")
	private String correoCliente;

	@Column(name="dir_cliente")
	private String dirCliente;

	@Id
	@Column(name="id_cliente")
	private int idCliente;

	@Column(name="nom_cliente")
	private String nomCliente;

	@Column(name="imagen")
	private String imagen;

	@Column(name="ruc_cliente")
	private String rucCliente;


	@Column(name="tel_cliente")
	private String telCliente;


}