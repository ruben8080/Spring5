package com.rhm.models.service;

import java.util.List;

import com.rhm.models.entity.Cliente;

public interface IClienteService {

	// es una firma sin implementar solo indica en nombre del metodo
	public List<Cliente> findAll();
	//insertar cliente 
	public void save(Cliente cliente);
	//editar cliente
	public Cliente findOne(Long id);
	//eliminar cliente
	public void delete(Long id);
	
}
