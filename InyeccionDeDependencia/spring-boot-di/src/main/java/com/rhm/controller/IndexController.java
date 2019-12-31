package com.rhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rhm.models.service.IServicio;
import com.rhm.models.service.MiServicio;

@Controller
public class IndexController {
	
	//inyectamos los servicios con @Autowired
	@Autowired
	private IServicio servicio;
	//cuando hay dos clases en concreto que buscan implementar la misma interface debemos de determinar cual de ellas se va a utilizar
	// para ello utilizamo la anotacion primary
	
//	private MiServicio ms;
//	MiServicio ms = new MiServicio();
	
	
	@GetMapping({"/", "", "index"})
	public String index(Model model) {
		model.addAttribute("objeto", servicio.operacion());
		return "index";
	}

	
	//vamos a inyectar via metodo set y contructor
	// inyectamos por contructro podemos omitir el @Autowired
//	@Autowired
//	public IndexController(IServicio servicio) {
//		this.servicio = servicio;
//	}

	
	//metodo set
	
//	@Autowired
//	public void setServicio(IServicio servicio) {
//		this.servicio = servicio;
//	}


	
	
}
