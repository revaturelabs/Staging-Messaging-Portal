import { Message } from './message';

export class MessageBlob {
    blobId: number;
    messages: Message[];
    roomId: number;
}