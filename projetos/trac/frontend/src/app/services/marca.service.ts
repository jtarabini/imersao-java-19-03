import { Injectable } from '@angular/core';
import { Marca } from '../entidades/marca';
import { MARCAS } from '../mock/marcas-mock';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {

  constructor() { }

  getMarcas() : Observable<Marca[]> {
    return of(MARCAS);
  }
}
