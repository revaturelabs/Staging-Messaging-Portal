import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

//component imports
import { AppComponent } from './app.component';
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
import { MessageViewComponent } from './message-view.component';
import { LandingPageComponent } from './landing-page.component';
import { LeftSideComponent } from './left-sidebar.component';
import { RightSideComponent } from './right-sidebar.component';
import { LoginFormComponent } from './login-form.component';
import { RegistrationFormComponent } from './registration-form.component';
import { PasswordRecoveryComponent } from './password-recovery.component'

//service imports
import { MessageService } from '../services/message.service';
import { AuthguardGuard} from '../services/authguard.guard';
import { UserService } from '../services/user.service'

import {AppRoutingModule} from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MessageViewComponent,
    LandingPageComponent,
    LeftSideComponent,
    RightSideComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    PasswordRecoveryComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    FormsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [ MessageService, UserService, AuthguardGuard, AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
