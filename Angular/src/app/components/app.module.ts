import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

//component imports
import { AppComponent } from './app.component';
import { MessageViewComponent } from './message-view.component';
import { LandingPageComponent } from './landing-page.component';
import { LeftSideComponent } from './left-sidebar.component';
import { RightSideComponent } from './right-sidebar.component';
import { LoginComponent } from './login.component';
import { RegisterComponent } from './register.component';
import { PasswordRecoveryComponent } from './password-recovery.component'

//service imports
import { MessageService } from '../services/message.service';


import {AppRoutingModule} from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    MessageViewComponent,
    LandingPageComponent,
    LeftSideComponent,
    RightSideComponent,
    LoginComponent,
    RegisterComponent,
    PasswordRecoveryComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [ MessageService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
