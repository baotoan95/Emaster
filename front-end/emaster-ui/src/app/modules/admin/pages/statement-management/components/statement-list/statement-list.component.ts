import { Component, ViewChild } from "@angular/core";
import { MatPaginator, MatSort, MatTableDataSource, MatDialog, MatSnackBar } from "@angular/material";
import { PortalService } from "../../../../../../shared/services/portal.service";
import { SpinnerService } from "../../../../../../shared/services/spinner.service";
import { Statement } from "../../../../../../shared/models/Statement";
import { ConfirmDialogComponent } from "../../../../../../shared/components/confirm-dialog/confirm-dialog.component";

@Component({
    selector: 'emaster-statement-list',
    templateUrl: './statement-list.component.html',
    styleUrls: [
        './statement-list.component.scss'
    ]
})
export class StatementListComponent {
    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;

    pageSizeOptions: number[] = [10, 50, 100];

    statements: Statement[];
    dataSource: MatTableDataSource<Statement>;
    displayedColumns: string[] = ['content', 'language', 'type', 'createdDate', 'createdBy', 'action'];

    constructor(private portalService: PortalService,
        private spinnerService: SpinnerService,
        public dialog: MatDialog,
        private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.spinnerService.show();
        this.portalService.statement.getAll(0, Number.MAX_SAFE_INTEGER).subscribe(data => {
            this.statements = data.content;
            this.setDataSource();
            this.spinnerService.hide();
        }, err => {
            this.spinnerService.hide();
            this.snackBar.open(err.error ? err.error.message : err, 'OK', {
                duration: 6000
            });
        });
    }

    private setDataSource() {
        this.dataSource = new MatTableDataSource(this.statements);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();
    }

    delete(statement: Statement) {
        const confirmDialog = this.dialog.open(ConfirmDialogComponent, {
            data: {
                title: 'Confirmation',
                message: 'Are you sure you want to delete?'
            }
        });
        confirmDialog.afterClosed().subscribe(result => {
            if(result) {
                this.spinnerService.show();
                this.portalService.statement.delete(statement.id).subscribe(result => {
                    this.statements = this.statements.filter(cate => cate.id != statement.id);
                    this.setDataSource();
                    this.spinnerService.hide();
                }, err => {
                    this.spinnerService.hide();
                    this.snackBar.open(err.error ? err.error.message : err, 'OK', {
                        duration: 6000
                    });
                });
            }
        });
    }
}