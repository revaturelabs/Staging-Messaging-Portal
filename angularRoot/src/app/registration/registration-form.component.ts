import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration.component.html'
})

export class RegistrationFormComponent {

  locations = ['Virginia', 'New York', 'Florida'];

  onSubmit(registrationForm: NgForm) {
    console.log(registrationForm.value);
  }
}
