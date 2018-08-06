import { Routes, RouterModule } from '@angular/router';
import { LandingComponent } from './modules/landing/landing.component';

const routes: Routes = [
    {
        path: '', redirectTo: 'index', pathMatch: 'full'
    },
    {
        path: 'index',
        component: LandingComponent,
        pathMatch: 'full'
    },
    {
        path: 'courses',
        loadChildren: './modules/course/course.module#CourseModule'
    },
    {
        path: 'learn',
        loadChildren: './modules/course/pages/learning/learning.module#LearningModule'
    },
    {
        path: 'admin',
        loadChildren: './modules/admin/admin.module#AdminModule'
    },
    // {
    //     path: '**', redirectTo: 'index'
    // }
];

export const ROUTES = RouterModule.forRoot(routes,
    {
        useHash: true,
    }
);
