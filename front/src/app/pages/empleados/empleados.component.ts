import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

import { EmpleadoDto } from '../../models/modelos';
import { EmpleadosService } from '../../services/empleados.service';
import { RespuestaCrearEmpleado, RespuestaConsultarEmpleado } from '../../models/respuestas';
import { Constantes } from 'src/app/models/constantes';
import { SwalUtils } from 'src/app/utils/swalUtils';

@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
  styleUrls: ['./empleados.component.css']
})
export class EmpleadosComponent implements OnInit {

  empleadosList: EmpleadoDto[] = [];

  constructor(private empleadosService: EmpleadosService,private router: Router) { }

  ngOnInit(): void {
    this.cargarInformacion();
  }

  cargarInformacion(): void {
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


  navegarPagina(): void {
    this.router.navigate(['']);
  }

}
