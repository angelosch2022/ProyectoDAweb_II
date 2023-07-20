package com.cibertec.proyectoDAWeb_ii.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;


/**
 * The persistent class for the tipo_bultos database table.
 * 
 */
@Entity
@Data
@Table(name="tipo_bultos")
public class TipoBulto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_bulto")
	private int idTipoBulto;

	@Column(name="desc_bulto")
	private String descBulto;

	@Column(name="sigla_bulto")
	private String siglaBulto;
}