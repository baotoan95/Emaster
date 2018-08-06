import { NgModule } from '@angular/core';
import { SharedModule } from '../../../../shared/shared.module';
import { LearningComponent } from './learning.component';
import { MultipleChoosesComponent } from '../../components/multiple-chooses/multiple-chooses.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TranslateComponent } from '../../components/translation/translation.component';

const routes = [
    {
        path: '',
        component: LearningComponent,
        pathMath: 'full'
    }
]

@NgModule({
    imports: [
        SharedModule,
        CommonModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        LearningComponent,
        MultipleChoosesComponent,
        TranslateComponent
    ]
})
export class LearningModule {
    
}