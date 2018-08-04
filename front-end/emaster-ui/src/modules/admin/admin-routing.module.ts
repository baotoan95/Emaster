import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';

const routes = [
    {
        path: '',
        component: AdminComponent,
        children: [
            {
                path: 'category-management',
                loadChildren: './pages/category-management/category-management.module#CategoryManagementModule'
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AdminRoutingModule {

}