import { Injectable } from '@angular/core';
import { Utilities } from '../helpers/utilities';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PortalService {
    category: CategoryService;

    constructor(
        private _u: Utilities
    ) {
        this.category = new CategoryService(_u);
    }
}

class CategoryService {
    constructor(private _u: Utilities) {

    }

    getAll(page: number, size: number): Observable<any> {
        return this._u.req.get('http://localhost:8080/categories', {page: page, size: size});
    }
}