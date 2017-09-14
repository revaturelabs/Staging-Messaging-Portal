import { Component } from '@angular/core';

@Component({
  selector: 'left-sidebar',
  templateUrl: '../html/left-sidebar.component.html',
  styleUrls: ['../css/left-sidebar.css']
})
export class LeftSideComponent {

/* Set the width of the side navigation to 0 */
 closeLeft() {
  document.getElementById("menuLeft").style.width = "0";
}

}