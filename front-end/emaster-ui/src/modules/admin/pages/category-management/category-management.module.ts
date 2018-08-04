import { NgModule } from '@angular/core';
import { CategoryManagementComponent } from './category-management.component';
import { CategoryManagementRoutingModule } from './category-management-routing.module';
import { CategoryComponent } from './components/category.component';
import { CategoryListComponent } from './components/category-list.component';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../shared/shared.module';

@NgModule({
    imports: [
        CategoryManagementRoutingModule,
        CommonModule,
        SharedModule
    ],
    declarations: [
        CategoryManagementComponent,
        CategoryComponent,
        CategoryListComponent
    ]
})
export class CategoryManagementModule {
    
}