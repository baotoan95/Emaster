import { Injectable } from '@angular/core';

@Injectable()
export class SpinnerService {
    spinner: any;

    constructor() {
        this.spinner = document.getElementsByTagName('emaster-spinner')[0];
    }

    show() {
        this.spinner.children[0].classList.remove('undisplayed');
    }

    hide() {
        if(!this.spinner) {
            this.spinner = document.getElementsByTagName('emaster-spinner')[0];
        }
        
        if(this.spinner) {
            this.spinner.children[0].classList.add('undisplayed');
        }
    }
}