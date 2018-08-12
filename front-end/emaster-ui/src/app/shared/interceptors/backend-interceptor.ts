import { HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { environment } from '../../../environments/environment';
import { Observable } from "rxjs";
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { LocalStorageService } from "../services/localStorage.service";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";

@Injectable()
export class BackEndInterceptor implements HttpInterceptor {
    accessToken: string;

    constructor(private localStorageService: LocalStorageService,
        private router: Router) {

    }

    intercept(request: HttpRequest<any>, next: HttpHandler) {
        if(!this.isLogedIn() && request.url !== '/') {
            this.router.navigate(['/']);
        }

        const url = request.url;
        this.accessToken = 'Bearer ' + this.accessToken;

        if (url.startsWith('/portal/')) {
            request = request.clone({setHeaders: {
                Authorization: this.accessToken
            }});
            request = request.clone({
                url: `${environment.portal.host}:${environment.portal.port}${url}`
            });
        } else if(url.startsWith('/authorization/')) {
            request = request.clone({
                url: `${environment.authorization.host}:${environment.authorization.port}${url}`
            })
        }

        return next.handle(request).do(event => {

        }).catch(response => {
            console.log(response);
            return Observable.throw(response);
        });
    }

    isLogedIn(): boolean {
        const expiration_time = this.localStorageService.get('expiration_time');
        this.accessToken = this.localStorageService.get('access_token');

        if(this.accessToken && expiration_time && (Number(expiration_time) >= Date.now())) {
            return true;
        }

        return false;
    }
}