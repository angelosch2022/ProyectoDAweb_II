package com.cibertec.proyectoDAWeb_ii.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


	/*****
	 * INVENTARIO PROPDUCTOS POR CLIENTE
	 ****/

	@GetMapping(value = "/inventarios/listarClientes")
	public String listarClientes(
			@RequestParam(value = "filtro", required = false) String filtro, 
			Model model,
			Pageable pageable) {
		
		Page<Cliente> clientes;
		Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), 5);
			    	

		if (filtro != null && !filtro.isEmpty()) {
			filtro = filtro.trim();
			clientes = clieService.GetAllByName(filtro, pageRequest);
		} else {
			clientes = clieService.GetAll(pageRequest);
		}

		model.addAttribute("page", clientes);
	    model.addAttribute("filtro", filtro);
	    model.addAttribute("nomUsuario", userLogin.getNomUsuario() + " " + userLogin.getApeUsuario());
	    
		return "inventarios/listarClientes";
	}

	// mostrar pagina listado de prodcutos por cliente
	@GetMapping(value = "/inventarios/listarProductos/{idCliente}")
	public String listarProductos(
			@PathVariable(name = "idCliente") Integer id, 
			Model model,
			@RequestParam(value = "filtro", required = false) String filtro,
			Pageable pageable) {

	
		Page<VwProducto> productos;
		Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), 5);

		if (filtro != null && !filtro.isEmpty()) {
			filtro = filtro.trim();
			productos = vwProductoService.GetProductsByIdCustomerAndDescProduct(id, filtro, pageRequest);
		} else {
			productos = vwProductoService.GetProductsByIdCustomer(id, pageRequest);
		}

		var cliente = clieService.Get(id);

		model.addAttribute("page", productos);
		model.addAttribute("inventario", new Inventario());
		model.addAttribute("idUserLogin", userLogin.getIdUsuario());
		model.addAttribute("fecha", new Date());
		model.addAttribute("idCliente", id);
		model.addAttribute("nomCliente", cliente.getNomCliente());
		model.addAttribute("filtro", filtro);
		model.addAttribute("nomUsuario", userLogin.getNomUsuario() + " " + userLogin.getApeUsuario());
		
		return "inventarios/listarProductos";
	}

	// guardar inventario
	@PostMapping(value = "/inventarios/guardarInventario")
	public String guardarInventario(@ModelAttribute("inventario") Inventario inventario, Model model,
			@RequestParam(name = "idCliente", required = false) String idCliente,
			RedirectAttributes redirectAttributes) {

		try {
			inventario.setFechaInventario(new Date());

			var user = userLogin;
			inventario.setUsuario(user);

			var producto = proService.Get(inventario.getProducto().getIdProducto());
			producto.setFechaUltimoInventario(new Date());
			producto.setIsInventariado(true);

			var guardado = invService.Save(inventario, producto);

			/*****************************************/
			if (guardado != null) {

				redirectAttributes.addFlashAttribute("msj",
						"Se registro el inventario del producto " + producto.getDescProducto() + " con "
								+ guardado.getCantInventario() + " " + producto.getTipoBulto().getDescBulto());
				redirectAttributes.addFlashAttribute("msjTitle", "Registro de Inventario");
			}
		} catch (Exception e) {

			model.addAttribute("msj", "Error al guardar inventario, intente nuevamente");
			model.addAttribute("errorMsj", true);
			model.addAttribute("msjTitle", "Error Registro de Inventario");

			log.error("ERROR inventario : " + e.getMessage());
			if (idCliente == null || idCliente.isEmpty()) {
				return "inventarios/listarProductos";
			} else {
				return "inventarios/listarProductos/" + idCliente;
			}
		}

		if (idCliente == null || idCliente.isEmpty()) {
			log.info("id cliente nulo: " + idCliente);
			return "redirect:/inventarios/listarProductos";
		} else {
			log.info("id cliente NO nulo: " + idCliente);
			return "redirect:/inventarios/listarProductos/" + idCliente;
		}
	}

	@GetMapping(value = "/inventarios/listarDetalle/{idProducto}")
	public String listarDetalle(
			Model model, @PathVariable(name = "idProducto") Integer idProd,
			@RequestParam(name = "filtro", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate filtro,
			Pageable pageable) {

		Page<VwInventario> listado;
		Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), 5);

		if (filtro != null) {
			Date date = Date.from(filtro.atStartOfDay(ZoneId.systemDefault()).toInstant());

			listado = vwInventarioService.GetAllInventoryByIdAndDate(idProd, date, pageRequest);
		} else {
			listado = vwInventarioService.GetAllInventoryByIdProduct(idProd, pageRequest);
		}

		var producto = proService.Get(idProd);

		model.addAttribute("page", listado);
		model.addAttribute("descProducto", producto.getDescProducto());
		model.addAttribute("inventario", new Inventario());
		model.addAttribute("idProducto", idProd);
		model.addAttribute("idCliente", producto.getCliente().getIdCliente());
		model.addAttribute("filtro", filtro);
		model.addAttribute("nomUsuario", userLogin.getNomUsuario() + " " + userLogin.getApeUsuario());
		
		return "/inventarios/listarDetalle";
	}

	// guardar inventario
	@PostMapping(value = "/inventarios/guardarInventarioDetalle")
	public String guardarInventarioDetalle(@ModelAttribute("inventario") Inventario inventario, Model model,
			RedirectAttributes redirectAttributes) {

		try {

			inventario.setFechaInventario(new Date());

			var user = userLogin;
			inventario.setUsuario(user);

			var producto = proService.Get(inventario.getProducto().getIdProducto());
			producto.setFechaUltimoInventario(new Date());
			producto.setIsInventariado(true);

			var guardado = invService.Save(inventario, producto);

			if (guardado != null) {
				redirectAttributes.addFlashAttribute("msj",
						"Se modificó el inventario del producto: " + producto.getDescProducto() + " con "
								+ guardado.getCantInventario() + " " + producto.getTipoBulto().getDescBulto());

				redirectAttributes.addFlashAttribute("msjTitle", "Modificación de Inventario");
			}
		} catch (Exception e) {

			model.addAttribute("msj", "Error al guardar inventario, intente nuevamente");
			model.addAttribute("erroMsj", true);
			model.addAttribute("msjTitle", "Error Modificar Inventario");

			log.error("ERROR inventario : " + e.getMessage());
			return "inventarios/listarProductos/" + inventario.getProducto().getIdProducto();
		}

		return "redirect:/inventarios/listarDetalle/" + inventario.getProducto().getIdProducto();
	}

	// guardar inventario
	@PostMapping(value = "/inventarios/eliminar")
	public String eliminarInventario(Model model, @RequestParam(name = "idCliente", required = false) String idCliente,
			@RequestParam(name = "idInventario", required = true) int idInventario,
			RedirectAttributes redirectAttributes) {
		
		var inventario = vwInventarioService.Get(idInventario);
		
		var descProducto= inventario.getProducto();
		
		try {

			invService.Delete(idInventario);

			redirectAttributes.addFlashAttribute("msj", "Se eliminó el inventario del producto: " + descProducto);
			redirectAttributes.addFlashAttribute("msjTitle", "Inventario eliminado");
			
		} catch (Exception e) {

			model.addAttribute("msj", "Error al eliminar inventario, intente nuevamente");
			model.addAttribute("msjTitle", "Error Eliminar Inventario");
			model.addAttribute("errorMsj", true);

			log.error("ERROR inventario : " + e.getMessage());
			if (idCliente == null || idCliente.isEmpty()) {
				return "inventarios/listarProductos";
			} else {
				return "inventarios/listarProductos/" + idCliente;
			}
		}

		if (idCliente == null || idCliente.isEmpty()) {
			log.info("id cliente nulo: " + idCliente);
			return "redirect:/inventarios/listarDetalle";
		} else {
			log.info("id cliente NO nulo: " + idCliente);
			return "redirect:/inventarios/listarDetalle/" + inventario.getIdProducto();
		}
	}

	/*****
	 * INVENTARIO TODOS LO PRODUCTOS
	 ****/
	// mostrar pagina listado de prodcutos por cliente
	@GetMapping(value = "/inventarios/listarProductos")
	public String listarProductosTodosClientes(
			Model model,
			@RequestParam(value = "filtro", required = false) String filtro,
			Pageable pageable) {

	
		Page<VwProducto> productos;
		Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), 5);

		if (filtro != null && !filtro.isEmpty()) {
	        productos = vwProductoService.GetAllInventoryByProductOrCustomer(filtro, pageRequest);
	    } else {
	        productos = vwProductoService.GetAll(pageRequest);
	    }

	    model.addAttribute("inventario", new Inventario());
	    model.addAttribute("idUserLogin", userLogin.getIdUsuario());
	    model.addAttribute("fecha", new Date());
	    model.addAttribute("page", productos);
	    model.addAttribute("filtro", filtro);
	    model.addAttribute("nomUsuario", userLogin.getNomUsuario() + " " + userLogin.getApeUsuario());
	    
		return "inventarios/listarProductos";
	}
	
	
	
	
	/*****
	 * CONSULTA DE INVENTARIOS
	 ****/

	@GetMapping(value = "/inventarios/consultaInventarios")
	public String consultaInventarios(
	        @RequestParam(value = "filtro", required = false) String filtro,
	        Model model,
	        Pageable pageable
	) {
	    Page<VwInventario> inventarios;
	    Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), 5);
	    
		if(filtro!=null)
			filtro = filtro.trim();

	    if (filtro != null && !filtro.isEmpty()) {
	        inventarios = vwInventarioService.GetAllInventoryByProductOrCustomer(filtro, pageRequest);
	    } else {
	    	
	        inventarios = vwInventarioService.GetAll(pageRequest);
	    }

	    model.addAttribute("page", inventarios);
	    model.addAttribute("filtro", filtro);
	    model.addAttribute("nomUsuario", userLogin.getNomUsuario() + " " + userLogin.getApeUsuario());
	    
	    return "inventarios/consultaInventarios";
	}

	
}
