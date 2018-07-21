import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { LandingModule } from '../modules/landing/landing.module';
import { ROUTES } from './app-routing.module';
import { SharedModule, createTranslateLoader } from '../shared/shared.module';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import { HttpClient } from '@angular/common/http';
import { LocalStorageService } from '../shared/services/localStorage.service';
import { Utilities } from '../shared/helpers/utilities';
import { PortalService } from '../shared/services/portal.service';
import { SpinnerComponent } from '../shared/components/spinner/spinner.component';


@NgModule({
  declarations: [
    AppComponent,
    SpinnerComponent
  ],
  imports: [
    BrowserModule,
    ROUTES,
    LandingModule,
    BrowserAnimationsModule,
    SharedModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    TranslateService,
    LocalStorageService,
    Utilities,
    PortalService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
