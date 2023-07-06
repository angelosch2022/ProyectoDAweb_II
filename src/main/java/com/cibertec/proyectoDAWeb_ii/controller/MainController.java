package com.cibertec.proyectoDAWeb_ii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.proyectoDAWeb_ii.model.Usuario;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

	public static Usuario userLogin = new Usuario("Rivas", "josef@", 1, "Angelo", "123");
	
	@GetMapping("")
	public String Home() {
		
		log.info("Dentro de Index");
		
		return "home";
	}
}
