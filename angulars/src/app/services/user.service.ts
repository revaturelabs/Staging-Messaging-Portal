import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { User } from '../classes/user';

@Injectable()
export class UserService {

  private isUserLoggedIn;
  private username;

  // Used for connecting to the service endpoint
  private headers = new Headers({
    'Authorization': 'Basic dXNlcjp1c2Vy'
  });
  private options = new RequestOptions({headers: this.headers});
  private userurl = 'http://localhost:8090/logger';

  constructor(private http: Http) {
    this.isUserLoggedIn = false;
  }

  listUsernames() : Promise<string[]> {
    var url = `${this.userurl}/list-usernames`;

    return this.http.get(url, {headers:this.headers}).toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  listUsers(username : string) : Promise<User[]> {
    var url = `${this.userurl}/list-users/${username}`;

    return this.http.get(url, {headers:this.headers}).toPromise()
      .then(response => response.json())
      .catch(this.handleError);
  }

  setUserLoggedIn(){
    this.isUserLoggedIn = true;
  }

  getUserLoggedIn(){
    return this.isUserLoggedIn;
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
