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

import com.cibertec.proyectoDAWeb_ii.model.Producto;
import com.cibertec.proyectoDAWeb_ii.service.ProductoService;
import com.cibertec.proyectoDAWeb_ii.service.VwProductoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductoController {

	@Autowired
	ProductoService service;

	@Autowired
	VwProductoService vwService;

	/*****************
	 * MANTENIMIENTO
	 ********************/
	// Mostrar página de listado registros
	@GetMapping(value = "/mantenimientos/productos")
	public String listar(Model model, 
			@ModelAttribute(name = "filtro") String filtro, 
			@RequestParam("msj") String msj) {

		if (filtro.isBlank() || filtro.isEmpty()) {
			model.addAttribute("listado", service.GetAll());
		} else {
			model.addAttribute("listado", service.GetAllProductsByDescription(filtro));
		}

		model.addAttribute("msj", msj);

		return "mantenimientos/productos.html";
	}

	// Mostrar Vista para Formulario de agregar registro
	@GetMapping(value = "/mantenimientos/modificarProducto")
	public String agregar(Model model) {
		Producto obj = new Producto();
		model.addAttribute("producto", obj);

		return "mantenimientos/modificarProducto";
	}

	// Guardar registro
	@PostMapping(value = "/mantenimientos/guardarProducto")
	public String guardar(
			@ModelAttribute("producto") Producto obj, 
			RedirectAttributes redirect, Model model) {

		try {
			var nuevo = service.Save(obj);

			if (nuevo != null) {

				log.info("Registrado correctamente");

			} else {
				model.addAttribute("msj", "Error al registrar");

				log.error("Error al registrar");

				return "/mantenimientos/agregarProducto";
			}

		} catch (Exception e) {
			log.error("Error al registrar" + e.getMessage());
			model.addAttribute("msj", "Error al registrar");
			return "mantenimientos/modificarProducto";
		}
	
		redirect.addFlashAttribute("msj", "Se registró correctamente, nombre: " + obj.getDescProducto());
		return "redirect:/mantenimientos/listarProductos";
	}

	// Mostrar Vista para Formulario modificar registro
	@GetMapping(value = "/mantenimientos/modificarProducto/{id}")
	public String modificar(Model model, @PathVariable(value = "id") int id) {

		var obj = service.Get(id);

		if (obj == null) {
			model.addAttribute("msj", "ERROR, no se encontró el registro seleccionado");
			return "/mantenimientos/listarProductos";
		}

		model.addAttribute("producto", obj);

		return "mantenimientos/modificarProducto";
	}

	// Eliminar registro
	@PostMapping(value = "/mantenimientos/eliminarProducto/{id}")
	public String elminar(
			@RequestParam(name = "id", required = true)String id, 
			RedirectAttributes redirect, 
			Model model) {
		
		try {
			service.Delete(Integer.parseInt(id));
			
		}catch (Exception e) {
			model.addAttribute("msj", "ERROR, no se pudo eliminar el registro seleccionado");
			log.error("Erro al eliminar: \n "+ e.getMessage());
			return "mantenimientos/listarProductos";
		}
		
		redirect.addFlashAttribute("msj","Se eliminó el registro seleccionado");
		return "redirect:/mantenimientos/listarProductos";
	}

	/*****************
 		INVENTARIOS
	 ********************/

}









