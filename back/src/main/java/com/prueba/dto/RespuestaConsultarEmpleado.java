package com.prueba.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaConsultarEmpleado extends RespuestaGenerica {

	private List<EmpleadoDto> listEmpleados;
}
