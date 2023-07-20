package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="almacenes")
public class Almacen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_almacen")
	private int idAlmacen;

	@Column(name="desc_almacen")
	private String descAlmacen;


	@OneToMany(mappedBy="almacen")
	private List<Producto> productos;


}