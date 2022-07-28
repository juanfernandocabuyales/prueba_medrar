package com.prueba.service;

import com.prueba.dto.PeticionCrearEmpleado;
import com.prueba.dto.RespuestaConsultarEmpleado;
import com.prueba.dto.RespuestaCrearEmpleado;

public interface IEmpleadoService {

	RespuestaCrearEmpleado crearEmpleado (PeticionCrearEmpleado peticionCrearEmpleado);
	
	RespuestaConsultarEmpleado consultarEmpleados();
}
