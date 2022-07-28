package com.prueba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.dto.PeticionCrearEmpleado;
import com.prueba.dto.RespuestaConsultarEmpleado;
import com.prueba.dto.RespuestaCrearEmpleado;
import com.prueba.service.IEmpleadoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {


	@Autowired
	private IEmpleadoService empleadoService;

	@PostMapping(value = "/crearEmpleado")
	public ResponseEntity<Object> crearEmpleado(@RequestBody @Valid PeticionCrearEmpleado peticionCrearEmpleado) {
		RespuestaCrearEmpleado respuestaCrearEmpleado = empleadoService.crearEmpleado(peticionCrearEmpleado);
		return ResponseEntity.ok().body(respuestaCrearEmpleado);
	}
	
	@GetMapping(value = "/consultarEmpleados")
    public ResponseEntity<Object> consultarEmpleados() {
		RespuestaConsultarEmpleado respuestaConsultarEmpleado = empleadoService.consultarEmpleados();
		return ResponseEntity.ok().body(respuestaConsultarEmpleado);
    }
}
