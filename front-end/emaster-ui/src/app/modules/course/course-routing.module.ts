import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CourseComponent } from './course.component';

const routes = [
    { path: '', component: CourseComponent }
]

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class CourseRoutingModule { }