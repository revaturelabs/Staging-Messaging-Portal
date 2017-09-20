import { Component } from '@angular/core';

@Component({
  selector: 'right-sidebar',
  templateUrl: '../html/right-sidebar.component.html',
  styleUrls: ['../css/right-sidebar.css']
})
export class RightSideComponent {

/* Set the width of the side navigation to 0 */
 closeRight() {
  document.getElementById("menuRight").style.width = "0";
}

}