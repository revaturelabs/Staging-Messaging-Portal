import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  templateUrl: '../html/registration-form.component.html',
  styleUrls: ['../css/registration-form-style.css']
})


export class RegistrationFormComponent {

  constructor(private http: HttpClient) {}

  locations = ['Choose a location... ', 'Virginia', 'New York', 'Florida'];
  location = this.locations[0];

  
  onSubmit(registrationForm: NgForm) {
    let user = registrationForm.value;

    switch(user.location){
      case "Virginia":
        user.locationId = 1;
        break;
      case "New York":
        user.locationId = 2;
        break;
      case "Florida":
        user.locationId = 3;
        break;
    }
   

    this.http
          .post('/api/register-user', user)
          .subscribe();     

    // Validate location is NOT [0]   

    // Validate email form

    alert('Form submitted!');
  }
}
