import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  templateUrl: '../html/registration-form.component.html',
  styleUrls: ['../css/registration-form-style.css']
})

export class RegistrationFormComponent {

  constructor(private http: HttpClient, private router: Router) {}

  locations = ['Choose a location... ', 'Virginia', 'New York', 'Florida'];
  location = this.locations[0];

  regUser = function(user){
    let response = this.http
              .post('/api/register-user', user, {observe: 'response'});

    response.subscribe(
    res => {
      console.log(res);
      alert('Please wait for an email from your manager. Redirecting...');
      setTimeout(() => {
        this.router.navigate(['']);
      }, 5000);  //5s
    },
    err => {
      console.log("Error occured");
      console.log(err.status);
      console.log(err.error.message);
      alert(err.error.message);
    });
  }


  onSubmit(registrationForm: NgForm) {
    let user = registrationForm.value;

    switch(user.location){
      case this.locations[0]:
        alert("Choose a valid location");
        break;
      case "Virginia":
        user.locationId = 1;
        this.regUser(user);
        break;
      case "New York":
        user.locationId = 2;
        this.regUser(user);
        break;
      case "Florida":
        user.locationId = 3;
        this.regUser(user);
        break;
    }

  }
}
