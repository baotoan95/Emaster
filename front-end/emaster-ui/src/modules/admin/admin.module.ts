import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { MatSidenavModule } from '@angular/material';
import { SidebarMenuComponent } from './components/sidebar-menu/sidebar-menu.component';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        AdminRoutingModule,
        MatSidenavModule
    ],
    declarations: [
        AdminComponent,
        SidebarMenuComponent
    ],
    providers: [

    ]
})
export class AdminModule {

}