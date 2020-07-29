package com.laura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.laura.spring.model.Producto;
import com.laura.spring.repository.UsuarioRepositorio;
import com.laura.spring.service.ProductoServicio;
import com.laura.spring.storage.StorageService;

@Controller
@RequestMapping("/producto")
public class ProductosController {

	@Autowired
	ProductoServicio productoServicio;

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	@Autowired
	StorageService storageService;

	@GetMapping("/lista")
	public String listaProductos(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		
		model.addAttribute("productos", productoServicio.listaProductosUsuario(usuarioRepositorio.getOne(username)));
		return "producto/lista";
	}

	@GetMapping("/form")
	public String formProducto(Model model) {
		model.addAttribute("producto", new Producto());
		return "producto/form";
	}

	@PostMapping("/nuevo")
	public String nuevoProducto(@ModelAttribute Producto producto, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			String name = storageService.store(file);
			String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(name).toUriString();
			producto.setImagen(uri);
		}
		productoServicio.addProducto(producto);
		return "redirect:/producto/lista";
	}

	// cambiar de método (lista.html)
	@GetMapping("/{id}/eliminar")
	public String eliminarProducto(@PathVariable Long id) {
		productoServicio.deleteProducto(id);
		return "redirect:/producto/lista";
	}

}
