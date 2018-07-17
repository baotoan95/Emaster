import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';

const routesConfig = [
    { path: '/', component: AppComponent },
    { path: '/landing', loadChildren: 'app/modules/landing/landing.module#LandingModule' }
]

@NgModule({
    imports: [
        RouterModule.forRoot(routesConfig)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {

}