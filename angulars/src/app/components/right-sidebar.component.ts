import { Component, Input, OnInit } from '@angular/core';

import { User } from '../classes/user';

import { UserService } from '../services/user.service';

@Component({
  selector: 'right-sidebar',
  templateUrl: '../html/right-sidebar.component.html',
  styleUrls: ['../css/right-sidebar.css'],
  providers: [UserService]
})
export class RightSideComponent {
  @Input() loggedInUser : User;
  usernames : string[] = new Array<string>();
  users : User[] = new Array<User>();

  constructor(
    private userService: UserService
  ){}

  ngOnInit(): void {
    this.getUsernames();
    // Setup web socket
  }

  /* Set the width of the side navigation to 0 */
  closeRight() {
    document.getElementById("menuRight").style.width = "0";
  }

  getUsernames() : void {
    var usernames = new Array<string>();

    this.userService.listUsernames()
      .then(response => response.forEach(username => this.usernames.push(username)))
      .catch(err=>console.log(err)
    );
  }

  getUsers() : void {
    // Dummy manager so that can display list of users
    var user = new User;
    user.username = "stanleym";

    var userList = new Array<User>();

    this.userService.listUsers(user.username)
      .then(response => this.updateUsers(response))
      .catch(err=>console.log(err)
    );

    // Assign the usernames from the returned users
    this.users.forEach(user => this.usernames.push(user.username));
  }

  // Callback method to take the returned list of users and update the list of 
  // users and usernames accordingly
  private updateUsers(userList : User[]) : void {
    userList.forEach(user => this.users.push(user));
    this.users.forEach(user => this.usernames.push(user.username));
  }
}