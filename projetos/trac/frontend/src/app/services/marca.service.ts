import { Injectable } from '@angular/core';
import { Marca } from '../entidades/marca';
import { MARCAS } from '../mock/marcas-mock';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getMarcas() : Observable<Marca[]> {
    return this.http.get<Marca[]>('/api/marcas');
  }

  save(marca:Marca) : Observable<Marca> {
    var url = "/api/marcas/"+marca.id;
    return this.http.post<Marca>(url, marca, this.httpOptions);
  } 

}
