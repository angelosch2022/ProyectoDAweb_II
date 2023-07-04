package com.cibertec.proyectoDAWeb_ii.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.proyectoDAWeb_ii.model.Cliente;
import com.cibertec.proyectoDAWeb_ii.model.Inventario;
import com.cibertec.proyectoDAWeb_ii.service.ClienteService;
import com.cibertec.proyectoDAWeb_ii.service.InventarioService;
import com.cibertec.proyectoDAWeb_ii.service.VwInventarioService;
import com.cibertec.proyectoDAWeb_ii.service.VwProductoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InventarioController {

	public int idClientegb;
	public int idProductogb;

	@Autowired
	ClienteService clieService;

	@Autowired
	VwProductoService vwProductoService;

	@Autowired
	InventarioService invService;

	@Autowired
	VwInventarioService vwInventarioService;

	
	// Mostrar página de listado clientes
	/*
	@GetMapping(value = "/inventarios/listarClientes")
	public String pageInicial(Model model) {
		
		return listar(1,"", model);
	}
	*/
	/*
	@GetMapping(value = "/inventarios/listarClientes/page/{pageNumber}")
	public String listar(
			@PathVariable(value = "pageNumber") int pageNumber,
			@RequestParam(value = "filtro", required = false) String nombre, 
			Model model) {
		
		int pageSize = 4;//Tamanio de elementos por pagina
		Page<Cliente> page;
		
		if(nombre != null && !nombre.isEmpty()) {
			 page = clieService.buscarPaginacionConFiltro(nombre,pageNumber, pageSize);
		}else {
			 page = clieService.buscarPaginacion(pageNumber, pageSize);
		}
		
		var clientes = page.getContent();
		
		model.addAttribute("listaClientes", clientes);
		model.addAttribute("paginaActual", pageNumber);
		model.addAttribute("totalPaginas", page.getTotalPages());
		model.addAttribute("totalClientes", page.getTotalElements());
		
		
		for (var c : clientes) {
			log.info(c.getNomCliente());
		}
		
		model.addAttribute("listaClientes", clientes);

		return "inventarios/listarClientes";
	}
*/
	@GetMapping(value = "/inventarios/listarClientes")
	public String pageInicial(@RequestParam(value = "filtro", required = false) String nombre, Model model) {
		List<Cliente> clientes;
		if(nombre != null && !nombre.isEmpty()) {
			 clientes = clieService.GetAllByName(nombre);
		}else {
			 clientes = clieService.GetAll();
		}

		model.addAttribute("listaClientes", clientes);
	
		for (var c : clientes) {
			log.info(c.getNomCliente());
		}
		
		model.addAttribute("listaClientes", clientes);

		return "inventarios/listarClientes";
	}
	
	
	// mostrar pagina listado de prodcutos por cliente
	@GetMapping(value = "/inventarios/listarProductos/{idCliente}")
	public String listarProductos(@RequestParam(name = "idCliente") Integer id, Model model,
			@RequestParam("msj") String msj) {

		idClientegb = id;

		var productos = vwProductoService.GetProductsByIdCustomer(id);

		model.addAttribute("msj", msj);
		model.addAttribute("inventario", new Inventario());
		model.addAttribute("productos", productos);
		return "inventarios/listarProductos";
	}

	
	
	
	
	
	// guardar inventario
	@PostMapping(value = "/inventarios/guardarInventario")
	public String guardarInventario(@ModelAttribute("inventario") Inventario inventario, Model model) {

		try {
			var guardado = invService.Save(inventario);

			if (guardado != null) {
				model.addAttribute("msj",
						"Se registro el inventario del producto " + inventario.getProducto().getDescProducto() + " con "
								+ inventario.getCantInventario() + " "
								+ inventario.getProducto().getTipoBulto().getDescBulto());
			}
		} catch (Exception e) {

			model.addAttribute("msj", "Error al guardar inventario, intente nuevamente");
			log.error("ERROR inventario : " + e.getMessage());
			return "inventarios/listarProductos/{" + idClientegb + "}";
		}

		return "redirect:/inventarios/listarProductos/{" + idProductogb + "}";
	}

	
	
	
	
	@GetMapping(value = "/inventarios/listarDetalle/{idProd}")
	public String listarDetalle(Model model, @RequestParam(name = "idProd") Integer idProd) {
		idProductogb = idProd;
		var listado = vwInventarioService.GetAllInventoryByIdProduct(idProd);

		model.addAttribute("listado", listado);

		return "/inventarios/detalle";
	}
	
	
	
	

	@GetMapping(value = "/inventarios/detalle/{idDeta}")
	public String mostrarDetalle(Model model, @RequestParam(name = "idDeta") Integer idDeta) {

		var detalle = invService.Get(idDeta);

		model.addAttribute("detalle", detalle);

		return "/inventarios/detalle";
	}

}
