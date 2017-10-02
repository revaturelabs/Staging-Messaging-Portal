import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { User } from '../classes/user';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class LoginService {
    private headers = new Headers({
        'Authorization': 'Basic dXNlcjp1c2Vy'
    })
    private options = new RequestOptions({headers: this.headers});
    private loginurl = 'http://localhost:8090/logger';
    constructor(private http: Http) {}

    login(user : User) : Promise<User>{
        var url = `${this.loginurl}/access`;

        return this.http.post(url, user,{headers:this.headers}).toPromise()
        .then(response => response.json())
        .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}