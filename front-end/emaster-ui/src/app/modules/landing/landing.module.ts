import { NgModule } from '@angular/core';

import { LandingComponent } from './landing.component';
import { LandingRoutingModule } from './landing-routing.module';
import { SharedModule } from '../../shared/shared.module';

import { ScrollToModule } from 'ng2-scroll-to-el';
import { CommonModule } from '@angular/common';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { LoginDialogComponent } from './components/login-dialog/login-dialog.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthoriztionSevice } from '../../shared/services/authorization.service';
import { SpinnerService } from '../../shared/services/spinner.service';
import { AlertDialogComponent } from '../../shared/components/alert-dialog/alert-dialog.component';

@NgModule({
    declarations: [
        LandingComponent,
        LoginDialogComponent,
        AlertDialogComponent
    ],
    imports: [
        LandingRoutingModule,
        SharedModule,
        ScrollToModule,
        CommonModule,
        TranslateModule.forChild({}),
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [
        TranslateService,
        AuthoriztionSevice,
        SpinnerService
    ],
    entryComponents: [
        LoginDialogComponent,
        AlertDialogComponent
    ]
})
export class LandingModule { }