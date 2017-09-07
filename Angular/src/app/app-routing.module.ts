import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
 
import { MessageViewComponent }   from './message-view.component';
import { LandingPageComponent } from './landing-page.component';
 
const routes: Routes = [
  { path: '', redirectTo: '/landingpage', pathMatch: 'full' },
  { path: 'landingpage',  component: LandingPageComponent }
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}