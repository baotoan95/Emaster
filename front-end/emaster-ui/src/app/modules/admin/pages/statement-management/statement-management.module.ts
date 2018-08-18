import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../shared/shared.module';
import { PortalService } from '../../../../shared/services/portal.service';
import { RouterModule, Routes } from '@angular/router';
import { StatementManagementComponent } from './statement-management.component';
import { StatementListComponent } from './components/statement-list/statement-list.component';
import { StatementComponent } from './components/statement/statement.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AutocompleteMultiSelectionComponent } from './components/autocomplete-multi-selection/autocomplete-multi-selection.component';

const routes: Routes = [
    {
        path: '',
        component: StatementManagementComponent,
        children: [
            {
                path: '',
                component: StatementListComponent,
                pathMatch: 'full'
            },
            {
                path: ':{action}',
                component: StatementComponent
            }
        ]
    }
]

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        RouterModule.forChild(routes),
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [
        StatementManagementComponent,
        StatementListComponent,
        StatementComponent,
        AutocompleteMultiSelectionComponent
    ],
    providers: [
        PortalService
    ]
})
export class StatementManagementModule {

}