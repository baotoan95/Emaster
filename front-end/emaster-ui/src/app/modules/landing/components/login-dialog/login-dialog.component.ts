import { Component, OnInit } from "@angular/core";
import { MatDialogRef } from "@angular/material";
import { Router } from "@angular/router";
import { FormGroup, Validators, FormControl } from "@angular/forms";
import { Credentials } from "../../../../shared/models/Credentials";
import { AuthoriztionSevice } from "../../../../shared/services/authorization.service";
import { SpinnerService } from "../../../../shared/services/spinner.service";
import { LocalStorageService } from "../../../../shared/services/localStorage.service";

@Component({
    selector: 'emaster-login-dialog',
    templateUrl: 'login-dialog.component.html',
    styleUrls: [
        './login-dialog.component.scss'
    ]
})
export class LoginDialogComponent implements OnInit {
    loginForm: FormGroup;
    credentials: Credentials;
    hidePass: boolean = true;
    errorMsg: string;

    constructor(public dialogRef: MatDialogRef<LoginDialogComponent>,
        private router: Router,
        private authSevice: AuthoriztionSevice,
        private spinnerService: SpinnerService,
        private localStorage: LocalStorageService) {

    }

    ngOnInit() {
        this.credentials = {
            email: '',
            password: ''
        }
        this.loginForm = new FormGroup({
            'email': new FormControl(this.credentials.email, Validators.required),
            'password': new FormControl(this.credentials.password, Validators.required)
        })
    }

    login() {
        this.spinnerService.show();
        const email = this.loginForm.get('email').value;
        const password = this.loginForm.get('password').value;
        this.authSevice.login(email, password).subscribe(res => {
            // Save to localStorage
            const expration_time = Date.now() + (res.expires_in * 1000);
            this.localStorage.set('access_token', res.access_token);
            this.localStorage.set('expiration_time', expration_time);
            this.localStorage.set('refresh_token', res.resfresh_token);
            this.localStorage.set('scope', res.scope);

            console.log('=============');
            console.log('Access_token', res.access_token);
            console.log('Exparation_time', expration_time);
            console.log('Refresh_token', res.refresh_token);
            console.log('Scope', res.scope);
            console.log('=============');

            this.router.navigate(['courses']);

            this.spinnerService.hide();
            this.closeDialog();
        }, err => {
            this.errorMsg = 'Email or Password is invalid';
            this.spinnerService.hide();
        });
    }

    closeDialog() {
        this.dialogRef.close();
    }
}