import { Component, OnInit } from '@angular/core';
import { SidebarMenuItem } from '../../shared/models/SidebarMenuItem';

@Component({
    selector: 'emaster-admin',
    templateUrl: './admin.component.html',
    styleUrls: [
        './admin.component.scss'
    ]
})
export class AdminComponent implements OnInit {
    menuItems: SidebarMenuItem[] = [
        new SidebarMenuItem('Category management', '', 'menu', false, [
            new SidebarMenuItem('All', '/user-management'),
            new SidebarMenuItem('Create new', '/user-management/create')
        ]),
        new SidebarMenuItem('User management', '/user-management'),
        new SidebarMenuItem('Statement management', '/statement-management'),
        new SidebarMenuItem('Question management', '/question-management'),
    ];

    ngOnInit() {
        
    }
}