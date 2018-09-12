import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { UserManagementComponent } from "./user-management.component";
import { CommonModule } from "@angular/common";
import { SharedModule } from "../../../../shared/shared.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PortalService } from "../../../../shared/services/portal.service";
import { MediaPlayerService } from "../../../../shared/services/mediaPlayer.service";
import { UserListComponent } from "./components/user-list/user-list.component";
import { InfiniteScrollModule } from 'ngx-infinite-scroll';

const routes: Routes = [
    {
        path: '',
        component: UserManagementComponent,
        children: [
            {
                path: '',
                component: UserListComponent,
                pathMatch: 'full'
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
        ReactiveFormsModule,
        InfiniteScrollModule
    ],
    declarations: [
        UserManagementComponent,
        UserListComponent
    ],
    providers: [
        PortalService,
        MediaPlayerService
    ]
})
export class UserManagementModule {

}