import { Component, Input } from '@angular/core';
import { SidebarMenuItem } from '../../../../../shared/models/SidebarMenuItem';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { SidebarService } from '../../../../../shared/services/sidebar.service';
import { Router } from '@angular/router';

@Component({
    selector: 'emaster-sidebar-menu-item',
    templateUrl: './sidebar-menu-item.component.html',
    styleUrls: [
        './sidebar-menu-item.component.scss'
    ],
    animations: [
        trigger('indicatorRotate', [
            state('collapsed', style({ transform: 'rotate(0deg)' })),
            state('expanded', style({ transform: 'rotate(180deg)' })),
            transition('expanded <=> collapsed',
                animate('225ms cubic-bezier(0.4,0.0,0.2,1)')
            ),
        ])
    ]
})
export class SidebarMenuItemComponent {
    @Input() item: SidebarMenuItem;
    @Input() depth: number;
    expanded: boolean;

    constructor(private sidebarService: SidebarService,
        private router: Router) {
        if(this.depth === undefined) {
            this.depth = 0;
        }
    }

    selectItem(item: SidebarMenuItem) {
        if(item.children && item.children.length) {
            this.expanded = !this.expanded;
        } else {
            this.sidebarService.closeDrawer();
            console.log(item.route);
            this.router.navigate([item.route]);
        }
    }
}