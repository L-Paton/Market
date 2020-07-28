package com.laura.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.laura.spring.model.Usuario;
import com.laura.spring.repository.UsuarioRepositorio;

@Service
public class UsuarioServicio {
	
	@Autowired
	UsuarioRepositorio repositorio;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public Usuario registrar(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return repositorio.save(usuario);
	}

}
