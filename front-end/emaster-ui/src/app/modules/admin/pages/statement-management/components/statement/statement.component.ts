import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Statement } from "../../../../../../shared/models/Statement";
import { Category } from "../../../../../../shared/models/Category";
import { PortalService } from "../../../../../../shared/services/portal.service";
import { SpinnerService } from "../../../../../../shared/services/spinner.service";
import { MatDialog, MatSnackBar } from "@angular/material";
import { Router } from "@angular/router";
import { AlertDialogComponent } from "../../../../../../shared/components/alert-dialog/alert-dialog.component";
import * as _ from 'lodash';

@Component({
    selector: 'emaster-statement',
    templateUrl: './statement.component.html',
    styleUrls: [
        './statement.component.scss'
    ]
})
export class StatementComponent implements OnInit {
    languages: any[] = [
        {
            displayName: 'Việt Nam',
            value: 'VIETNAMESE'
        },
        {
            displayName: 'Tiếng Anh',
            value: 'ENGLISH'
        }
    ]

    questionTypes: any[] = [
        {
            displayName: 'Form',
            value: 'FORM'
        },
        {
            displayName: 'Translation',
            value: 'TRANSLATE'
        },
        {
            displayName: 'Listen',
            value: 'LISTEN'
        }
    ]

    categories: Category[];
    statement: Statement;
    statementForm: FormGroup;

    constructor(private portalService: PortalService,
        private spinnerService: SpinnerService,
        public dialog: MatDialog,
        private snackBar: MatSnackBar,
        private router: Router) {
        
    }

    ngOnInit() {
        this.statement = new Statement();
        this.statement.language = this.languages[0].value;
        this.statement.type = this.questionTypes[0].value;

        this.initForm();
        this.loadCategories();
    }

    private loadCategories() {
        this.spinnerService.show();
        this.portalService.category.getAll(0, Number.MAX_SAFE_INTEGER).subscribe(data => {
            this.categories = data.content;
            if(this.categories.length <= 0) {
                this.spinnerService.hide();
                this.dialog.open(AlertDialogComponent, {
                    data: {
                        title: 'No category has been found!',
                        message: 'Please create a new one before create statement'
                    }
                }).afterClosed().subscribe(result => {
                    this.router.navigate(['admin/category-management']);
                });
            }
            this.statement.categoryId = this.categories.length > 0 ? this.categories[0].id : '';
            this.statementForm.setControl('categoryId', new FormControl(this.statement.categoryId));
            this.spinnerService.hide();
        }, err => {
            this.snackBar.open('Can\'t get categories', 'OK', {
                duration: 6000
            });
            this.spinnerService.hide();
        });
    }

    private initForm() {
        this.statementForm = new FormGroup({
            'originLang': new FormControl(this.statement.language, Validators.required),
            'content': new FormControl(this.statement.content, Validators.required),
            'explaination': new FormControl(this.statement.explaination),
            'type': new FormControl(this.statement.type, Validators.required),
            'categoryId': new FormControl(this.statement.categoryId)
        });
    }

}