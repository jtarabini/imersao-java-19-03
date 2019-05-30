import { Injectable } from '@angular/core';
import { Marca } from '../entidades/marca';
import { MARCAS } from '../mock/marcas-mock';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MarcasService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getMarcas() : Observable<Marca[]> {
    return this.http.get<Marca[]>('/api/marcas');
  }
  save(marca : Marca) : Observable<Marca> {
    console.log('entrou no service')
    var url = 'api/marcas/'+marca.id;
    console.log('vai chamar a url '+url)
    return this.http.post<Marca>(url, marca, this.httpOptions);

  }
}
