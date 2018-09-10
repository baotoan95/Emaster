import { Component, Input, OnInit } from '@angular/core';
import { MediaPlayerService } from '../../../../shared/services/mediaPlayer.service';
import { SpinnerService } from '../../../../shared/services/spinner.service';

@Component({
    selector: 'emaster-multiple-chooses',
    templateUrl: './multiple-chooses.component.html',
    styleUrls: ['./multiple-chooses.component.scss']
})
export class MultipleChoosesComponent implements OnInit{
    @Input() question;
    selectedAnswer: any;

    constructor(
        private mediaPlayer: MediaPlayerService,
        private spinner: SpinnerService) {
        spinner.show();
    }

    ngOnInit() {
        console.log(this.question);
        this.spinner.hide();
    }

    setAnswer(selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        console.log(this.selectedAnswer);
    }
}