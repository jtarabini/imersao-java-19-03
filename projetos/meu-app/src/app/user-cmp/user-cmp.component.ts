import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-cmp',
  templateUrl: './user-cmp.component.html',
  styleUrls: ['./user-cmp.component.css']
})
export class UserCmpComponent implements OnInit {
  selectedUser : User = {
    firstName : "Thiago ", 
    middleName : "Valverde de",
    lastName : "Souza"
  }

  constructor() { }

  ngOnInit() {
  }

}
export class User {
  firstName : String;
  middleName : String;
  lastName : String;
}
