import { Component, OnInit, Input } from '@angular/core';
import { SidebarMenuItem } from '../../../../shared/models/SidebarMenuItem';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
    selector: 'emaster-sidebar-menu',
    templateUrl: './sidebar-menu.component.html',
    styleUrls: [
        './sidebar-menu.component.scss'
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
export class SidebarMenuComponent implements OnInit {
    @Input("items") items: SidebarMenuItem[];
    expanded: boolean;

    ngOnInit() {

    }

    selectItem(item: SidebarMenuItem) {
        if(item.children && item.children.length) {
            this.expanded = !this.expanded;
        } else {
            
        }
    }
}