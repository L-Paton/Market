package com.laura.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laura.spring.model.Usuario;
import com.laura.spring.repository.UsuarioRepositorio;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
    
	@Autowired
    UsuarioRepositorio repositorio;
	
    @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
    	Usuario usuario = repositorio.findByEmail(email);
		UserBuilder builder = null;
		
    	if (usuario != null) {
			builder = User.withUsername(usuario.getUsername());
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority("USER"));
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
    	
    	return builder.build();
    }
}
