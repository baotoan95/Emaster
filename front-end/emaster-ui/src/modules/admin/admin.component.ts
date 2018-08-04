import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { SidebarMenuItem } from '../../shared/models/SidebarMenuItem';
import { SidebarService } from '../../shared/services/sidebar.service';
import { MatDrawer } from '@angular/material';

@Component({
    selector: 'emaster-admin',
    templateUrl: './admin.component.html',
    styleUrls: [
        './admin.component.scss'
    ]
})
export class AdminComponent implements AfterViewInit {
    @ViewChild('drawer') drawer: MatDrawer;

    menuItems: SidebarMenuItem[] = [
        new SidebarMenuItem('Category management', '', 'menu', false, [
            new SidebarMenuItem('All', 'admin/category-management'),
            new SidebarMenuItem('Create new', 'admin/category-management/category')
        ]),
        new SidebarMenuItem('User management', '/user-management'),
        new SidebarMenuItem('Statement management', '/statement-management'),
        new SidebarMenuItem('Question management', '/question-management'),
    ];

    constructor(private sidebarService: SidebarService) {

    }

    ngAfterViewInit() {
        this.sidebarService.appDrawer = this.drawer;
    }
}