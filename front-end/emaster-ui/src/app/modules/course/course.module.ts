import { NgModule } from '@angular/core';
import { CourseRoutingModule } from './course-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { CourseComponent } from './course.component';
import { PortalService } from '../../shared/services/portal.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';

@NgModule({
    imports: [
        SharedModule,
        CourseRoutingModule,
        CommonModule
    ],
    declarations: [
        CourseComponent,
        NavbarComponent
    ],
    providers: [
        PortalService
    ]
})
export class CourseModule {

}