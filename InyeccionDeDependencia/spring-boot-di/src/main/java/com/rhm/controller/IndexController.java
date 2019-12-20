package com.rhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rhm.models.service.MiServicio;

@Controller
public class IndexController {
	
	//inyectamos los servicios con @Autowired
	@Autowired
	private MiServicio ms;
//	MiServicio ms = new MiServicio();
	
	
	@GetMapping({"/", "", "index"})
	public String index(Model model) {
		model.addAttribute("objeto", ms.operacion());
		return "index";
	}
}
