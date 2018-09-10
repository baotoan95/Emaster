import { NgModule } from '@angular/core';
import { CourseRoutingModule } from './course-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { CourseComponent } from './course.component';
import { PortalService } from '../../shared/services/portal.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { CourseMenuComponent } from './pages/course-menu/course-menu.component';
import { CategoriesComponent } from './pages/categories/categories.component';

@NgModule({
    imports: [
        SharedModule,
        CourseRoutingModule,
        CommonModule,
        TranslateModule.forChild({})
    ],
    declarations: [
        CourseComponent,
        NavbarComponent,
        CourseMenuComponent,
        CategoriesComponent
    ],
    providers: [
        PortalService,
        TranslateService
    ]
})
export class CourseModule {

}