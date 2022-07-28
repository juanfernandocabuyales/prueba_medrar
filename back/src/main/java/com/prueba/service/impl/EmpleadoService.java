package com.prueba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.EmpleadoDto;
import com.prueba.dto.PeticionCrearEmpleado;
import com.prueba.dto.RespuestaConsultarEmpleado;
import com.prueba.dto.RespuestaCrearEmpleado;
import com.prueba.entity.Empleado;
import com.prueba.repository.IEmpleadoRepository;
import com.prueba.service.IEmpleadoService;
import com.prueba.utils.Constantes;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private IEmpleadoRepository empleadoRepository;

	@Override
	public RespuestaCrearEmpleado crearEmpleado(PeticionCrearEmpleado peticionCrearEmpleado) {
		RespuestaCrearEmpleado respuestaCrearEmpleado = new RespuestaCrearEmpleado();
		try {
			Empleado empleado = new Empleado();
			empleado.setEmpleadoNombre(peticionCrearEmpleado.getEmpleadoNombre());
			
			empleado = empleadoRepository.save(empleado);
			
			respuestaCrearEmpleado.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_CERO);
			respuestaCrearEmpleado.setMensaje(Constantes.RESPUESTA_EXITOSA);
		}catch(Exception e) {
			respuestaCrearEmpleado.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
			respuestaCrearEmpleado.setMensaje(Constantes.RESPUESTA_FALLO);
		}
		return respuestaCrearEmpleado;
	}

	@Override
	public RespuestaConsultarEmpleado consultarEmpleados() {
		RespuestaConsultarEmpleado respuestaConsultarEmpleado = new RespuestaConsultarEmpleado();
		try {
			List<Empleado> listEmpleados = empleadoRepository.findAll();
			
			if(listEmpleados != null && !listEmpleados.isEmpty()) {
				List<EmpleadoDto> listEmpleadosDto = new ArrayList<>();
				listEmpleados.forEach(empleado -> {
					EmpleadoDto empleadoDto = new EmpleadoDto();
					empleadoDto.setEmpleadoId(empleado.getEmpleadoId());
					empleadoDto.setEmpleadoNombre(empleado.getEmpleadoNombre());
					listEmpleadosDto.add(empleadoDto);
				});
				
				respuestaConsultarEmpleado.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_CERO);
				respuestaConsultarEmpleado.setMensaje(Constantes.RESPUESTA_EXITOSA);
				respuestaConsultarEmpleado.setListEmpleados(listEmpleadosDto);
			}else {
				respuestaConsultarEmpleado.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
				respuestaConsultarEmpleado.setMensaje(Constantes.RESPUESTA_FALLO);
			}
		}catch(Exception e) {
			respuestaConsultarEmpleado.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
			respuestaConsultarEmpleado.setMensaje(Constantes.RESPUESTA_FALLO);
		}
		return respuestaConsultarEmpleado;
	}
}
