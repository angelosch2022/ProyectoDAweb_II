package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Data
@Table(name="productos")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="desc_producto")
	private String descProducto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_ultimo_inventario")
	private Date fechaUltimoInventario;

	/*
	@Column(name="id_cliente")
	private int idCliente;*/
	
	@Id
	@Column(name="id_producto")
	private int idProducto;

	/*@Column(name="id_tipo_bulto")
	private int idTipoBulto;*/

	@Column(name="is_inventariado")
	private Object isInventariado;

	@Column(name="sku_producto")
	private String skuProducto;

	@Column(name="stock_producto")
	private int stockProducto;

	//bi-directional many-to-one association to Almacene
	@ManyToOne
	@JoinColumn(name="id_almacen")
	private Almacen almacen;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_bulto")
	private TipoBulto tipoBulto;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
}