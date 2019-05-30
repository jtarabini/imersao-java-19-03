import { Component, OnInit } from '@angular/core';
import { Marca } from '../entidades/marca';
import { MARCAS } from '../mock/marcas-mock';
import { MarcasService } from '../services/marcas.service';

@Component({
  selector: 'app-marcas',
  templateUrl: './marcas.component.html',
  styleUrls: ['./marcas.component.css']
})
export class MarcasComponent implements OnInit {

  entities : Marca[];
  selectedEntity : Marca;

  constructor(private marcasService : MarcasService) { }

  ngOnInit() {
    this.getMarcas();
  }

  getMarcas() {
    this.marcasService.getMarcas().subscribe(
      marcas => this.entities = marcas
    );
  }

  select(entity) {
    this.selectedEntity = entity;
  }

}
