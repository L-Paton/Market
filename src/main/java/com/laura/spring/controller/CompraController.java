package com.laura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laura.spring.model.Compra;
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
	public String compras() {
		return "/compra/listado";
	}
	
	@GetMapping("/carrito")
	public String main(Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("carrito", compraServicio.findByComprador(usuarioServicio.getUserById(username)));
		return "/compra/carrito";
	}
	
	@PostMapping("/carrito/add")
	public String add(@RequestParam("id") long id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		Compra compra = new Compra(usuarioServicio.getUserById(username), productoServicio.getProductoById(id), false);
		compraServicio.addToCarrito(compra);
		return "redirect:/carrito";
	}
	
	@PostMapping("/carrito/delete")
	public String delete(@RequestParam("id") long id) {
		compraServicio.deleteFromCarrito(id);
		return "redirect:/carrito";
	}
}
