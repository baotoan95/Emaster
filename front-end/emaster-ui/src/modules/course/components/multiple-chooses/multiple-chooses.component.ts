import { Component } from '@angular/core';

@Component({
    selector: 'emaster-multiple-chooses',
    templateUrl: './multiple-chooses.component.html',
    styleUrls: ['./multiple-chooses.component.scss']
})
export class MultipleChoosesComponent {
    answers: any[] = [
        {
            content: 'The doctor had assumed that was her car.'
        },
        {
            content: 'The lawyer had finished that was her car.'
        },
        {
            content: 'The lawyer had assumed that was her car.'
        },
        {
            content: 'The doctor had assumed that was her car.'
        },
        {
            content: 'The lawyer had finished that was her car. The lawyer had finished that was her car.'
        },
        {
            content: 'The lawyer had assumed that was her car.'
        }
    ]
}