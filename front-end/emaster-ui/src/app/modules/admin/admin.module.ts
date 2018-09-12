import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../shared/shared.module';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { MatSidenavModule } from '@angular/material';
import { SidebarMenuModule } from './components/sidebar-menu/sidebar-menu.module';
import { SidebarService } from '../../shared/services/sidebar.service';
import { CategoryManagementModule } from './pages/category-management/category-management.module';
import { UserManagementModule } from './pages/user-management/user-management.module';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        AdminRoutingModule,
        MatSidenavModule,
        SidebarMenuModule,
        CategoryManagementModule,
        UserManagementModule
    ],
    declarations: [
        AdminComponent
    ],
    providers: [
        SidebarService
    ]
})
export class AdminModule {
    
}