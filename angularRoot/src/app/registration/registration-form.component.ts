import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form-style.css']
})

export class RegistrationFormComponent {

  locations = ['Choose a location... ', 'Virginia', 'New York', 'Florida'];
  location = this.locations[0];

  onSubmit(registrationForm: NgForm) {
    console.log(registrationForm.value);
    // Validate location is NOT [0]

    // Validate email form

    alert('Form submitted!');
  }
}
