package com.rhm.models.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rhm.models.entity.Cliente;

//PagingAndSortingRepository se utiliza para hacer el paginado a la hora de traer todos los registros 

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{
	// es una firma sin implementar solo indica en nombre del metodo
//	public List<Cliente> findAll();
//	//insertar cliente 
//	public void save(Cliente cliente);
//	//editar cliente
//	public Cliente findOne(Long id);
//	//eliminar cliente
//	public void delete(Long id);
//	
}
