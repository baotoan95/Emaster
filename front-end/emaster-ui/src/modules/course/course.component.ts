import { Component, OnInit } from '@angular/core';
import { PortalService } from '../../shared/services/portal.service';
import { SpinnerService } from '../../shared/services/spinner.service';

@Component({
    selector: 'emaster-course',
    templateUrl: './course.component.html',
    styleUrls: ['./course.component.scss'],
    providers: [
        PortalService,
        SpinnerService
    ]
})
export class CourseComponent implements OnInit {
    categories: any;

    constructor(
        private portalSerivce: PortalService,
        private spinner: SpinnerService
    ) {

    }

    ngOnInit() {
        this.spinner.show();
        this.portalSerivce.category.getAll(0, 100).subscribe(data => {
            this.categories = data.content;
            console.log(this.categories);
            this.spinner.hide();
        }, error => {
            
        });
    }
}