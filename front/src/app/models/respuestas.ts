import { ActividadDto, EmpleadoDto } from './modelos';

export class RespuestaGenerica{
    codigoRespuesta?: string;
    mensaje?: string;
}

export class RespuestaConsultarActividades extends RespuestaGenerica{
    listActividades?: ActividadDto[];
}

export class RespuestaConsultarEmpleado extends RespuestaGenerica{
    listEmpleados: EmpleadoDto[];
    constructor(listEmpleados: EmpleadoDto[]){
        super();
        this.listEmpleados = listEmpleados;
    }
}

export class RespuestaCrearActividad extends RespuestaGenerica{
}

export class RespuestaCrearEmpleado extends RespuestaGenerica{
}

export class RespuestaEliminarActividad extends RespuestaGenerica{
}