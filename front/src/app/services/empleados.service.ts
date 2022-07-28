import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

import { RespuestaConsultarEmpleado, RespuestaCrearEmpleado } from '../models/respuestas';
import { PeticionCrearEmpleado } from '../models/peticiones';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService {

  private urlEmpleados = `${environment.url}/empleado`;

  constructor(private httClient: HttpClient) { }

  obtenerEmpleados(): Observable<RespuestaConsultarEmpleado>{
    return this.httClient.get<RespuestaConsultarEmpleado>(`${this.urlEmpleados}/consultarEmpleados`);
  }

  crearEmpleados(peticion: PeticionCrearEmpleado): Observable<RespuestaCrearEmpleado>{
    return this.httClient.post<RespuestaCrearEmpleado>(`${this.urlEmpleados}/crearEmpleado`,peticion);
  }
}
