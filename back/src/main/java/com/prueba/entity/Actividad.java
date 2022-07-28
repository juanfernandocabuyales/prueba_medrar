package com.prueba.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "actividad", schema = "prueba")
@Getter
@Setter
public class Actividad implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actividad_id", unique = true, nullable = false)
	private Long actividadId;
	
	@Column(name ="actividad_nombre", length = 1000)
	private String actividadNombre;
	
	@Column(name ="actividad_estado", length = 1000)
	private String actividadEstado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actividad_fecha_inicio", length = 50)
	private Date actividadFecha;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "actividad_empleado")
	private Empleado actividadEmpleado;
}
