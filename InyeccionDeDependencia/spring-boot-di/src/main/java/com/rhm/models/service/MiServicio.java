package com.rhm.models.service;
import org.springframework.stereotype.Component;
// todos los package deben estar dentro del package base qye es donde esta aplicattioncontext
@Component //es una semantica nada para poder inyectar
public class MiServicio {

	public String operacion() {
		return "ejecutando algun proceso importamte...";
	}
}
