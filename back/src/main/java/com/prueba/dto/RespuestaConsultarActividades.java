package com.prueba.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaConsultarActividades extends RespuestaGenerica {

	private List<ActividadDto> listActividades;
}
