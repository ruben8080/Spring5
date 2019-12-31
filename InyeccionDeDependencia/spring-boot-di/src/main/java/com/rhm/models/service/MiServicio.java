package com.rhm.models.service;
import org.springframework.stereotype.Component;
//vamos a implementar la interface iservicio en esta clase que aser implementada es obligada a 

// todos los package deben estar dentro del package base qye es donde esta aplicattioncontext
@Component("miServicioSimple") //es una semantica nada para poder inyectar
public class MiServicio implements IServicio{

	@Override //indica que esta es una implementacion del padre  IServicio esuna protoculo que debe cumplir es un contrato
	public String operacion() {
		return "ejecutando algun proceso importamte...";
	}

	public MiServicio() {
	}
	
	//todo componente spring que  vamos a registrar en el contenedor tiene que tener un constructor por defecdto sin argumentos 
}
