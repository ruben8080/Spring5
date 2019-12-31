package com.rhm.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Primary //con esta anotacion en el caso de que mas de ua clase requiera la mismas interfas le decimos que tome la que tiene  la anotacion primary
@Component("miServicioComplejo") //es una semantica nada para poder inyectar
public class MiServicioComplejo implements IServicio{

	@Override
	public String operacion() {
		
		return "ejecutando algun proceso importante simple......";
	}

}
