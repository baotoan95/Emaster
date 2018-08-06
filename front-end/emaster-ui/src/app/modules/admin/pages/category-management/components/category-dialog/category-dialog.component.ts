import { Component, Inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Category } from '../../../../../../shared/models/Category';
import { FormControl, FormGroup, Validators } from '@angular/forms';

enum DialogAction {
    CREATE = 'create',
    UPDATE = 'update'
}

@Component({
    selector: 'emaster-category-component',
    templateUrl: './category-dialog.component.html',
    styleUrls: ['./category-dialog.component.scss']
})
export class CategoryDialogComponent implements OnInit {
    static TYPE = DialogAction;
    categoryForm: FormGroup;
    iconFile: any;

    constructor(public dialogRef: MatDialogRef<CategoryDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public category: Category,
        private cd: ChangeDetectorRef) {
    }

    ngOnInit() {
        this.categoryForm = new FormGroup({
            'name': new FormControl(this.category.name, [
                Validators.required,
                Validators.minLength(5)
            ]),
            'description': new FormControl(this.category.description, []),
            'id': new FormControl(this.category.id),
            'iconFile': new FormControl()
        });
    }

    onFileChange(event) {
        const reader = new FileReader();

        if (event.target.files && event.target.files.length) {
            reader.readAsArrayBuffer(event.target.files[0]);
            reader.onload = () => {
                this.iconFile = event.target.files[0];
            };
        }
    }

    get name() {
        return this.categoryForm.get('name');
    }

    get description() {
        return this.categoryForm.get('description');
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    ok(): void {
        this.dialogRef.close({
            'name': this.categoryForm.get('name').value,
            'id': this.categoryForm.get('id').value,
            'iconFile': this.iconFile,
            'description': this.categoryForm.get('description').value
        });
    }
}