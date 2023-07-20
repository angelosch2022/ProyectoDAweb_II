package com.cibertec.proyectoDAWeb_ii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.proyectoDAWeb_ii.model.Usuario;
import com.cibertec.proyectoDAWeb_ii.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	UsuarioService userService;

	public static Usuario userLogin;

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@GetMapping("/")
	public String Home(Model model, @AuthenticationPrincipal User user) {
		if (user != null) {
			userLogin = userService.GetByUserName(user.getUsername());
			model.addAttribute("nomUsuario", userLogin.getNomUsuario() + " " + userLogin.getNomUsuario());
			
			log.info("Dentro de HOME "+user);
		} else {
			return "login";
		}

		return "home";
	}

	@GetMapping("/login")
	public String Login() {

		log.info("Dentro de Login");
		return "login";
	}

	@GetMapping("/error403")
	public String Error403() {

		log.info("Error403 acceso denegado");

		return "error403";
	}
	
	@PostMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("logout", true);
		
		log.info("Sesion cerrada");
		return "login";
	}
}
