import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Message } from '../classes/message';
import { MessageBlob } from '../classes/messageBlob';

@Injectable()
export class MessageService {
    private headers = new Headers({
        'Authorization': 'Basic dXNlcjp1c2Vy'
    })
    private options = new RequestOptions({headers: this.headers});
    private messageurl = 'http://localhost:8090/msg'
    constructor(private http: Http) {}

    getByRoom(roomId:number): Promise<Message[]> {
        var url = `${this.messageurl}/fetch-room/${roomId}`;

        return this.http.get(url,{headers:this.headers}).toPromise()
        .then(response => response.json())
        .catch(this.handleError)
    }

    getMostRecent(roomId:number): Promise<MessageBlob[]> {
        var url = `${this.messageurl}/getmostrecent/${roomId}`;
        
        return this.http.get(url,{headers:this.headers}).toPromise()
            .then(response => this.decode(response.json()))
            .catch(this.handleError);
    }

    getPrevious(roomId:number,blobId:number): Promise<MessageBlob[]> {
        var url = `${this.messageurl}/getprevious/${roomId}/${blobId}`;
        
        return this.http.get(url,{headers:this.headers}).toPromise()
            .then(response => this.decode(response.json()))
            .catch(this.handleError);
    }

    getPublic(): Promise<Message[]> {
        var url = `${this.messageurl}/fetch-room/public`;
        
        return this.http.get(url,{headers:this.headers}).toPromise()
        .then(response => response.json().messages)
        .catch(this.handleError);
    }

    getUpdate(roomId:number,blobId:number): Promise<MessageBlob[]>{
        var url = `${this.messageurl}/getupdate/${roomId}/${blobId}`;
        
        return this.http.get(url,{headers:this.headers}).toPromise()
            .then(response => this.decode(response.json()))
            .catch(this.handleError);
    }

    post(roomId:number,message:Message): Promise<void>{
        var url = `${this.messageurl}/post`;

        return this.http.post(url, message,{headers:this.headers}).toPromise()
            .then(response => {})
            .catch(this.handleError);
    }

    private decode(code): MessageBlob[]{
        return code.map(blob=>
        {
            return {
                blobId: blob.messageBlobId,
                messages: JSON.parse(window.atob(blob.messageBlob)) as Message[],
                roomId: blob.messageRoomId
            }
        })
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}