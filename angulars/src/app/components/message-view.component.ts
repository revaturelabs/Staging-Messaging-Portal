import { Component, Input, OnInit} from '@angular/core';

import { Message } from '../classes/message';
import { MessageBlob } from '../classes/messageBlob'
import { MessageService } from '../services/message.service'

@Component({
    selector: 'message-view',
    templateUrl: `../html/message-view.component.html`,
    providers: [MessageService]
})

export class MessageViewComponent implements OnInit{
    @Input() roomId: number = 1;
    blobs: MessageBlob[]= [];
    messages: Message[] = [];
    oldest: number = 0;
    newest: number = 0;
    hadstuff: boolean = true;

    outmessage: Message = new Message;

    constructor(
        private messageService: MessageService
    ){}

    getMessages(): void {
        this.messageService.getMostRecent(this.roomId)
            .then(blobs => {
                this.addBlobs(blobs);
                this.setNewest(blobs);
            })
            .catch(err=>console.log(err));
    }

    getPublic(): void {
        this.messageService.getPublic()
        .then(messages => messages.forEach(msg => this.messages.push(msg)))
        .catch(err=>console.log(err));
    }

    getUpdate(): void{
        if(this.newest != 0){
            this.messageService.getUpdate(this.roomId,this.newest)
                .then(blobs =>{
                    this.updateBlobs(blobs);
                    this.setNewest(blobs);
                })
                .catch(err=>console.log(err));
        }else{
            this.getMessages();
        }
    }

    loadMore(): void {
        if(this.hadstuff){
            this.messageService.getPrevious(this.roomId,this.oldest)
                .then(blobs => this.addBlobs(blobs))
                .catch(err=>console.log(err));
        }
    }

    post(): void{
        var msg = this.outmessage;
        msg.roomId = this.roomId;

        this.outmessage = new Message; // Clear the message

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
        this.getPublic();
    }
}