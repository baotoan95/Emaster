import { NgModule } from '@angular/core';
import { CourseRoutingModule } from './course-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { CourseComponent } from './course.component';
import { PortalService } from '../../shared/services/portal.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

@NgModule({
    imports: [
        SharedModule,
        CourseRoutingModule,
        CommonModule,
        TranslateModule.forChild({})
    ],
    declarations: [
        CourseComponent,
        NavbarComponent
    ],
    providers: [
        PortalService,
        TranslateService
    ]
})
export class CourseModule {

}