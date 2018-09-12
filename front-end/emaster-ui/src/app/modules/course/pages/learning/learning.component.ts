import { Component, OnInit } from '@angular/core';
import { PortalService } from '../../../../shared/services/portal.service';
import { SpinnerService } from '../../../../shared/services/spinner.service';
import { MatSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { MediaPlayerService } from '../../../../shared/services/mediaPlayer.service';

@Component({
    selector: 'emaster-learning',
    templateUrl: './learning.component.html',
    styleUrls: [ './learning.component.scss' ],
    providers: [
        SpinnerService
    ]
})
export class LearningComponent implements OnInit {
    categoryId: string;
    questions: any[];

    constructor(
        private questionGenerateService: PortalService,
        private spinner: SpinnerService,
        private snackBar: MatSnackBar,
        private activatedRoute: ActivatedRoute,
        private mediaPlayer: MediaPlayerService) {

    }

    ngOnInit() {
        if(this.activatedRoute.snapshot.params['categoryId']) {
            this.categoryId = this.activatedRoute.snapshot.params['categoryId'];
        }

        this.spinner.show();
        this.questionGenerateService.question.generateByCategory(this.categoryId).subscribe(data => {
            this.questions = data.sort(() => Math.random() - 0.5);
            console.log(this.questions);
            this.spinner.hide();
        }, err => {
            this.spinner.hide();
            this.snackBar.open(err, 'OK', {
                duration: 6000
            });
        });
    }

    checkAnswer() {
        this.mediaPlayer.playCorrectSound();
    }
}