import { Component, OnInit, AfterViewInit, HostListener } from "@angular/core";
import { ScrollToService } from 'ng2-scroll-to-el';
import {
    trigger,
    state,
    style,
    animate,
    transition
} from '@angular/animations';
import { TranslateService } from "@ngx-translate/core";
import { SupportedLanguages } from "../../shared/models/localization.model";
import { LocalStorageService } from "../../shared/services/localStorage.service";
declare var $: any;

@Component({
    selector: 'emaster-landing',
    templateUrl: './landing.component.html',
    styleUrls: ['./landing.component.scss'],
    providers: [
        ScrollToService,
        TranslateService
    ],
    animations: [
        trigger('bestLearnAnimation', [
            state('inactive', style({
                opacity: 0,
                transform: "translateX(-100%)"
            })),
            state('active', style({
                opacity: 1,
                transform: "translateX(0)"
            })),
            transition('inactive => active', animate('700ms ease-in')),
            transition('active => inactive', animate('700ms ease-out'))
        ]),
        trigger('textGuideAnimation', [
            state('inactive', style({
                opacity: 0,
                transform: "translateX(100%)"
            })),
            state('active', style({
                opacity: 1,
                transform: "translateX(0)"
            })),
            transition('inactive => active', animate('700ms ease-out')),
            transition('active => inactive', animate('700ms ease-in'))
        ])
    ]
})
export class LandingComponent implements OnInit, AfterViewInit {
    SECTIONS = SECTIONS;
    currentSection = SECTIONS.WELCOME;
    sectionMeta: any = {};
    guideState: string = 'inactive';
    aboutUs: any[] = [
        { icon: 'home', title: 'EIUSMOD TEMPOR', display: 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi' },
        { icon: 'home', title: 'EIUSMOD TEMPOR', display: 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi' },
        { icon: 'home', title: 'EIUSMOD TEMPOR', display: 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi' },
        { icon: 'home', title: 'EIUSMOD TEMPOR', display: 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi' },
    ]
    supportedLanguages = SupportedLanguages;
    selectedLanguage: any;

    constructor(
        private scrollService: ScrollToService,
        private translateService: TranslateService,
        private localStorageService: LocalStorageService) {

    }

    @HostListener("window:scroll", [])
    onWindowScroll() {
        const currentPosition = $(window).scrollTop();
        this.markCurrentSection(currentPosition);

        if(this.currentSection === SECTIONS.GUIDE) {
            this.guideState = 'active';
        } else {
            this.guideState = 'inactive';
        }
    }

    private markCurrentSection(currentPosition: any) {
        Object.keys(SECTIONS).forEach((key) => {
            const section = $('#' + SECTIONS[key]);
            if (section.offset().top <= currentPosition
            && (section.offset().top + section.height()) > currentPosition) {
                this.currentSection = SECTIONS[key];
            }
        });
    }

    ngOnInit() {
        this.selectedLanguage = this.supportedLanguages.find(lang => lang.code === this.localStorageService.get('selectedLanguage'));
    }

    ngAfterViewInit() {
        setTimeout(() => {
            this.scrollService.scrollTo('#' + this.currentSection);
        }, 500);
    }

    scrollTo(section: SECTIONS) {
        this.scrollService.scrollTo('#' + section).subscribe(e => {
            setTimeout(() => {
                this.currentSection = section;
            }, 50);
        });
    }

    switchLanguage(language: any) {
        this.selectedLanguage = language;
        this.translateService.use(this.selectedLanguage.code);
        this.localStorageService.set('selectedLanguage', this.selectedLanguage.code);
    }
}

export enum SECTIONS {
    WELCOME = "welcome",
    GUIDE = "guide",
    ABOUT = "about",
    CONTACT = "contact"
}