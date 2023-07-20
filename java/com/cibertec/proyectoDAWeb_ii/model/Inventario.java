package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the inventarios database table.
 * 
 */
@Entity
@Data
@Table(name="inventarios")
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inventario")
	private int idInventario;

	@Column(name="cant_inventario")
	private int cantInventario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inventario")
	private Date fechaInventario;

	@Column(name="saldo_producto")
	private int saldoProducto;

	
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
}