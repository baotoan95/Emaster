import { NgModule } from '@angular/core';
import { CategoryManagementComponent } from './category-management.component';
import { CategoryManagementRoutingModule } from './category-management-routing.module';
import { CategoryDialogComponent } from './components/category-dialog/category-dialog.component';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../shared/shared.module';
import { PortalService } from '../../../../shared/services/portal.service';
import { SpinnerService } from '../../../../shared/services/spinner.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ConfirmDialogComponent } from '../../../../shared/components/confirm-dialog/confirm-dialog.component';

@NgModule({
    imports: [
        CategoryManagementRoutingModule,
        CommonModule,
        SharedModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [
        CategoryManagementComponent,
        CategoryDialogComponent,
        CategoryListComponent,
        ConfirmDialogComponent
    ],
    providers: [
        PortalService,
        SpinnerService
    ],
    entryComponents: [
        CategoryDialogComponent,
        ConfirmDialogComponent
    ]
})
export class CategoryManagementModule {
    
}