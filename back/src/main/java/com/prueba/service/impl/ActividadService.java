package com.prueba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.ActividadDto;
import com.prueba.dto.EmpleadoDto;
import com.prueba.dto.PeticionCrearActividad;
import com.prueba.dto.RespuestaConsultarActividades;
import com.prueba.dto.RespuestaCrearActividad;
import com.prueba.dto.RespuestaEliminarActividad;
import com.prueba.entity.Actividad;
import com.prueba.entity.Empleado;
import com.prueba.repository.IActividadRepository;
import com.prueba.service.IActividadService;
import com.prueba.utils.Constantes;
import com.prueba.utils.Utilidades;

@Service
public class ActividadService implements IActividadService {
	
	@Autowired
	private IActividadRepository actividadRepository;

	@Override
	public RespuestaCrearActividad crearActividad(PeticionCrearActividad peticionCrearActividad) {
		RespuestaCrearActividad respuestaCrearActividad = new RespuestaCrearActividad();
		try {
			ActividadDto actividadDto = peticionCrearActividad.getActividadDto();
			Actividad actividad = new Actividad();
			actividad.setActividadNombre(actividadDto.getActividadNombre());
			actividad.setActividadEstado(actividadDto.getActividadEstado());
			actividad.setActividadFecha(Utilidades.convertirStringToDate(actividadDto.getActividadFecha()));
			Empleado empleado = new Empleado();
			empleado.setEmpleadoId(actividadDto.getActividadEmpleado().getEmpleadoId());
			actividad.setActividadEmpleado(empleado);
			
			actividad = actividadRepository.save(actividad);
			
			respuestaCrearActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_CERO);
			respuestaCrearActividad.setMensaje(Constantes.RESPUESTA_EXITOSA);
		}catch(Exception e) {
			respuestaCrearActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
			respuestaCrearActividad.setMensaje(Constantes.RESPUESTA_FALLO);
		}
		return respuestaCrearActividad;
	}

	@Override
	public RespuestaCrearActividad actualizarActividad(PeticionCrearActividad peticionCrearActividad) {
		RespuestaCrearActividad respuestaCrearActividad = new RespuestaCrearActividad();
		try {
			ActividadDto actividadDto = peticionCrearActividad.getActividadDto();
			Actividad actividad = actividadRepository.findById(actividadDto.getActividadId()).orElse(null);
			if(actividad != null) {
				actividad.setActividadNombre(actividadDto.getActividadNombre());
				actividad.setActividadEstado(actividadDto.getActividadEstado());
				actividad.setActividadFecha(Utilidades.convertirStringToDate(actividadDto.getActividadFecha()));
				Empleado empleado = new Empleado();
				empleado.setEmpleadoId(actividadDto.getActividadEmpleado().getEmpleadoId());
				actividad.setActividadEmpleado(empleado);
				
				actividad = actividadRepository.save(actividad);
				
				respuestaCrearActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_CERO);
				respuestaCrearActividad.setMensaje(Constantes.RESPUESTA_EXITOSA);
			}else {
				respuestaCrearActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
				respuestaCrearActividad.setMensaje(Constantes.RESPUESTA_FALLO);
			}
		}catch(Exception e) {
			respuestaCrearActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
			respuestaCrearActividad.setMensaje(Constantes.RESPUESTA_FALLO);
		}
		return respuestaCrearActividad;
	}

	@Override
	public RespuestaConsultarActividades consultarActividades() {
		RespuestaConsultarActividades respuestaConsultarActividades = new RespuestaConsultarActividades();
		try {
			List<Actividad> listActividades = actividadRepository.findAll();
			
			if(listActividades != null && !listActividades.isEmpty()) {
				List<ActividadDto> listActividadDto = new ArrayList<>();
				listActividades.forEach(actividad -> {
					String resultados [] = Utilidades.formatearFecha(actividad.getActividadFecha());
					ActividadDto actividadDto = new ActividadDto();
					actividadDto.setActividadId(actividad.getActividadId());
					actividadDto.setActividadNombre(actividad.getActividadNombre());
					actividadDto.setActividadEstado(actividad.getActividadEstado());
					actividadDto.setActividadFecha(resultados[1]);
					EmpleadoDto empleadoDto = new EmpleadoDto();
					empleadoDto.setEmpleadoId(actividad.getActividadEmpleado().getEmpleadoId());
					empleadoDto.setEmpleadoNombre(actividad.getActividadEmpleado().getEmpleadoNombre());
					actividadDto.setActividadEmpleado(empleadoDto);
					actividadDto.setDiasAtraso(Long.parseLong(resultados[0]));
					if(actividadDto.getDiasAtraso() < 0) {
						actividadDto.setDiasAtraso(0L);
					}
					listActividadDto.add(actividadDto);
				});
				
				respuestaConsultarActividades.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_CERO);
				respuestaConsultarActividades.setMensaje(Constantes.RESPUESTA_EXITOSA);
				respuestaConsultarActividades.setListActividades(listActividadDto);
			}else {
				respuestaConsultarActividades.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
				respuestaConsultarActividades.setMensaje(Constantes.RESPUESTA_FALLO);
			}
		}catch(Exception e) {
			respuestaConsultarActividades.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
			respuestaConsultarActividades.setMensaje(Constantes.RESPUESTA_FALLO);
		}
		return respuestaConsultarActividades;
	}

	@Override
	public RespuestaEliminarActividad eliminarActividad(Long idActividad) {
		RespuestaEliminarActividad respuestaEliminarActividad = new RespuestaEliminarActividad();
		try {
			Actividad actividad = actividadRepository.findById(idActividad).orElse(null);
			if(actividad != null) {
				actividadRepository.delete(actividad);
				respuestaEliminarActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_CERO);
				respuestaEliminarActividad.setMensaje(Constantes.RESPUESTA_EXITOSA);
			}else {
				respuestaEliminarActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
				respuestaEliminarActividad.setMensaje(Constantes.RESPUESTA_FALLO);
			}
		}catch(Exception e) {
			respuestaEliminarActividad.setCodigoRespuesta(Constantes.CODIGO_RESPUESTA_UNO);
			respuestaEliminarActividad.setMensaje(Constantes.RESPUESTA_FALLO);
		}
		return respuestaEliminarActividad;
	}

}
