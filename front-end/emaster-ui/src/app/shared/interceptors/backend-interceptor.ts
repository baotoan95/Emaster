import { HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { environment } from '../../../environments/environment';
import { Observable } from "rxjs";
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { LocalStorageService } from "../services/localStorage.service";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { SpinnerService } from "../services/spinner.service";
import { MatSnackBar } from "@angular/material";

@Injectable()
export class BackEndInterceptor implements HttpInterceptor {
    accessToken: string;

    constructor(private localStorageService: LocalStorageService,
        private router: Router,
        private spinner: SpinnerService,
        private snackBar: MatSnackBar) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler) {
        if (!this.isLoggedIn() && request.url !== '/') {
            this.router.navigate(['/']);
        }

        const url = request.url;
        this.accessToken = 'Bearer ' + this.accessToken;

        if (url.startsWith('/portal/')) {
            request = request.clone({
                setHeaders: {
                    Authorization: this.accessToken
                }
            });
            request = request.clone({
                url: `${environment.portal.host}:${environment.portal.port}${url}`
            });
        } else if (url.startsWith('/authorization/')) {
            request = request.clone({
                url: `${environment.authorization.host}:${environment.authorization.port}${url}`
            })
        }

        return next.handle(request).do(event => {

        }).catch(err => {
            if (err instanceof HttpErrorResponse) {
                this.handleError(err);
            }
            return Observable.throw(err);
        });
    }

    handleError(err: HttpErrorResponse) {
        console.log("Interceptor: " + err);

        if (err.status === 404) {
            this.snackBar.open('Server not found', 'OK', {
                duration: environment.constants.promptTimeout
            });
        } else if (err.status === 500) {
            this.snackBar.open('Something went wrong, please contact the administrator for more details', 'OK', {
                duration: environment.constants.promptTimeout
            });
        } else if (err.status === 400) {
            this.snackBar.open('Your request was bad', 'OK', {
                duration: environment.constants.promptTimeout
            });
        } else if(err.status === 401 || err.status === 0) {
            this.snackBar.open('You need to login before do this', 'OK', {
                duration: environment.constants.promptTimeout
            });
            this.router.navigate(['/']);
        }
        
        this.spinner.hide();
    }

    isLoggedIn(): boolean {
        const expiration_time = this.localStorageService.get('expiration_time');
        this.accessToken = this.localStorageService.get('access_token');

        if (this.accessToken && expiration_time && (Number(expiration_time) >= Date.now())) {
            return true;
        }

        return false;
    }
}