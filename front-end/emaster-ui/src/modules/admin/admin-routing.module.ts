import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminModule } from './admin.module';
import { AdminComponent } from './admin.component';
import { CategoryManagementModule } from './pages/category-management/category-management.module';

const routers = [
    {
        path: '',
        component: AdminComponent,
        pathMath: 'full'
    },
    {
        path: '/category-management',
        component: CategoryManagementModule,
        pathMath: 'full'
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routers)
    ],
    exports: [
        RouterModule
    ]
})
export class AdminRoutingModule {

}