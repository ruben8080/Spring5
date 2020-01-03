package com.rhm.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rhm.models.dao.IClienteDao;
import com.rhm.models.entity.Cliente;

@Service//una clase con la anotacion service esta basado en el patron de diseño fachada 
//un unico punto de acceso hacia distintos DAOs o repositorios 
//otra caracteristica de la clase service es que poodemos manejar la transaccion sin tener que implementar las anotaciones transseccional en elDAO
public class ClienteServiceImpl implements IClienteService {
//inyectamos el cliente DAO
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true) // lo colocamos esto por que es una consulta de solo lectura por ser consulta
	// cuando es un editar un insert ahi podemos omitir el readOnly = true
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.delete(id);
		
	}

}
