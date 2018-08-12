import { Injectable } from "@angular/core";
import { Utilities } from "../helpers/utilities";
import { Observable } from "rxjs";
import { RequestOptions, Headers } from "@angular/http";
import { HttpHeaders, HttpParams } from "@angular/common/http";

@Injectable()
export class AuthoriztionSevice {
    constructor(private _u: Utilities) {
        
    }

    login(email: string, password: string): Observable<any> {
        let data = new URLSearchParams();
        data.set('username', email);
        data.set('password', password);
        data.set('grant_type', 'password');
        const headers = {
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Basic ' + btoa("emaster-client:emaster-secret")
        };
        return this._u.req.post('/authorization/oauth/token', data.toString(), headers);
    }
}