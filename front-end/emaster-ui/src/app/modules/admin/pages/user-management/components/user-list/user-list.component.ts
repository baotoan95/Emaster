import { Component, OnInit } from "@angular/core";
import { PortalService } from "../../../../../../shared/services/portal.service";

@Component({
    selector: 'emaster-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: [
        './user-list.component.scss'
    ]
})
export class UserListComponent implements OnInit {
    users: any[];
    currentPage = 1;
    pageSize = 5;
    throttle = 300;
    scrollDistance = 1;
    scrollUpDistance = 2;
    infiniteContainer = ".users";

    constructor(private portalService: PortalService) {

    }

    ngOnInit() {
        this.portalService.user.getAll(this.currentPage, this.pageSize).subscribe(data => {
            this.users = data.content;
            console.log(this.users);
        }, err => {
            
        });
    }

    showMore(ev) {
        console.log(ev);
        this.currentPage++;
        this.portalService.user.getAll(this.currentPage, this.pageSize).subscribe(data => {
            this.users = this.users.concat(data.content);
            console.log(this.users);
        }, err => {
            
        });
    }
}