package com.rhm;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
//	para agregar recursos estaticos a nuestro proyecto este metodo se agrega con la opcion click derecho sources overrride/Implemen metho -> addResourceHandler(Resour..... 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		//2020
		String resourcesPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		log.info(resourcesPath);
		//
		
		
		registry.addResourceHandler("/uploads/**")//la url la misma que se detalla en el template donde es llamada en este caso el ver.html, los ** corresponden al valor de la fot 
		//definimos el directorio fisico externo  a nuestro proyecto
		.addResourceLocations("file:/opt/uploads/");
//		.addResourceLocations("file:/C:/Temp/uploads/");windows
	}
	

}
