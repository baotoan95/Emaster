import { NgModule } from '@angular/core';
import { CourseRoutingModule } from './course-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { CourseComponent } from './course.component';
import { PortalService } from '../../shared/services/portal.service';

@NgModule({
    imports: [
        SharedModule,
        CourseRoutingModule
    ],
    declarations: [
        CourseComponent
    ],
    providers: [
        PortalService
    ]
})
export class CourseModule {

}