package com.prueba.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeticionCrearEmpleado {

	@NotNull(message = "El campo Nombre Empleado debe ser obligatorio")
	@NotEmpty(message = "El campo Nombre Empleado no puede estar vacio")
	private String empleadoNombre;
}
