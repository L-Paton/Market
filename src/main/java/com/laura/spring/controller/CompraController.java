package com.laura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laura.spring.model.Compra;
import com.laura.spring.model.Usuario;
import com.laura.spring.service.CompraServicio;
import com.laura.spring.service.ProductoServicio;
import com.laura.spring.service.UsuarioServicio;

@Controller
public class CompraController {

	@Autowired 
	CompraServicio compraServicio;
	
	@Autowired
	ProductoServicio productoServicio;
	
	@Autowired 
	UsuarioServicio usuarioServicio;
	
	@GetMapping("/compras")
	public String compras(Model model) {
		model.addAttribute("compras", compraServicio.comprasFinalizadas(getUser()));
		return "/compra/listado";
	}
	
	@GetMapping("/compras/factura/{id}")
	public String facturaCompra(Model model, @PathVariable("id") long id) {
		model.addAttribute("compra", compraServicio.findById(id));
		return "/compra/factura";
	}
	
	@GetMapping("/carrito")
	public String main(Model model){
		model.addAttribute("carrito", compraServicio.comprasNoFinalizadas(getUser()));
		return "/compra/carrito";
	}
	
	@PostMapping("/carrito/add")
	public String add(@RequestParam("id") long id) {
		Compra compra = new Compra(getUser(), productoServicio.getProductoById(id), false);
		compraServicio.addToCarrito(compra);
		return "redirect:/carrito";
	}
	
	@PostMapping("/carrito/delete")
	public String delete(@RequestParam("id") long id) {
		compraServicio.deleteFromCarrito(id);
		return "redirect:/carrito";
	}
	
	@PostMapping("/carrito/finalizar")
	public String finalizar(Model model) {
		compraServicio.finalizarCompra(getUser());
		return "redirect:/compras";
	}
	
	private Usuario getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		return usuarioServicio.getUserById(username);
	}
}
