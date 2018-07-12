import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from '../shared/components/layout/header/header.component';
import { SideBarComponent } from '../shared/components/layout/sidebar/sidebar.component';
import { ContentHeaderComponent } from '../shared/components/layout/content-header/content-header.component';
import { ControlBarComponent } from '../shared/components/layout/controlbar/controlbar.component';
import { FooterComponent } from '../shared/components/layout/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SideBarComponent,
    ContentHeaderComponent,
    ControlBarComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
