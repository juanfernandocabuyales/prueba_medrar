package com.prueba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.dto.PeticionCrearActividad;
import com.prueba.dto.RespuestaConsultarActividades;
import com.prueba.dto.RespuestaCrearActividad;
import com.prueba.dto.RespuestaEliminarActividad;
import com.prueba.service.IActividadService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/actividad")
public class ActividadController {

	@Autowired
	private IActividadService actividadService;
	
	@GetMapping(value = "/consultarActividades")
    public ResponseEntity<Object> consultarActividades() {
		RespuestaConsultarActividades respuestaConsultarActividades = actividadService.consultarActividades();
		return ResponseEntity.ok().body(respuestaConsultarActividades);
    }
	
	@PostMapping(value = "/crearActividad")
	public ResponseEntity<Object> crearActividad(@RequestBody @Valid PeticionCrearActividad peticionCrearActividad) {
		RespuestaCrearActividad respuestaCrearActividad = actividadService.crearActividad(peticionCrearActividad);
		return ResponseEntity.ok().body(respuestaCrearActividad);
	}
	
	@PutMapping(value = "/actualizarActividad")
	public ResponseEntity<Object> actualizarActividad(@RequestBody @Valid PeticionCrearActividad peticionCrearActividad) {
		RespuestaCrearActividad respuestaCrearActividad = actividadService.actualizarActividad(peticionCrearActividad);
		return ResponseEntity.ok().body(respuestaCrearActividad);
	}
	
	@DeleteMapping(value = "/eliminarActividad/{id}")
	public ResponseEntity<Object> eliminarActividad(@PathVariable(name = "id") Long id) {
		RespuestaEliminarActividad respuestaEliminarActividad = actividadService.eliminarActividad(id);
		return ResponseEntity.ok().body(respuestaEliminarActividad);
	}
}
