package com.cibertec.proyectoDAWeb_ii.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.proyectoDAWeb_ii.model.Cliente;
import com.cibertec.proyectoDAWeb_ii.model.Inventario;
import com.cibertec.proyectoDAWeb_ii.model.VwInventario;
import com.cibertec.proyectoDAWeb_ii.model.VwProducto;
import com.cibertec.proyectoDAWeb_ii.service.ClienteService;
import com.cibertec.proyectoDAWeb_ii.service.InventarioService;
import com.cibertec.proyectoDAWeb_ii.service.ProductoService;
import com.cibertec.proyectoDAWeb_ii.service.VwInventarioService;
import com.cibertec.proyectoDAWeb_ii.service.VwProductoService;
import static com.cibertec.proyectoDAWeb_ii.controller.MainController.userLogin;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InventarioController {

	public int idClientegb;
	//public int idProductogb;

	@Autowired
	ClienteService clieService;

	@Autowired
	VwProductoService vwProductoService;

	@Autowired
	InventarioService invService;

	@Autowired
	VwInventarioService vwInventarioService;
	
	@Autowired
	ProductoService proService;

	// Mostrar página de listado clientes
	/*
	 * @GetMapping(value = "/inventarios/listarClientes") public String
	 * pageInicial(Model model) {
	 * 
	 * return listar(1,"", model); }
	 */
	/*
	 * @GetMapping(value = "/inventarios/listarClientes/page/{pageNumber}") public
	 * String listar(
	 * 
	 * @PathVariable(value = "pageNumber") int pageNumber,
	 * 
	 * @RequestParam(value = "filtro", required = false) String nombre, Model model)
	 * {
	 * 
	 * int pageSize = 4;//Tamanio de elementos por pagina Page<Cliente> page;
	 * 
	 * if(nombre != null && !nombre.isEmpty()) { page =
	 * clieService.buscarPaginacionConFiltro(nombre,pageNumber, pageSize); }else {
	 * page = clieService.buscarPaginacion(pageNumber, pageSize); }
	 * 
	 * var clientes = page.getContent();
	 * 
	 * model.addAttribute("listaClientes", clientes);
	 * model.addAttribute("paginaActual", pageNumber);
	 * model.addAttribute("totalPaginas", page.getTotalPages());
	 * model.addAttribute("totalClientes", page.getTotalElements());
	 * 
	 * 
	 * for (var c : clientes) { log.info(c.getNomCliente()); }
	 * 
	 * model.addAttribute("listaClientes", clientes);
	 * 
	 * return "inventarios/listarClientes"; }
	 */
	
	
	
	/*****
	 * INVENTARIO PROPDUCTOS POR CLIENTE
	 * ****/
	
	
	@GetMapping(value = "/inventarios/listarClientes")
	public String pageInicial(@RequestParam(value = "filtro", required = false) String filtro, Model model) {
		List<Cliente> clientes;

		if (filtro != null && !filtro.isEmpty()) {
			clientes = clieService.GetAllByName(filtro);
		} else {
			clientes = clieService.GetAll();
		}

		model.addAttribute("clientes", clientes);

		return "inventarios/listarClientes";
	}

	
	
	// mostrar pagina listado de prodcutos por cliente
	@GetMapping(value = "/inventarios/listarProductos/{idCliente}")
	public String listarProductos(
			@PathVariable(name = "idCliente") Integer id, 
			Model model,
			@RequestParam(value = "filtro", required = false) String filtro) {

		List<VwProducto> productos;

		if (filtro != null && !filtro.isEmpty()) {
			productos = vwProductoService.GetProductsByIdCustomerAndDescProduct(id, filtro);
		} else {
			productos = vwProductoService.GetProductsByIdCustomer(id);
		}
		
			
		var cliente = clieService.Get(id);

		model.addAttribute("inventario", new Inventario());
		model.addAttribute("idUserLogin", userLogin.getIdUsuario());
		model.addAttribute("fecha", new Date());
		model.addAttribute("productos", productos);
		model.addAttribute("idCliente", id);
		model.addAttribute("nomCliente", cliente.getNomCliente());
		
		return "inventarios/listarProductos";
	}
	
	

	// guardar inventario
	@PostMapping(value = "/inventarios/guardarInventario")
	public String guardarInventario(
			@ModelAttribute("inventario") Inventario inventario, 
			Model model, 
			@RequestParam(name = "idCliente", required = false)String idCliente) {

		try {
			inventario.setFechaInventario(new Date());
			
			var user = userLogin;
			inventario.setUsuario(user);
			
			
			var producto = proService.Get(inventario.getProducto().getIdProducto());
			producto.setFechaUltimoInventario(new Date());
			producto.setIsInventariado(true);
			
			var guardado = invService.Save(inventario, producto);			
			
			if (guardado != null) {
				
				model.addAttribute("msj",
						"Se registro el inventario del producto " + producto.getDescProducto() + " con "
								+ guardado.getCantInventario() + " "
								+ producto.getTipoBulto().getDescBulto());
			}
		} catch (Exception e) {

			model.addAttribute("msj", "Error al guardar inventario, intente nuevamente");
			log.error("ERROR inventario : " + e.getMessage());
			if(idCliente == null) {
				return "inventarios/listarProductos";
			}else {
				return "inventarios/listarProductos" + idCliente;
			}
		}

		if(idCliente == null) {
			log.info("id cliente nulo: "+idCliente);
			return "redirect:/inventarios/listarProductos";
		}else {
			return "redirect:/inventarios/listarProductos" + idCliente;
		}
	}
 
	
	
	
	@GetMapping(value = "/inventarios/listarDetalle/{idProducto}")
	public String listarDetalle(
			Model model, 
			@PathVariable(name = "idProducto") Integer idProd,
			@RequestParam(name = "filtro",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate filtro) {
		
		List<VwInventario> listado;
		
		if(filtro != null)
		{
			
			Date date = Date.from(filtro.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			log.info("FITLRO: "+ date);
			
			listado = vwInventarioService.GetAllInventoryByIdAndDate(idProd, date);
		}else {
			listado = vwInventarioService.GetAllInventoryByIdProduct(idProd);
		}
		
		var producto = proService.Get(idProd);
		
		for(var i : listado) {
			log.info("los i son: "+ i.getIdInventario() +" - "+ i.getIdProducto());
		}
		
		model.addAttribute("inventarios", listado);
		model.addAttribute("descProducto", producto.getDescProducto());
		model.addAttribute("inventario", new Inventario());
		model.addAttribute("idProducto", idProd);
		model.addAttribute("idCliente", producto.getCliente().getIdCliente());
		
		log.info("ID PRODUCTO PARA DETALLE: "+ idProd);
		
		
		return "/inventarios/listarDetalle";
	}

	
	// guardar inventario
		@PostMapping(value = "/inventarios/guardarInventarioDetalle")
		public String guardarInventarioDetalle(
				@ModelAttribute("inventario") Inventario inventario, 
				Model model) {

			try {
				
				inventario.setFechaInventario(new Date());
				
				var user = userLogin;
				inventario.setUsuario(user);
				
				
				var producto = proService.Get(inventario.getProducto().getIdProducto());
				producto.setFechaUltimoInventario(new Date());
				producto.setIsInventariado(true);
				
				var guardado = invService.Save(inventario, producto);			
				
				if (guardado != null) {
					
					model.addAttribute("msj",
							"Se modificó el inventario del producto corectamente" + producto.getDescProducto() + " con "
									+ guardado.getCantInventario() + " "
									+ producto.getTipoBulto().getDescBulto());
				}
			} catch (Exception e) {

				model.addAttribute("msj", "Error al guardar inventario, intente nuevamente");
				log.error("ERROR inventario : " + e.getMessage());
				return "inventarios/listarProductos/" + inventario.getProducto().getIdProducto();
			}

			return "redirect:/inventarios/listarDetalle/" + inventario.getProducto().getIdProducto();
		}
	 
		
	
	
		
		
		
		/*****
		 * INVENTARIO TODOS LO PRODUCTOS
		 * ****/
		// mostrar pagina listado de prodcutos por cliente
		@GetMapping(value = "/inventarios/listarProductos")
		public String listarProductosTodosClientes( 
				Model model,
				@RequestParam(value = "filtro", required = false) String filtro) {

			List<VwProducto> productos;

			if (filtro != null && !filtro.isEmpty()) {
				productos = vwProductoService.GetAllInventoryByProductOrCustomer(filtro);
			} else {
				productos = vwProductoService.GetAll();
			}
			
			model.addAttribute("inventario", new Inventario());
			model.addAttribute("idUserLogin", userLogin.getIdUsuario());
			model.addAttribute("fecha", new Date());
			model.addAttribute("productos", productos);
			
			return "inventarios/listarProductos";
		}

}
