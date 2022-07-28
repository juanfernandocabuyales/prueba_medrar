export class ActividadDto{
    actividadId?: number;
    actividadNombre?: string;
    actividadEstado?: string;
    actividadFecha?: string;
    actividadEmpleado?: EmpleadoDto;
    diasAtraso?: number;
}

export class EmpleadoDto{
    empleadoId?: number;
    empleadoNombre?: string;
}