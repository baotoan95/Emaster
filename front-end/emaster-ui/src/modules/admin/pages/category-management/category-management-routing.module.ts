import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CategoryManagementComponent } from './category-management.component';
import { CategoryListComponent } from './components/category-list.component';
import { CategoryComponent } from './components/category.component';

const routes = [
    {
        path: '',
        component: CategoryManagementComponent,
        children: [
            {
                path: '',
                component: CategoryListComponent
            },
            {
                path: 'category',
                component: CategoryComponent
            }
        ]
    }
]

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class CategoryManagementRoutingModule {

}