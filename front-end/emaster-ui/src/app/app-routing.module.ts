import { Routes, RouterModule } from '@angular/router';
import { LandingComponent } from '../modules/landing/landing.component';

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
        loadChildren: '../modules/course/course.module#CourseModule',
        pathMatch: 'full'
    },
    {
        path: '**', redirectTo: 'index'
    },
];

export const ROUTES = RouterModule.forRoot(routes,
    {
        useHash: true,
    }
);
