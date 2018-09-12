import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';

const routes = [
    {
        path: '',
        component: AdminComponent,
        children: [
            {
                path: '',
                redirectTo: 'category-management',
                pathMatch: 'full'
            },
            {
                path: 'category-management',
                loadChildren: './pages/category-management/category-management.module#CategoryManagementModule'
            },
            {
                path: 'statement-management',
                loadChildren: './pages/statement-management/statement-management.module#StatementManagementModule'
            },
            {
                path: 'user-management',
                loadChildren: './pages/user-management/user-management.module#UserManagementModule'
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