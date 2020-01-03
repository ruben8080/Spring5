package com.rhm.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rhm.models.entity.Cliente;

//decoramos la clase con la anotacion @Repository marca a la clase como un componente spring lo marca como un tipo repositorio 
@Repository
public class ClienteDaoImpl implements IClienteDao {
//DAO en la clase de acceso a datos 
	@PersistenceContext // de forma automatica va a inyectar en entity maneyar segun la unidad de
						// perisstencia
	// EntityManager :el entity manegaer se encarga de manejar las clases de
	// entidades,el ciclo de vida las persiste dentro del contexto las actuliza las
	// elimina
	// puede eliminar consultas, todas las operaciones a la BD a nivel objeto
	// atravez de las clases entity o sea que las consultas son todas JPA
	private EntityManager em;

	//metodos que son solo consultas a la DB
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true) // lo colocamos esto por que es una consulta de solo lectura por ser consulta
	// cuando es un editar un insert ahi podemos omitir el readOnly = true
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	// editar nuevo cliente
	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
	
	//metodos que realisan acciones dentro de la tabla de DB***************************

	// guardar nuevo cliente
	@Override
	@Transactional
	public void save(Cliente cliente) {
		if (cliente.getId() != null && cliente.getId() > 0) {
			System.out.println("llego a editar");
			em.merge(cliente);// con merge editamos los datos existentes
		} else {
			em.persist(cliente);
		}
	}

	// eliminar cliente
	@Override
	@Transactional
	public void delete(Long id) {
		// primero obtenemos el cliente a eliminar
		em.remove(findOne(id));

	}

}
