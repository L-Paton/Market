package com.laura.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.spring.model.Compra;
import com.laura.spring.model.Producto;
import com.laura.spring.model.Usuario;

public interface CompraRepositorio extends JpaRepository<Compra, Long>{

	List<Compra> findByComprador(Usuario usuario);
	
	List<Compra> findByProducto(Producto producto);
	
}
