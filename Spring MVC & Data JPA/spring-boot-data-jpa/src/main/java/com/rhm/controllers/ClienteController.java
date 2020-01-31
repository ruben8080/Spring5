package com.rhm.controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.rhm.models.entity.Cliente;
import com.rhm.models.service.IClienteService;
import com.rhm.util.paginator.PageRender;


@Controller
//indicamos que se va a guardar en los atributos de la session el objeto cliente mapeado al fomulario cada vez que se invoca el crear o editar 
//una peticion get, va a obtener el onjeto cliente lo guarda en los atributos de la session y lo  pasa a la vista 
//los mismos quedan persistentes hasta el cierre de la session
@SessionAttributes("cliente")
public class ClienteController {


	@Autowired//inyectamos  la interface
//	public IClienteDao clienteDao;
	public IClienteService clienteService;
	
	
	// mapping de ver detalle del cliente 
	@RequestMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		System.out.println("llego a ver mapping");
		
		Cliente cliente = clienteService.findOne(id);
		if (cliente == null ) {
			
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());
		return "ver";
	}
		
	@RequestMapping(value = "/listar", method=RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0")int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto , RedirectAttributes flash, SessionStatus status) {//tenemos que anotar con @Valid para que pueda tomar nuestras validaciones declaradas en la clase entity 
		//lo errores se capturan o validan con el BindingResult, siempre van juntos @Valid Cliente cliente, BindingResult result ,su hibiera otros parametros 
//		RedirectAttributes flash la interface lo utilizo para enviar mensajes al front desde el backend 
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Cliente");
			return "form";
		}
		//preguntamos que foto no sea vacio
		if (!foto.isEmpty()) {
			//debemos de definir nuestro diretorio donde vamos a colocar nuestras imagenes
			//importamos de import java.nio.file.Path;
			Path directorioRecursos = Paths.get("src//main//resources//static//uploads");//directorio dondde gurdan mis imahgenes subidas nuestra ruta 
			//obtenes el string del directorio 
			String rootPath = directorioRecursos.toFile().getAbsolutePath();
			//obtenemos los bits de la imagen 
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");
				cliente.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//vemos como diferenciar el editar de el guardar 
		
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con éxito!" : "Cliente creado con éxito!";
		clienteService.save(cliente);
		status.setComplete();//elimina el objeto cliente de la session y termiana el proceso 
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	//implementamos el mapping 
	//editar cliente
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
			
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo","Editar Cliente");
		return "form";
	} 
	
	//eliminar cliente
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("error", "Cliente Eliminado con éxito!");
		} 
		return "redirect:/listar";
	} 
	
	

	
}
