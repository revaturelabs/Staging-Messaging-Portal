import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../classes/user';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'login-form',
  templateUrl: '../html/login-form.component.html',
  styleUrls: ['../css/login-form.component.css'],
  providers: [LoginService]
})
export class LoginFormComponent implements OnInit {
  @Input() user : User = new User;

  constructor(
    private router:Router,
    private userService: UserService,
    private loginService: LoginService
  ){}

  ngOnInit() {
  }

  // Checks the returned user from the service. 
  // If the user exists, routes to the landing page
  private checkLogin(user : User) : void{

    // If the user is valid (username defined),
    // go to the landing page
    if(user.username !== undefined){
      // Navigate to landing page
      this.router.navigate(['landingpage']);
    }
  }

  login(e) : void {
    // Get the user information located at these locations
    // in the form.
    this.user.username = e.target.elements[0].value;
    this.user.password = e.target.elements[1].value;

    // Attempt to login. Check if the login was successful
    // (a user was returned)
    this.loginService.login(this.user)
      .then(response => this.checkLogin(response))
      .catch(err=>console.log(err)
    );
  }

  loginUser(e) : void {
    e.preventDefault();
    console.log(e);
    var username = e.target.elements[0].value;
    var password = e.target.elements[1].value;
    
    if(username == 'admin' && password == 'admin'){
      this.userService.setUserLoggedIn();
      this.router.navigate(['landingpage']);
    }
    else{
    }

  }

}