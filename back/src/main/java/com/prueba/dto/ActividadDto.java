package com.prueba.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActividadDto {

	private Long actividadId;
	
	private String actividadNombre;
	
	private String actividadEstado;
	
	private String actividadFecha;
	
	private EmpleadoDto actividadEmpleado;
	
	private Long diasAtraso;
}
