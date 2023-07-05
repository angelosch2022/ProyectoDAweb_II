package com.cibertec.proyectoDAWeb_ii.model;
import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the vw_inventarios database table.
 * 
 */
@Entity
@Data
@Table(name="vw_inventarios")
///@NamedQuery(name="VwInventario.findAll", query="SELECT v FROM VwInventario v")
public class VwInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String bulto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="`Fecha Inventario`")
	private Date fechaInventario;

	@Id
	@Column(name="id_inventario")
	private int idInventario;

	@Column(name="id_producto")
	private int idProducto;

	@Column(name="id_usuario")
	private int idUsuario;

	private int inventario;

	private String producto;

	private int saldo;

	private String sku;

	private String usuario;
	
	private String cliente;

}