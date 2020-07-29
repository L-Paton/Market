package com.laura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laura.spring.repository.ProductoRepositorio;

@Controller
public class HomeController {
	
	@Autowired
	ProductoRepositorio repositorio;
	
	@GetMapping({"/", "/home"})
	public String index(Model model) {
		model.addAttribute("productos", repositorio.findAll());
		return "index";
	}
}
