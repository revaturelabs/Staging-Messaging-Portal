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
  console.log('For testing purposes login username and password is admin');
  this.getContacts();
  this.getData;
}


  getData() {
    return this.http.get(this.apiUrl).map((res: Response)=> res.json())
  }


  getContacts() {
    this.getData().subscribe(data => { console.log(data);
                              this.data=data})
  }

}
