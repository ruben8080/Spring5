package com.rhm.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//se declara el nombre de la tabla en plural y en minuscula 
@Table(name = "clientes")
public class Cliente implements Serializable {
	// implementar serialisable en este tipo de clases es recomendado por que muchas
	// veces se trabaja con serialzacion(proceso de convertir un objeto en una
	// secuencia de bits para almacenarlos
	// o transmitirlos a la memoria por ejemplo o a aun json a una bd )
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // cual es la estrategia en que genera la llave en BD el motor
														// (auto incremental )
	private Long id;
	// si el nombre de nuestro atributo no concide con el nombre en la tabla de BD
	// podemos agregar la notacion @Column
//	@Column(name = "NOMBRE_CLIENTE")
//	@Column(name = "nombre-clien")
	//reglas de validacion de javax.validation.constraints.NotEmpty
	@NotEmpty//distinto de vacio y que el largo sea mayor que cero, al menos escribimos uno o mas caracteres en el campo, por ser de tipo string
	private String nombre;
	@NotEmpty
	private String apellido;
	
	@NotEmpty//solo para string
	@Email//en srping boot siempre se utiliza javax.validation. para la validacion de los campos
	private String email;
	
	@NotNull//valida que la fecha no sea nula, se usa para otras tipos de datos
	@Column(name = "create_at") // nomenglatura al ecribir en la notacion @Coulmn
	@Temporal(TemporalType.DATE) // indica el formato que se va a guardar esta fecha en java en la BD
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	private String foto;

	public Cliente() {
	}

	public Cliente(Long id, String nombre, String apellido, String email, Date createAt) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	//hacemos un metodo que un atributo persista antes de hacer la perisistencia propiamente dicha
//	@PrePersist
//	public void prePersist() {
//		createAt = new Date();
//	}
//	

}
