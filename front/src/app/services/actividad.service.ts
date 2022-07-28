import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

import { RespuestaConsultarActividades, RespuestaCrearActividad, RespuestaEliminarActividad } from '../models/respuestas';
import { PeticionCrearActividad, PeticionEliminarActividad } from '../models/peticiones';

@Injectable({
  providedIn: 'root'
})
export class ActividadService {

  private urlActividad = `${environment.url}/actividad`;

  constructor(private httClient: HttpClient) { }

  obtenerActividades(): Observable<RespuestaConsultarActividades>{
    return this.httClient.get<RespuestaConsultarActividades>(`${this.urlActividad}/consultarActividades`);
  }

  guardarActividad(peticion: PeticionCrearActividad): Observable<RespuestaCrearActividad>{
    return this.httClient.post<RespuestaConsultarActividades>(`${this.urlActividad}/crearActividad`,peticion);
  }

  actualizarActividad(peticion: PeticionCrearActividad): Observable<RespuestaCrearActividad>{
    return this.httClient.put<RespuestaConsultarActividades>(`${this.urlActividad}/actualizarActividad`,peticion);
  }

  eliminarActividad(id: number): Observable<RespuestaEliminarActividad>{
    return this.httClient.delete<RespuestaEliminarActividad>(`${this.urlActividad}/eliminarActividad/${id}`);
  }
}
