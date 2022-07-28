import { ActividadDto } from './modelos';

export interface PeticionCrearActividad{
    actividadDto: ActividadDto
}

export interface PeticionCrearEmpleado{
    empleadoNombre: string;
}

export interface PeticionEliminarActividad{
    actividadId: number;
}