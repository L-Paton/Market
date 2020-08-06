package com.laura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laura.spring.service.ProductoServicio;

@Controller
public class HomeController {
	
	@Autowired
	ProductoServicio productoServicio;
	
	@GetMapping({"/", "/home"})
	public String index(Model model) {
		model.addAttribute("productos", productoServicio.listaProductosNoVendidos());
		return "index";
	}
}
