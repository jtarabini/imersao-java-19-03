import { Component, OnInit } from '@angular/core';
import { Marca } from '../entidades/marca';
import { MARCAS } from '../mock/marcas-mock';

@Component({
  selector: 'app-marcas',
  templateUrl: './marcas.component.html',
  styleUrls: ['./marcas.component.css']
})
export class MarcasComponent implements OnInit {

  entities : Marca[];
  selectedEntity : Marca;

  constructor() { }

  ngOnInit() {
    this.entities = MARCAS;
  }

  select(entity) {
    this.selectedEntity = entity;
  }

}
