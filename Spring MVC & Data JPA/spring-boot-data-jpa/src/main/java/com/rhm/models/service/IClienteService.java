package com.rhm.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rhm.models.entity.Cliente;

public interface IClienteService {

//	import org.springframework.data.domain.Page;
//	import org.springframework.data.domain.Pageable; 
	//para poder utilizar el paginado es indispensable importar de estas dos importaciones  

	
	// es una firma sin implementar solo indica en nombre del metodo
	public List<Cliente> findAll();
	// hace un metodo que me devuelve todos los registrso paginados 
	public Page<Cliente> findAll(Pageable pageable);
	//insertar cliente 
	public void save(Cliente cliente);
	//editar cliente
	public Cliente findOne(Long id);
	//eliminar cliente
	public void delete(Long id);
	
}
