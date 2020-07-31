package com.laura.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.laura.spring.model.Producto;
import com.laura.spring.model.Usuario;
import com.laura.spring.repository.ProductoRepositorio;
import com.laura.spring.storage.StorageService;

@Service
public class ProductoServicio {

	@Autowired
	ProductoRepositorio repositorio;
	
	@Autowired
	UsuarioServicio usuarioServicio;
	
	@Autowired
	StorageService storageService;
	
	public Producto getProductoById(long id) {
		return repositorio.getOne(id);
	}
	
	public List<Producto> listaProductosUsuario(Usuario usuario){
		return repositorio.findByVendedor(usuario);
	}
	
	public Producto addProducto(Producto p) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		p.setVendedor(usuarioServicio.getUserById(username));
		return repositorio.save(p);
	}
	
	public void deleteProducto(long id) {
		if (!repositorio.getOne(id).getImagen().isEmpty())
				storageService.delete(repositorio.getOne(id).getImagen());
		repositorio.deleteById(id);
	}
}
