import { Injectable } from '@angular/core';
import { Utilities } from '../helpers/utilities';
import { Observable } from 'rxjs/Observable';
import { Category } from '../models/Category';
import { Statement } from '../models/Statement';

@Injectable()
export class PortalService {
    category: CategoryService;
    statement: StatementService;
    question: GenerateQuestionService;
    user: UserService;

    constructor(
        private _u: Utilities
    ) {
        this.category = new CategoryService(_u);
        this.statement = new StatementService(_u);
        this.question = new GenerateQuestionService(_u);
        this.user = new UserService(_u);
    }
}

class CategoryService {
    constructor(private _u: Utilities) {

    }

    getAll(page: number, size: number): Observable<any> {
        return this._u.req.get('/portal/categories', {page: page, size: size});
    }

    create(category: Category): Observable<any> {
        let formData = new FormData();
        formData.append('iconFile', category.iconFile);
        formData.append('name', category.name);
        formData.append('description', category.description);
        formData.append('createdByEmail', category.createdBy);
        return this._u.req.post('/portal/categories', formData);
    }

    delete(id: string): Observable<any> {
        return this._u.req.delete(`/portal/categories/${id}`);
    }

    update(category: Category): Observable<any> {
        let formData = new FormData();
        formData.append('id', category.id);
        formData.append('iconFile', category.iconFile ? category.iconFile : new File([], ''));
        formData.append('name', category.name);
        formData.append('description', category.description);
        return this._u.req.post('/portal/categories/update', formData);
    }
}

class StatementService {
    constructor(private _u: Utilities) {

    }

    getAll(page: number, size: number): Observable<any> {
        return this._u.req.get('/portal/statements', {page: page, size: size});
    }

    create(statement: Statement): Observable<any> {
        let formData = new FormData();

        statement.createdBy = 'baotoan.95@gmail.com';
        formData.append('statement', new Blob([JSON.stringify(statement)], { type: "application/json"}));
        formData.append('normalSoundFile', statement.normalSoundFile);
        formData.append('slowSoundFile', statement.slowSoundFile);
        formData.append('imageFile', statement.imageFile);
        return this._u.req.post('/portal/statements', formData);
    }

    delete(id: string): Observable<any> {
        return this._u.req.delete(`/portal/statements/${id}`);
    }

    update(statement: Statement): Observable<any> {
        let formData = new FormData();

        statement.createdBy = 'baotoan.95@gmail.com';
        formData.append('statement', new Blob([JSON.stringify(statement)], { type: "application/json"}));
        formData.append('normalSoundFile', statement.normalSoundFile);
        formData.append('slowSoundFile', statement.slowSoundFile);
        formData.append('imageFile', statement.imageFile);
        return this._u.req.put('/portal/statements', formData);
    }

    getById(id: string): Observable<any> {
        return this._u.req.get(`/portal/statements/${id}`);
    }
}

class GenerateQuestionService {
    constructor(private _u: Utilities) {

    }

    generateByCategory(categoryId: string): Observable<any> {
        return this._u.req.get(`/portal/questionBank/generateByCategory?categoryId=${categoryId}`);
    }
}

class UserService {
    constructor(private _u: Utilities) {

    }

    getAll(page: number, size: number): Observable<any> {
        return this._u.req.get('/portal/users', {params: {page: page, size: size}});
    }
}