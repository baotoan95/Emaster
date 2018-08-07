import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { DialogConfirmData } from "../../models/DialogConfirmData";

@Component({
    selector: 'emaster-confirm-dialog',
    templateUrl: './confirm-dialog.component.html',
    styleUrls: [
        './confirm-dialog.component.scss'
    ]
})
export class ConfirmDialogComponent {
    constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogConfirmData) {
        // Do nothing
    }

    cancel() {
        this.dialogRef.close(false);
    }

    ok() {
        this.dialogRef.close(true);
    }
}