import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../shared/shared.module';
import { SidebarMenuComponent } from './sidebar-menu.component';
import { SidebarMenuItemComponent } from './sidebar-menu-item/sidebar-menu-item.component';

@NgModule({
    imports: [
        CommonModule,
        SharedModule
    ],
    declarations: [
        SidebarMenuComponent,
        SidebarMenuItemComponent
    ],
    exports: [
        SidebarMenuComponent,
        SidebarMenuItemComponent
    ]
})
export class SidebarMenuModule {

}