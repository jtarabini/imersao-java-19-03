import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-cmp',
  templateUrl: './user-cmp.component.html',
  styleUrls: ['./user-cmp.component.css']
})
export class UserCmpComponent 
  implements OnInit {
  userList : User[] = [
    { 
      firstName : "Thiago ", 
      middleName : "Valverde de",
      lastName : "Souza"
    },
    { 
      firstName : "Simone", 
      middleName : "Leal",
      lastName : "kosmalski"
    },
    { 
      firstName : "Carmem", 
      middleName : "Suzana",
      lastName : "Vaz"
    }

  ];
  selectedUser : User = this.userList[0];

  constructor() { }

  ngOnInit() {
  }
  selectUser(user) {
    this.selectedUser = user;
  }

}

export class User {
  firstName : String;
  middleName : String;
  lastName : String;
}
