package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


/**
 * The persistent class for the almacenes database table.
 * 
 */
@Entity
@Data
@Table(name="almacenes")
//@NamedQuery(name="Almacen.findAll", query="SELECT a FROM Almacen a")
public class Almacen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_almacen")
	private int idAlmacen;

	@Column(name="desc_almacen")
	private String descAlmacen;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="almacen")
	private List<Producto> productos;


}