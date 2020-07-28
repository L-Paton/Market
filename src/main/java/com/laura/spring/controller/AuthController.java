package com.laura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.laura.spring.model.Usuario;
import com.laura.spring.service.UsuarioServicio;

@Controller
public class AuthController {
	
	@Autowired
	UsuarioServicio usuarioServicio;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return null;
	}
	
	@GetMapping("/registry")
	public String registry(Model model){
		model.addAttribute("usuario", new Usuario());
		return "registry";
	}
	
	@PostMapping("/registry")
	public String registry(@ModelAttribute Usuario usuario) {
		usuarioServicio.registrar(usuario);
		return "redirect:/login";
	}

}
