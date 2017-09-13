import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { RegistrationFormComponent } from './registration/registration-form.component';

@NgModule({
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'register', component: RegistrationFormComponent}])
  ],
  declarations: [
    AppComponent,
    RegistrationFormComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
