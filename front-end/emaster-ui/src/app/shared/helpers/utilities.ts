import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import * as lodash from 'lodash';
import { LocalStorageService } from '../services/localStorage.service';

@Injectable()
export class Utilities {
    req: RequestHelper;
    _: any;
    commonHelper: CommonHelper;

    constructor(
        private _httpClient: HttpClient,
        private localStorageService: LocalStorageService
    ) {
        this.req = new RequestHelper(_httpClient, localStorageService);
        this._ = lodash;
    }
}

class RequestHelper {
    xcrfToken: string;

    constructor(private _httpClient: HttpClient, private localStorage: LocalStorageService) {

    }

    get(api: string, options?: any): Observable<any> {
        return this._httpClient.get(api, options);
    }

    post(api: string, data: any, headers?: any): Observable<any> {
        if (!headers) { headers = {} }
        headers['X-Csrf-Token'] = this.xcrfToken || '';
        return this._httpClient.post(api, data, { headers: new HttpHeaders(headers) });
    }

    put(api: string, data: any, headers?: any): Observable<any> {
        if (!headers) { headers = {} }
        headers['X-Csrf-Token'] = this.xcrfToken || '';
        return this._httpClient.put(api, data, { headers: new HttpHeaders(headers) });
    }

    delete(api: string, headers?: any): Observable<any> {
        if (!headers) { headers = {} }
        headers['X-Csrf-Token'] = this.xcrfToken || '';
        return this._httpClient.delete(api, { headers: new HttpHeaders(headers) });
    }
}

class CommonHelper {

}