import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpleadosComponent } from './pages/empleados/empleados.component';
import { ActividadesComponent } from './pages/actividades/actividades.component';

const routes: Routes = [
  {
    path: '',
    component: ActividadesComponent
  },
  {
    path: 'empleados',
    component: EmpleadosComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
