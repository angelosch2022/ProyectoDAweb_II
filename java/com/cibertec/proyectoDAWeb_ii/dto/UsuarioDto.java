package com.cibertec.proyectoDAWeb_ii.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
	

	
	@NotEmpty(message = "Ingrese apellido valido")
	private String ape_usuario;
	
	@NotEmpty(message = "Ingrese mail valido")
	@Email
	private String email_usuario;
	
	private int id_usuario;
	
	@NotEmpty(message = "Ingrese nombre valido")
	private String nom_usuario;

	
	@NotEmpty(message = "Ingrese contraseña valida")
	private String password_usuario;

}
