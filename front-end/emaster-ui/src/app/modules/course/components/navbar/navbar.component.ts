import { Component } from '@angular/core';
import { LocalStorageService } from '../../../../shared/services/localStorage.service';
import { Router } from '@angular/router';

@Component({
    selector: 'emaster-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: [ './navbar.component.scss' ]
})
export class NavbarComponent {
    constructor(private localStorage: LocalStorageService,
        private router: Router) {

    }

    logout() {
        this.localStorage.set('access_token', null);
        this.router.navigate(['/']);
    }
    
}