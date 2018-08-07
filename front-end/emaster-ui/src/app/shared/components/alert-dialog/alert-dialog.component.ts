import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { DialogConfirmData } from "../../models/DialogConfirmData";
import { ConfirmDialogComponent } from "../confirm-dialog/confirm-dialog.component";

@Component({
    selector: 'emaster-alert-dialog',
    templateUrl: './alert-dialog.component.html',
    styleUrls: [
        './alert-dialog.component.scss'
    ]
})
export class AlertDialogComponent {
    constructor(public dialogRef: MatDialogRef<AlertDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ConfirmDialogComponent) {
        // Do nothing
    }

    ok() {
        this.dialogRef.close(true);
    }
}