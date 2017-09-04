import { Component, Input, OnInit} from '@angular/core';

import { Message } from './message';
import { MessageBlob } from './messageBlob'
import { MessageService } from './message.service'

@Component({
    selector: 'message-view',
    template: `
        <h2>Room #{{roomId}}</h2>
        <div><button (click)="loadMore()" *ngIf="hadstuff">Load Older Messages</button></div>
        <div *ngFor="let blob of blobs">
        <li *ngFor="let message of blob.messages">
            <span>{{message.time | date : "M/d/yy h:mm:ss a"}} - {{message.user}} : {{message.text}}</span>
        </li>
        </div>
        <div><button (click)="getUpdate()">Load New Messages</button></div>
        <div>
        <input [(ngModel)]="outmessage.user" placeholder = "name">
        <input [(ngModel)]="outmessage.text" placeholder = "text">
        <button (click)="post()">Send</button>
        </div>
    `,
    providers: [MessageService]
})

export class MessageViewComponent implements OnInit{
    @Input() roomId: number = 1;
    blobs: MessageBlob[]= [];
    oldest: number = 0;
    newest: number = 0;
    hadstuff: boolean = true;

    outmessage: Message = new Message;

    constructor(private messageService: MessageService){}

    getMessages(): void {
        this.messageService.getMostRecent(this.roomId)
            .then(blobs => {
                this.addBlobs(blobs);
                this.setNewest(blobs);
            })
            .catch(err=>console.log(err));
    }

    loadMore(): void {
        if(this.hadstuff){
            this.messageService.getPrevious(this.roomId,this.oldest)
                .then(blobs => this.addBlobs(blobs))
                .catch(err=>console.log(err));
        }
    }

    getUpdate(): void{
        this.messageService.getUpdate(this.roomId,this.newest)
            .then(blobs =>{
                this.updateBlobs(blobs);
                this.setNewest(blobs);
            })
            .catch(err=>console.log(err));
    }

    post(): void{
        var msg = this.outmessage;
        this.outmessage = new Message;
        this.messageService.post(this.roomId,msg)
            .then(()=>{
                this.getUpdate();
            })
            .catch(err=>console.log(err));
    }

    addBlobs(blobs:MessageBlob[]): void {
        this.blobs = blobs.concat(this.blobs);
        if(blobs.length>0){
            this.oldest = blobs[0].blobId;
        }else{
            this.hadstuff = false;
        }
    }

    updateBlobs(blobs:MessageBlob[]): void {
        if(this.blobs.length>0 && blobs.length>0){
            this.blobs.splice(this.blobs.length-1,1);
            this.blobs = this.blobs.concat(blobs);
        }else if(blobs.length>0){
            this.blobs = blobs;
        }
    }

    setNewest(blobs:MessageBlob[]): void {
        if(blobs.length>0){
            this.newest = blobs[blobs.length-1].blobId;
        }
    }

    ngOnInit(): void {
        this.getMessages();
    }

}