package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the vw_productos database table.
 * 
 */
@Entity
@Data
@Table(name="vw_productos")
public class VwProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String almacen;

	private String bulto;

	private String cliente;

	@Column(name="id_almacen")
	private int idAlmacen;

	@Column(name="id_cliente")
	private int idCliente;

	@Id
	@Column(name="id_producto")
	private int idProducto;

	@Column(name="id_tipo_bulto")
	private int idTipoBulto;

	private boolean inventariado;

	private String producto;

	private int saldo;

	private String sku;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="`Ultimo Inventario`")
	private Date ultimo_Inventario;

}