import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { MediaPlayerService } from '../../../../shared/services/mediaPlayer.service';
import { SpinnerService } from '../../../../shared/services/spinner.service';

@Component({
    selector: 'emaster-multiple-chooses',
    templateUrl: './multiple-chooses.component.html',
    styleUrls: ['./multiple-chooses.component.scss']
})
export class MultipleChoosesComponent implements OnInit{
    @Input() question;
    @Output() correct = new EventEmitter();

    selectedAnswer: any;

    constructor(
        private mediaPlayer: MediaPlayerService,
        private spinner: SpinnerService) {
        spinner.show();
    }

    ngOnInit() {
        this.spinner.hide();
    }

    setAnswer(selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}