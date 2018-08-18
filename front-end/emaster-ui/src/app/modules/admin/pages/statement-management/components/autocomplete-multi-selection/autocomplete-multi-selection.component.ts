import { Component, Input, Output, OnInit, ViewChild, EventEmitter, OnChanges, SimpleChanges } from "@angular/core";
import { Observable } from "rxjs";
import { FormControl } from "@angular/forms";
import {map, startWith} from 'rxjs/operators';
import { MatAutocomplete } from "@angular/material";

@Component({
    selector: 'emaster-autocomplete-multi-selection',
    templateUrl: './autocomplete-multi-selection.component.html',
    styleUrls: [
        './autocomplete-multi-selection.component.scss'
    ]
})
export class AutocompleteMultiSelectionComponent implements OnInit, OnChanges {
    filterInput = new FormControl();

    @Input('options') options: any[];
    @Output('change') change: EventEmitter<string[]> = new EventEmitter();

    @ViewChild("autoCompleteComponent") autoCompleteComponent: MatAutocomplete;

    filterOptions: Observable<any>;
    selectedItems: any[] = [];

    ngOnChanges(changes: SimpleChanges) {
        if(!changes.options.firstChange) {
            this.options = changes.options.currentValue;
            this.filterOptions = this.filterInput.valueChanges.pipe(
                startWith(''),
                map(value => this.filter(value))
            );
        }
    }

    ngOnInit() {
        this.autoCompleteComponent.optionSelected.subscribe(res => {
            this.selectedItems.push(res.option.value);
            console.log(this.selectedItems);
            this.change.emit(this.selectedItems);
            this.filterInput.setValue('');
        });
    }

    private filter(option: any) {
        if(option && (typeof option === 'string')) {
            const filterValue = option.toLowerCase();
            return this.options.filter(opt => opt.content.toLowerCase().includes(filterValue));
        } else {
            return this.options;
        }
    }

    removeSelectedItem(item: any) {
        this.selectedItems = this.selectedItems.filter(element => element.id !== item.id);
        console.log(this.selectedItems);
        this.change.emit(this.selectedItems);
    }
}