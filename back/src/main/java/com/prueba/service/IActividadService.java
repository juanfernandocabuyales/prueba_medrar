package com.prueba.service;

import com.prueba.dto.PeticionCrearActividad;
import com.prueba.dto.PeticionEliminarActividad;
import com.prueba.dto.RespuestaConsultarActividades;
import com.prueba.dto.RespuestaCrearActividad;
import com.prueba.dto.RespuestaEliminarActividad;

public interface IActividadService {

	RespuestaCrearActividad crearActividad(PeticionCrearActividad peticionCrearActividad);
	
	RespuestaCrearActividad actualizarActividad(PeticionCrearActividad peticionCrearActividad);
	
	RespuestaConsultarActividades consultarActividades();
	
	RespuestaEliminarActividad eliminarActividad(Long idActividad);
}
