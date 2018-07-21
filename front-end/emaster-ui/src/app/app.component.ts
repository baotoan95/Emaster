import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { SupportedLanguages } from '../shared/models/localization.model';
import { LocalStorageService } from '../shared/services/localStorage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [
    TranslateService,
    LocalStorageService
  ]
})
export class AppComponent {
  title = 'app';

  constructor(private translateService: TranslateService,
    private localStorageService: LocalStorageService) {
    // Add supported languages
    this.translateService.addLangs(SupportedLanguages.map(lang => lang.code));

    // Set current language
    let selectedLanguage = localStorageService.get('selectedLanguage');
    if(selectedLanguage === undefined) {
      // Get browser's default language
      const browswerLanguage = this.translateService.getBrowserLang();
      selectedLanguage = browswerLanguage.match(/en/) ? browswerLanguage : SupportedLanguages[0].code;
      // Set to localStorage
      localStorageService.set('selectedLanguage', selectedLanguage);
    }
    
    this.translateService.use(selectedLanguage);
  }
}
