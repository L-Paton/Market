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
		if(repositorio.findByEmail(usuario.getEmail()) == null) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			return repositorio.save(usuario);
		}
		return null;
	}
	
	public Usuario getUserById(String id) {
		return repositorio.getOne(id);
	}

}
