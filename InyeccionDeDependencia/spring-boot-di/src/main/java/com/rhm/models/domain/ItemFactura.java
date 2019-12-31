package com.rhm.models.domain;

public class ItemFactura {

	private Producto producto;
	private Integer cantidad;

	public ItemFactura(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public ItemFactura() {
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Integer calcularImporte()
	{
		int resultado;
		resultado = this.cantidad * this.producto.getPrecio();
		return resultado;
	}
	//calculamos el total de productos por el precio 
	

}
