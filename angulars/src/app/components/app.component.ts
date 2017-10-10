import { Component } from '@angular/core';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: '../html/app.component.html',
  styleUrls: ['../css/app.component.css']
})
export class AppComponent {
  title = 'Staging Messaging Portal';
  private apiUrl = 'http://localhost:8090/users';
  data: any = {};

constructor(private http: Http) {
}



}
