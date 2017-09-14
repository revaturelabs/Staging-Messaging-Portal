import { Component } from '@angular/core';

@Component({
  selector: 'landing-page',
  templateUrl: '../html/landing-page.component.html',
  styleUrls: ['../css/landing-page.css']
})
export class LandingPageComponent {
  
/* Set the width of the side navigation to 250px */
 openLeft() {
  document.getElementById("menuLeft").style.width = "250px";
  document.getElementById("menuRight").style.width = "0";
}

 openRight() {
  document.getElementById("menuRight").style.width = "250px";
  document.getElementById("menuLeft").style.width = "0";
}

}
