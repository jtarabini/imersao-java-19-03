import { Component, OnInit } from '@angular/core';
import { Marca } from '../entidades/marca';
import { MARCAS } from '../mock/marcas-mock';
import { MarcaService } from '../services/marca.service'

@Component({
  selector: 'app-marcas',
  templateUrl: './marcas.component.html',
  styleUrls: ['./marcas.component.css']
})
export class MarcasComponent implements OnInit {

  entities : Marca[];
  selectedEntity : Marca;

  constructor(private marcaService : MarcaService) { }

  ngOnInit() {
    this.getMarcas();
  }

  getMarcas() {
    this.marcaService.getMarcas()
      .subscribe( marcas => this.entities = marcas );
  }

  select(entity) {
    this.selectedEntity = entity;
  }

}
