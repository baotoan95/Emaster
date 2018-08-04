import { Component, Input } from '@angular/core';
import { SidebarMenuItem } from '../../../../shared/models/SidebarMenuItem';

@Component({
    selector: 'emaster-sidebar-menu',
    templateUrl: './sidebar-menu.component.html',
    styleUrls: [
        './sidebar-menu.component.scss'
    ]
})
export class SidebarMenuComponent {
    @Input("items") items: SidebarMenuItem[];
}