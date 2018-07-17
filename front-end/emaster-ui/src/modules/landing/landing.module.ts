import { NgModule } from '@angular/core';

import { LandingComponent } from './landing.component';
import { LandingRoutingModule } from './landing-routing.module';

@NgModule({
    declarations: [
        LandingComponent
    ],
    imports: [
        LandingRoutingModule
    ]
})
export class LandingModule { }