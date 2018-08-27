import { Component, OnInit, OnDestroy } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Statement } from "../../../../../../shared/models/Statement";
import { Category } from "../../../../../../shared/models/Category";
import { PortalService } from "../../../../../../shared/services/portal.service";
import { SpinnerService } from "../../../../../../shared/services/spinner.service";
import { MatDialog, MatSnackBar } from "@angular/material";
import { Router, ActivatedRoute } from "@angular/router";
import { AlertDialogComponent } from "../../../../../../shared/components/alert-dialog/alert-dialog.component";
import { FileType } from "../../../../../../shared/enums/FileType";
import { Location } from '@angular/common';

import * as _ from 'lodash';

@Component({
    selector: 'emaster-statement',
    templateUrl: './statement.component.html',
    styleUrls: [
        './statement.component.scss'
    ],
    providers: [
        Location
    ]
})
export class StatementComponent implements OnInit, OnDestroy {
    languages: any[] = [
        {
            displayName: 'Việt Nam',
            value: 'VIETNAMESE'
        },
        {
            displayName: 'Tiếng Anh',
            value: 'ENGLISH'
        }
    ];

    questionTypes: any[] = [
        {
            displayName: 'Wrong',
            value: 'WRONG'
        },
        {
            displayName: 'Text',
            value: 'TEXT'
        },
        {
            displayName: 'GAP',
            value: 'GAP'
        }
    ];

    FileType = FileType;

    categories: Category[];
    statements: any[];
    statement: Statement;
    statementForm: FormGroup;

    correctAnwers: any[] = [];
    incorrectAnwers: any[] = [];

    audio: any;

    constructor(private portalService: PortalService,
        private spinnerService: SpinnerService,
        public dialog: MatDialog,
        private snackBar: MatSnackBar,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private location: Location) {

        this.audio = new Audio();
    }

    ngOnInit() {
        this.statement = new Statement();
        this.initData();

        if (this.activatedRoute.snapshot.params['{action}'] === 'update' && this.activatedRoute.snapshot.params['{id}']) {
            this.portalService.statement.getById(this.activatedRoute.snapshot.params['{id}']).subscribe(res => {
                this.statement = res;
                this.correctAnwers = this.statement.correctAnswers;
                this.incorrectAnwers = this.statement.incorrectAnswers;
                this.initData();
            }, err => {
                this.statement = new Statement();
                this.initData();
            });
        }
    }

    initData() {
        this.statement.language = this.languages[0].value;
        this.statement.type = this.questionTypes[0].value;

        this.initForm();
        this.spinnerService.show();
        this.portalService.statement.getAll(0, Number.MAX_SAFE_INTEGER).subscribe(data => {
            this.statements = data.content;
        }, error => {
            this.snackBar.open('Can\'t get statements', 'OK', {
                duration: 6000
            });
            this.spinnerService.hide();
        });
        this.loadCategories();
    }

    private loadCategories() {
        this.portalService.category.getAll(0, Number.MAX_SAFE_INTEGER).subscribe(data => {
            this.categories = data.content;
            if (this.categories.length <= 0) {
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
            'categoryId': new FormControl(this.statement.categoryId),
            'image': new FormControl(),
            'normalSound': new FormControl(),
            'slowSound': new FormControl()
        });
    }

    onFileChange(event, fileType: FileType) {
        const reader = new FileReader();

        if (event.target.files && event.target.files.length) {
            reader.readAsArrayBuffer(event.target.files[0]);
            reader.onload = () => {
                switch (fileType) {
                    case FileType.STATEMENT_IMAGE:
                        this.statement.imageFile = event.target.files[0];
                        break;
                    case FileType.NORMAL_SOUND:
                        this.statement.normalSoundFile = event.target.files[0];
                        break;
                    case FileType.SLOW_SOUND:
                        this.statement.slowSoundFile = event.target.files[0];
                        break;
                }
            };
        }
    }

    save() {
        this.statement.categoryId = this.statementForm.get('categoryId').value;
        this.statement.language = this.statementForm.get('originLang').value;
        this.statement.type = this.statementForm.get('type').value;
        this.statement.content = this.statementForm.get('content').value;
        this.statement.correctAnswers = this.correctAnwers;
        this.statement.incorrectAnswers = this.incorrectAnwers;
        this.statement.explaination = this.statementForm.get('explaination').value;

        // Update
        if (this.statement.id) {
            this.portalService.statement.update(this.statement).subscribe(res => {
                this.router.navigate(['/admin/statement-management']);
            }, resErr => {
                this.snackBar.open(resErr.error.message, 'OK', {
                    duration: 6000
                });
            });
        } else {
            // Create
            this.portalService.statement.create(this.statement).subscribe(res => {
                this.router.navigate(['/admin/statement-management']);
            }, resErr => {
                this.snackBar.open(resErr.error.message, 'OK', {
                    duration: 6000
                });
            });
        }
    }

    playSound(resourceUrl: string) {
        this.audio.src = `http://localhost:8080/portal/resources/audio?url=${resourceUrl}`;
        this.audio.play();
    }

    ngOnDestroy() {
        this.audio.pause();
    }
}