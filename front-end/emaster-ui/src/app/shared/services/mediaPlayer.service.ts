import { Injectable } from "@angular/core";

@Injectable()
export class MediaPlayerService {
    audio: any;

    constructor() {
        this.audio = new Audio();
        this.audio.crossOrigin = 'anonymous';
    }
    
    playSound(resourceUrl: string) {
        this.audio.src = `http://localhost:8080/portal/resources/audio?url=${resourceUrl}`;
        this.audio.play();
    }

    pause() {
        this.audio.pause();
    }

    playWrongSound() {
        this.audio.src = '../../../assets/audio/depth.mp3';
        this.audio.play();
    }

    playCorrectSound() {
        this.audio.src = '../../../assets/audio/depth.mp3';
        this.audio.play();
    }
}