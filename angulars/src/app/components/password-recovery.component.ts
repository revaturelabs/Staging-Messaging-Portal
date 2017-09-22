import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'password-recovery',
  templateUrl: '../html/password-recovery.component.html',
  styleUrls: ['../css/password-recovery.css']
})
export class PasswordRecoveryComponent {

  constructor(private http: HttpClient, private router: Router) {}

  resetUser = function(useremail){
    console.log("In the password-recovery.component.ts");
    
    let response = this.http.post('/api/password-recovery', useremail, {Observe: 'response'});
   
    response.subscribe(
      res => {
        console.log(res);
        alert('An email with your new TEMPORARY password will be arriving shortly. Please remember to reset it after you log in. Redirecting...');
        setTimeout(() => {
          this.router.navigate(['']);
        }, 5000);  //5s
      },
      err => {
        console.log("Error occured");
        console.log(err.status);
        console.log(err.error.message);
        alert(err.error.message);
      }
    );
  }

  onSubmit(passwordRecoveryForm: NgForm) {
    let useremail = passwordRecoveryForm.value;
    console.log("useremail is: " + useremail.email);
    console.log("In end of onSubmit");
    this.resetUser(useremail);
    
  }
}