package com.laura.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Producto> listaProductosNoVendidos(){
		return repositorio.findByVendido(false);
	}
	
	public Producto addProducto(Producto p) {
		return repositorio.save(p);
	}
	
	public void deleteProducto(long id) {
		Producto p = repositorio.findById(id).orElse(null);
		String imagen = p.getImagen();
		
		if(p != null) {
			repositorio.deleteById(id);
			if (imagen != null && repositorio.findById(id).orElse(null) == null)
				storageService.delete(p.getImagen());
		}
	}
}
