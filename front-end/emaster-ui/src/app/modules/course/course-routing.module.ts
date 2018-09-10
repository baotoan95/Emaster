import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CourseComponent } from './course.component';
import { CourseMenuComponent } from './pages/course-menu/course-menu.component';
import { CategoriesComponent } from './pages/categories/categories.component';

const routes = [
    { 
        path: '', 
        component: CourseComponent,
        children: [
            {
                path: '',
                component: CourseMenuComponent
            },
            {
                path: 'categories',
                component: CategoriesComponent
            }
        ]
    }
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