import { NgModule } from '@angular/core';

// i18n
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

import { NgProgressModule } from 'ngx-progressbar';

import {
    MatCardModule,
    MatDialogModule,
    MatTooltipModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatProgressBarModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule
} from '@angular/material';

const MATERIALNG_DEPENDENCIES = [
    MatDialogModule,
    MatCardModule,
    MatTooltipModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatProgressBarModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule
];

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, '../assets/i18n/', '.json');
}

@NgModule({
    declarations: [
    ],
    imports: [
        ...MATERIALNG_DEPENDENCIES,
        TranslateModule.forChild({}),
        NgProgressModule
    ],
    exports: [
        ...MATERIALNG_DEPENDENCIES
    ],
    providers: [
    ]
})
export class SharedModule { }