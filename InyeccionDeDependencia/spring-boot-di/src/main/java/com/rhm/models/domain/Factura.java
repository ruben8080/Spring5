package com.rhm.models.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
//contextos o alcanses de los componenetes 
//por defeto de tipo singleton va a mentener una instancia en el componente de spring de esa instancia 
//y no solo se aplica a factura con component sino tambien a los controladores que por defecto esta en un contexto singleton
//
@Component
@RequestScope//ahora el ben va a durar lo que dura una peticion(request) http de usuario, osea que el objeto se esta construyendo y se destruye 
//en cada peticion del usuario y no una sola vez como en el singleton ,se cambia en contexto tambien en cliente @RequestScope para que tambien tenga este contexto

//@SessionScope//este contexto es para cuando utilizamos request http y cada objeto o componente que se cree 
// va a durar o que dura la session o cuando se cierre session o se cierra el navegador, tambien debe de cambiarse en cliente 
public class Factura implements Serializable {
	
	private static final long serialVersionUID = -7947590360040903025L;
	
	@Value("${factura.descripcion}")
	private String descripcion;
	@Autowired // inyectamos en cliente
	private Cliente cliente;// esta relacionada con cliente
	@Autowired // inyectamos el bean si hay mas de uno utilizamos el @Qualifier 
	@Qualifier("itemsFacturaOficina")
	private List<ItemFactura> items; // tinen muchos items de tipo itemsfactura

	//cilco de vida de un componente
	//inicializar geramos un metodo publico para ejecutar algo  o modificar algun atributo 
	//
	@PostConstruct//se ejecuta despues que se aya creado ell objeto y despues de que se aya inyectado la dependcia 
	public void inicializar() {
		cliente.setNombre(cliente.getNombre().concat( " " ).concat("José"));
		descripcion = descripcion.concat(" del cliente: ").concat(cliente.getNombre());
	}
	// destruir es cuando se destruye un objeto la parar la app
	@PreDestroy
	public void destruir() {
		System.out.println("Factura destruida : ".concat(this.descripcion));
	}
	
	public Factura() {
	}

	public Factura(String descripcion, Cliente cliente, List<ItemFactura> items) {
		this.descripcion = descripcion;
		this.cliente = cliente;
		this.items = items;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

}
