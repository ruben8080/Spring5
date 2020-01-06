package com.rhm.controllers;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.rhm.models.entity.Cliente;
import com.rhm.models.service.IClienteService;


@Controller
//indicamos que se va a guardar en los atributos de la session el objeto cliente mapeado al fomulario cada vez que se invoca el crear o editar 
//una peticion get, va a obtener el onjeto cliente lo guarda en los atributos de la session y lo  pasa a la vista 
//los mismos quedan persistentes hasta el cierre de la session
@SessionAttributes("cliente")
public class ClienteController {


	@Autowired//inyectamos  la interface
//	public IClienteDao clienteDao;
	public IClienteService clienteService;
	
	
	@RequestMapping(value = "/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	//creamos el cliente
//	@RequestMapping(value = "/listar", method=RequestMethod.GET)//al ser metodo get se omite por defecto se entiende que es get 
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente c = new Cliente();//objeto de negocio bidireccional 
		model.put("cliente",c);
		model.put("titulo", "Crear Cliente");
		return "form";
	}
	
//	guardamos el cliente 
	@RequestMapping(value = "/form", method=RequestMethod.POST)
	//SessionStatus status elimna la session 
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {//tenemos que anotar con @Valid para que pueda tomar nuestras validaciones declaradas en la clase entity 
		//lo errores se capturan o validan con el BindingResult, siempre van juntos @Valid Cliente cliente, BindingResult result ,su hibiera otros parametros 
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Cliente");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();//elimina el objeto cliente de la session y termiana el proceso 
		return "redirect:listar";
	}
	//implementamos el mapping 
	//editar cliente
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model){
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo","Editar Cliente");
		return "form";
	} 
	
	//eliminar cliente
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id){
		if (id > 0) {
			clienteService.delete(id);
		} 
		return "redirect:/listar";
	} 
	//ver detalle cliente
	@RequestMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model){
			Cliente cliente = null;
			if (id > 0) {
				cliente = clienteService.findOne(id);
			} 
			model.put("cliente", cliente);
			model.put("titulo","Detalle Cliente");
			return "form";
		} 
	

	
}
