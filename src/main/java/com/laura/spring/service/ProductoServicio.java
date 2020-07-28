package com.laura.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.spring.model.Producto;
import com.laura.spring.model.Usuario;
import com.laura.spring.repository.ProductoRepositorio;

@Service
public class ProductoServicio {

	@Autowired
	ProductoRepositorio repositorio;
	
	public List<Producto> listaProductosUsuario(Usuario usuario){
		return repositorio.findByVendedor(usuario);
	}
	
	public Producto addProducto(Producto p) {
		return repositorio.save(p);
	}
	
	public void deleteProducto(long id) {
		repositorio.deleteById(id);
	}
}
