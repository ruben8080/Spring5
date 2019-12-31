package com.rhm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rhm.models.domain.ItemFactura;
import com.rhm.models.domain.Producto;

@Configuration
public class AppConfig {
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems(){
		Producto p1 = new Producto("Camara Sony", 100);
		Producto p2 = new Producto("Bicicleta Bianchi aro 206", 500);
		
		ItemFactura linea1 = new ItemFactura(p1, 2);
		ItemFactura linea2 = new ItemFactura(p2, 5);
		return Arrays.asList(linea1, linea2);// retornamos una lista donde con el metodo Arrays.asList convertimos a lista los elementos de item factura 
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina(){
		Producto p1 = new Producto("Monior LG", 250);
		Producto p2 = new Producto("Netbook asus Bianchi aro 206", 500);
		Producto p3 = new Producto("Impresora multiufncion HP", 80);
		Producto p4 = new Producto("Escritorio de oficina", 300);
		
		ItemFactura linea1 = new ItemFactura(p1, 2);
		ItemFactura linea2 = new ItemFactura(p2, 5);
		ItemFactura linea3 = new ItemFactura(p3, 2);
		ItemFactura linea4 = new ItemFactura(p4, 5);
		return Arrays.asList(linea1, linea2, linea3, linea4);// retornamos una lista donde con el metodo Arrays.asList convertimos a lista los elementos de item factura 
	}
	

}
