import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
 
import { MessageViewComponent }   from './message-view.component';
import { LandingPageComponent } from './landing-page.component';
import { LoginFormComponent } from './login-form.component';
import { RegistrationFormComponent } from './registration-form.component';
import { PasswordRecoveryComponent } from './password-recovery.component';
 
const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegistrationFormComponent },
  { path: 'passwordrecover', component: PasswordRecoveryComponent },
  { path: 'landingpage',  component: LandingPageComponent }
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}