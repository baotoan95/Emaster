import { EventEmitter, Injectable } from '@angular/core';

@Injectable()
export class SidebarService {
    public appDrawer: any;

    constructor() {}

    public closeDrawer() {
        this.appDrawer.close();
    }

    public openDrawer() {
        this.appDrawer.open();
    }
}