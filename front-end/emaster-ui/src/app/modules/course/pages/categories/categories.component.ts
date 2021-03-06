import { Component, OnInit } from '@angular/core';
import { PortalService } from '../../../../shared/services/portal.service';
import { SpinnerService } from '../../../../shared/services/spinner.service';
import { MatSnackBar } from '@angular/material';

@Component({
    selector: 'emaster-categories',
    templateUrl: './categories.component.html',
    styleUrls: ['./categories.component.scss'],
    providers: [
        PortalService,
        SpinnerService
    ]
})
export class CategoriesComponent implements OnInit {
    categories: any;

    constructor(
        private portalSerivce: PortalService,
        private spinner: SpinnerService,
        private snackBar: MatSnackBar
    ) {

    }

    ngOnInit() {
        this.spinner.show();
        this.portalSerivce.category.getAll(0, 100).subscribe(data => {
            this.categories = data.content;
            this.spinner.hide();
            this.snackBar.open('Already', 'OK', {
                duration: 6000
            });
        });
    }
}