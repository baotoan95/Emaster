import { NgModule } from '@angular/core';

// i18n
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule } from '@ngx-translate/core';
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
    MatInputModule,
    MatSnackBarModule,
    MatRadioModule,
    MatToolbarModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatTabsModule
} from '@angular/material';
import { FormsModule } from '@angular/forms';

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
    MatSnackBarModule,
    MatProgressBarModule,
    MatRadioModule,
    FormsModule,
    MatToolbarModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatTabsModule
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
        NgProgressModule,
        HttpClientModule
    ],
    exports: [
        ...MATERIALNG_DEPENDENCIES
    ],
    providers: [
    ]
})
export class SharedModule { }