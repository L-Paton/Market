package com.laura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.spring.model.Producto;
import com.laura.spring.model.Usuario;
import com.laura.spring.repository.UsuarioRepositorio;
import com.laura.spring.service.ProductoServicio;

@Controller
@RequestMapping("/producto")
public class ProductosController {

	@Autowired
	ProductoServicio productoServicio;
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	private Usuario usuario;
	
	@ModelAttribute("/")
	public void misProductos() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = usuarioRepositorio.findById(email).orElse(null);
	}
	
	@GetMapping("/lista")
	public String listaProductos(Model model) {
		model.addAttribute("productos", productoServicio.listaProductosUsuario(usuario));
		return "producto/lista";
	}
	
	@GetMapping("/form")
	public String formProducto(Model model){
		model.addAttribute("producto", new Producto());
		return "producto/form";
	}
	
	@PostMapping("/nuevo")
	public String nuevoProducto(@ModelAttribute Producto producto){
		productoServicio.addProducto(producto);
		return "redirect:/producto/lista";
	}
	
	//cambiar de método (lista.html)
	@GetMapping("/{id}/eliminar")
	public String eliminarProducto(@PathVariable Long id) {
		productoServicio.deleteProducto(id);
		return "redirect:/producto/lista";
	}
	
}
