package com.laura.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laura.spring.model.Compra;
import com.laura.spring.model.Producto;
import com.laura.spring.model.Usuario;
import com.laura.spring.repository.CompraRepositorio;

@Service
public class CompraServicio {

	@Autowired
	CompraRepositorio repositorio;
	
	public void addToCarrito(Compra compra) {
		repositorio.save(compra);
	}
	
	public void deleteFromCarrito(long id) {
		repositorio.delete(repositorio.getOne(id));
	}
	
	public List<Compra> findByComprador(Usuario usuario) {
		return repositorio.findByComprador(usuario);
	}
	
	public List<Compra> findByProducto(Producto producto){
		return repositorio.findByProducto(producto);
	}
	
}
