package com.cibertec.proyectoDAWeb_ii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.proyectoDAWeb_ii.model.Usuario;
import com.cibertec.proyectoDAWeb_ii.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UsuarioController {
	@Autowired
	UsuarioService service;

	// Mostrar página de listado registros
	@GetMapping(value = "/mantenimientos/usuarios")
	public String listar(
			Model model,  
			@RequestParam(value="msj",required = false) String msj,
			@RequestParam(value = "filtro", required = false)String filtro) {

		if(filtro!= null && !filtro.isEmpty()) {			
			model.addAttribute("usuarios", service.GetAllByName(filtro));
			model.addAttribute("filtro", filtro);			
		}else {
			model.addAttribute("usuarios", service.GetAll());
		}
		//model.addAttribute("msj", msj);

		return "mantenimientos/usuarios";
	}

	// Mostrar Vista para Formulario de agregar registro
	@GetMapping(value = "/mantenimientos/modificarUsuario")
	public String agregar(Model model) {
		var obj = new Usuario();
		
		model.addAttribute("usuario", obj);
		
		return "mantenimientos/modificarUsuario";
	}

	// Guardar registro
	@PostMapping(value = "/mantenimientos/guardarUsuario")
	public String guardar(
			@ModelAttribute("usuario") Usuario obj, 
			RedirectAttributes redirect, 
			Model model) {

		try {
			
			var nuevo = service.Save(obj);

			if (nuevo != null) {
				
				log.info("Registrado correctamente");

			} else {
				model.addAttribute("msj", "Error al registrar");

				log.error("Error al registrar");
				
				return "mantenimientos/guardarUsuario";
			}

		} catch (Exception e) {
			log.error("Error al registrar: " + e.getMessage());
			model.addAttribute("msj", "Error al registrar");
			model.addAttribute("msjTitle", "Error");
			model.addAttribute("errorMsj", true);
			
			return "mantenimientos/modificarUsuario";
		}

		redirect.addFlashAttribute("msj", "Se registró correctamente, nombre: "+ obj.getNomUsuario()+" "+ obj.getApeUsuario());
		return "redirect:/mantenimientos/listarUsuarios";
	}

	// Mostrar Vista para Formulario modificar registro
	@GetMapping(value = "/mantenimientos/modificarUsuario/{id}")
	public String modificar(Model model, @PathVariable(value = "id") int id) {

		var obj = service.Get(id);

		if (obj == null) {
			model.addAttribute("msj", "ERROR, no se encontró el registro seleccionado");			
			return "mantenimientos/listarUsuarios";
		}

		model.addAttribute("usuario", obj);
		model.addAttribute("titleUsuario", "Modificar Usuario");
		model.addAttribute("nomUsuario", obj.getNomUsuario()+ " "+obj.getApeUsuario());

		return "mantenimientos/modificarUsuario";
	}

	// Eliminar registro
	@GetMapping(value = "/mantenimientos/eliminarUsuario/{idUsuario}")
	public String elminar(
			@PathVariable(name = "idUsuario", required = true)int idUsuario, 
			RedirectAttributes redirect, 
			Model model) {
		
		String nomUsuario = "";
		
		try {
			var usuario = service.Get(idUsuario);
			nomUsuario=usuario.getNomUsuario()+" "+usuario.getApeUsuario();
			
			log.error("INGRESÓ A ELIMINAR");
			
			service.Delete(usuario);
			
		}catch (Exception e) {
			model.addAttribute("msj", "ERROR, no se pudo eliminar el registro seleccionado");
			model.addAttribute("msjTitle", "Error");
			model.addAttribute("errorMsj", true);
			e.printStackTrace();
			log.error("Error al eliminar: \n "+ e.getMessage());
			return "mantenimientos/usuarios";
		}
		
		redirect.addFlashAttribute("msj","Se eliminó al usuario: "+nomUsuario);
		redirect.addFlashAttribute("msjTitle","Usuarios");
		return "redirect:/mantenimientos/usuarios";
	}
}
