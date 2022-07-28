import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { ActividadDto, EmpleadoDto } from '../../models/modelos';
import { ActividadService } from '../../services/actividad.service';
import { RespuestaConsultarActividades, RespuestaConsultarEmpleado, RespuestaCrearActividad, RespuestaEliminarActividad } from '../../models/respuestas';
import { Constantes } from 'src/app/models/constantes';
import { SwalUtils } from 'src/app/utils/swalUtils';
import { EmpleadosService } from 'src/app/services/empleados.service';
import { PeticionCrearActividad } from '../../models/peticiones';
import { SweetAlertResult } from 'sweetalert2';

@Component({
  selector: 'app-actividades',
  templateUrl: './actividades.component.html',
  styleUrls: ['./actividades.component.css']
})
export class ActividadesComponent implements OnInit {

  actividadesList: ActividadDto[] = [];

  empleadosList: EmpleadoDto[] = [];

  form: FormGroup;

  isForm: Promise<any>;

  submitted: boolean;

  creacion: boolean;

  editar: boolean;

  actividad: ActividadDto;

  constructor(private formBuilder: FormBuilder,
              private actividadService: ActividadService,
              private router: Router,
              private datePipe: DatePipe,
              private empleadosService: EmpleadosService) { }

  ngOnInit(): void {
    this.iniciarFormulario();
    this.cargarInformacion();
    this.cargarInformacionEmpleado();
  }

  iniciarFormulario(): void {
    this.isForm = Promise.resolve(
      this.form = this.formBuilder.group({
        nombre: new FormControl(null, [Validators.required,Validators.minLength(4), Validators.maxLength(1000)]),
        estado: new FormControl(null, [Validators.required]),
        fecha: new FormControl(null, [Validators.required]),
        empleado: new FormControl(null, [Validators.required])
      }));
  }

  mostrarFormulario(): void {
    this.creacion = true;
  }

  cargarInformacion(): void {
    this.actividadService.obtenerActividades().subscribe(( resp:RespuestaConsultarActividades ) => {
      if(resp.codigoRespuesta === Constantes.CODIGO_RESPUESTA_CERO ){
        this.actividadesList = resp.listActividades;
      }else{
        SwalUtils.showAlert('Información',resp.mensaje,'warning');
      }
    }, () => {
      SwalUtils.showAlert(
        'Error',
        'Se ha presentado un unconveniente al realizar la operación',
        'error'
      );
    });
  }

  cargarInformacionEmpleado(): void {
    this.empleadosService.obtenerEmpleados().subscribe(( resp:RespuestaConsultarEmpleado ) => {
      if(resp.codigoRespuesta === Constantes.CODIGO_RESPUESTA_CERO ){
        this.empleadosList = resp.listEmpleados;
      }else{
        SwalUtils.showAlert('Información',resp.mensaje,'warning');
      }
    }, () => {
      SwalUtils.showAlert(
        'Error',
        'Se ha presentado un unconveniente al realizar la operación',
        'error'
      );
    });
  }

  validarOperacion(): void {
    if(!this.editar){
      this.guardarActividad();
    }else{
      this.actualizarActividad();
    }
  }

  guardarActividad(): void {
    this.submitted = true;
    if(this.form.invalid){
      SwalUtils.showAlert('Información','Favor revisar los datos ingresados','error');
    }else{
      const valores = this.form.value;
      let peticion: PeticionCrearActividad = {
        actividadDto : {
          actividadNombre: valores.nombre,
          actividadEstado: valores.estado,
          actividadFecha: this.datePipe.transform(valores.fecha,'yyyy-MM-dd HH:mm:ss'),
          actividadEmpleado: {
            empleadoId: valores.empleado
          }
        }
      };
      this.actividadService.guardarActividad(peticion).subscribe((resp: RespuestaCrearActividad) => {
        SwalUtils.showAlert('Información',resp.mensaje,'success').then(()=>{
          this.submitted = false;
          this.creacion = false;
          this.form.reset(); 
          this.cargarInformacion();
        });
      }, () => {
        SwalUtils.showAlert(
          'Error',
          'Se ha presentado un unconveniente al realizar la operación',
          'error'
        );
      });
    }
  }

  actualizarActividad(): void {
    this.submitted = true;
    if(this.form.invalid){
      SwalUtils.showAlert('Información','Favor revisar los datos ingresados','error');
    }else{
      const valores = this.form.value;
      let peticion: PeticionCrearActividad = {
        actividadDto : {
          actividadId: this.actividad.actividadId,
          actividadNombre: valores.nombre,
          actividadEstado: valores.estado,
          actividadFecha: this.datePipe.transform(valores.fecha,'yyyy-MM-dd HH:mm:ss'),
          actividadEmpleado: {
            empleadoId: valores.empleado
          }
        }
      };
      this.actividadService.actualizarActividad(peticion).subscribe((resp: RespuestaCrearActividad) => {
        SwalUtils.showAlert('Información',resp.mensaje,'success').then(()=>{
          this.submitted = false;
          this.editar = false;
          this.form.reset(); 
          this.cargarInformacion();
        });
      }, () => {
        SwalUtils.showAlert(
          'Error',
          'Se ha presentado un unconveniente al realizar la operación',
          'error'
        );
      });
    }
  }

  editarActividad(actividad: ActividadDto): void {
   this.editar = true;
   this.actividad = actividad;
   this.form.get('nombre').setValue(actividad.actividadNombre);
   this.form.get('estado').setValue(actividad.actividadEstado);
   let fecha = this.toDateString(new Date(actividad.actividadFecha));
   this.form.get('fecha').setValue(fecha);
   let empleado: EmpleadoDto = this.empleadosList.find((empleado: EmpleadoDto) => empleado.empleadoId === actividad.actividadEmpleado.empleadoId);
   this.form.get('empleado').setValue(empleado.empleadoId);
  }

  eliminarActividad(actividad: ActividadDto): void {
    SwalUtils.showAlertConfirmar('Información','¿Desea eliminar la actividad?','warning').then((resp: SweetAlertResult) => {
      if(resp.isConfirmed){
        this.actividadService.eliminarActividad(actividad.actividadId).subscribe((resp: RespuestaEliminarActividad) => {
          SwalUtils.showAlert('Información',resp.mensaje,'success').then(()=>{
            this.cargarInformacion();
          });
        });
      }
    })
  }

  navegarPagina(): void {
    this.router.navigate(['empleados']);
  }

  private toDateString(date: Date): string {
    let respuesta = (date.getFullYear().toString() + '-' 
       + ("0" + (date.getMonth() + 1)).slice(-2) + '-' 
       + ("0" + (date.getDate())).slice(-2))
       + 'T' + date.toTimeString().slice(0,5);
       return respuesta;
  }
}
