import Swal, { SweetAlertIcon, SweetAlertResult } from 'sweetalert2';

export class SwalUtils {

  public static showAlert(title?: string, text?: string, type?: SweetAlertIcon): Promise<SweetAlertResult<any>> {
    return Swal.fire({
      title,
      text,
      icon: type,
      confirmButtonText: 'Aceptar',
      confirmButtonColor: '#689F38',
      showCancelButton: false,
    }).then();
  }

 public static showAlertConfirmar(title: string, text: string, type: SweetAlertIcon): Promise<SweetAlertResult<any>> {
    return Swal.fire({
      title,
      text,
      icon: type,
      confirmButtonText: 'Aceptar',
      confirmButtonColor: '#689F38',
      cancelButtonText: 'Cancelar',
      cancelButtonColor: '#D32F2F',
      showCancelButton: true        
    }).then();
  }

  public static showDialogInput(title: string,text?:string):  Promise<SweetAlertResult<any>>{
    return Swal.fire({
      title,
      text,
      input: 'text',
      confirmButtonText: 'Aceptar',
      cancelButtonText: 'Cancelar',
      showCancelButton: true        
    });
  }
}