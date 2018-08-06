import { NgModule } from '@angular/core';

import { LandingComponent } from './landing.component';
import { LandingRoutingModule } from './landing-routing.module';
import { SharedModule } from '../../shared/shared.module';

import { ScrollToModule } from 'ng2-scroll-to-el';
import { CommonModule } from '@angular/common';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

@NgModule({
    declarations: [
        LandingComponent
    ],
    imports: [
        LandingRoutingModule,
        SharedModule,
        ScrollToModule,
        CommonModule,
        TranslateModule.forChild({})
    ],
    providers: [
        TranslateService
    ]
})
export class LandingModule { }