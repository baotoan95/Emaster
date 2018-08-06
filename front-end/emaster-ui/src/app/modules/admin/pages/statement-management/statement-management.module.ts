import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../shared/shared.module';
import { PortalService } from '../../../../shared/services/portal.service';
import { RouterModule, Routes } from '@angular/router';
import { StatementManagementComponent } from './statement-management.component';
import { StatementListComponent } from './components/statement-list/statement-list.component';

const routes: Routes = [
    {
        path: '',
        component: StatementManagementComponent,
        pathMatch: 'full',
        children: [
            {
                path: '',
                component: StatementListComponent
            }
        ]
    }
]

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        StatementManagementComponent,
        StatementListComponent
    ],
    providers: [
        PortalService
    ]
})
export class StatementManagementModule {

}