package com.laura.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laura.spring.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
	public Usuario findByEmail(String email);
}
