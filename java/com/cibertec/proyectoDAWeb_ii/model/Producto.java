package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
//@Data
@Table(name="productos")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="desc_producto")
	private String descProducto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_ultimo_inventario")
	private Date fechaUltimoInventario;


	@Id
	@Column(name="id_producto")
	private int idProducto;


	@Column(name="is_inventariado")
	private boolean isInventariado;

	@Column(name="sku_producto")
	private String skuProducto;

	@Column(name="stock_producto")
	private int stockProducto;

	@ManyToOne
	@JoinColumn(name="id_almacen")
	private Almacen almacen;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_bulto")
	private TipoBulto tipoBulto;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	public String getDescProducto() {
		return descProducto;
	}

	public Date getFechaUltimoInventario() {
		return fechaUltimoInventario;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public boolean isInventariado() {
		return isInventariado;
	}

	public String getSkuProducto() {
		return skuProducto;
	}

	public int getStockProducto() {
		return stockProducto;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public TipoBulto getTipoBulto() {
		return tipoBulto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	public void setFechaUltimoInventario(Date fechaUltimoInventario) {
		this.fechaUltimoInventario = fechaUltimoInventario;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setIsInventariado(boolean isInventariado) {
		this.isInventariado = isInventariado;
	}

	public void setSkuProducto(String skuProducto) {
		this.skuProducto = skuProducto;
	}

	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public void setTipoBulto(TipoBulto tipoBulto) {
		this.tipoBulto = tipoBulto;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}