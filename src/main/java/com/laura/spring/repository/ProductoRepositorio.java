package com.laura.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.spring.model.Producto;
import com.laura.spring.model.Usuario;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

	List<Producto> findByVendedor(Usuario usuario);
}
