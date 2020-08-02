package com.laura.spring.service;

import java.util.ArrayList;
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
	
	public void finalizarCompra(Usuario usuario) {
		repositorio.findByComprador(usuario).forEach(c -> c.setFinalizada(true));
		repositorio.flush();
	}
	
	public Compra findById(long id) {
		return repositorio.getOne(id);
	}
	
	public List<Compra> comprasFinalizadas(Usuario usuario){
		List<Compra> lista = new ArrayList<>();
		repositorio.findByComprador(usuario).stream().filter(c -> c.isFinalizada()).forEach(c -> lista.add(c));
		return lista;
	}
	
	public List<Compra> comprasNoFinalizadas(Usuario usuario) {
		List<Compra> lista = new ArrayList<>();
		repositorio.findByComprador(usuario).stream().filter(c -> !c.isFinalizada()).forEach(c -> lista.add(c));
		return lista;
	}
	
	public List<Compra> findByProducto(Producto producto){
		return repositorio.findByProducto(producto);
	}
	
}
