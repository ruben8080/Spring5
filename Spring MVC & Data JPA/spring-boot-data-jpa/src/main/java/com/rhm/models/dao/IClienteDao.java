package com.rhm.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.rhm.models.entity.Cliente;
//convertimos esta interface en un repositorio 
public interface IClienteDao extends CrudRepository<Cliente, Long >{

}
