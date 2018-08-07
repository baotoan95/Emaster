import { Component, OnInit, ViewChild } from '@angular/core';
import { PortalService } from '../../../../../../shared/services/portal.service';
import { Category } from '../../../../../../shared/models/Category';
import { MatPaginator, MatTableDataSource, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { SpinnerService } from '../../../../../../shared/services/spinner.service';
import { CategoryDialogComponent } from '../category-dialog/category-dialog.component';
import { ConfirmDialogComponent } from '../../../../../../shared/components/confirm-dialog/confirm-dialog.component';

@Component({
    selector: 'emaster-category-list-component',
    templateUrl: './category-list.component.html',
    styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {
    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;

    pageSizeOptions: number[] = [10, 50, 100];

    categories: Category[];
    dataSource: MatTableDataSource<Category>;
    displayedColumns: string[] = ['icon', 'name', 'createdBy', 'default', 'forkCount', 'action'];

    CATEGORY_ACTION: any;

    constructor(private portalService: PortalService,
        private spinnerService: SpinnerService,
        public dialog: MatDialog,
        private snackBar: MatSnackBar) {
        this.CATEGORY_ACTION = CategoryDialogComponent.TYPE;
    }

    ngOnInit() {
        this.spinnerService.show();
        this.portalService.category.getAll(0, Number.MAX_SAFE_INTEGER).subscribe(data => {
            this.categories = data.content;
            this.setDataSource();
            this.spinnerService.hide();
        }, err => {
            this.spinnerService.hide();
            this.snackBar.open(err.error ? err.error.message : err, 'OK', {
                duration: 6000
            });
        });
    }

    openDialog(type, category?: Category): void {
        if (CategoryDialogComponent.TYPE.CREATE === type) {
            category = new Category();
        }
        const dialogRef = this.dialog.open(CategoryDialogComponent, {
            data: {
                category: category,
                title: `${type === CategoryDialogComponent.TYPE.CREATE ? 'Create new category' : 'Update category'}`
            }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                if (CategoryDialogComponent.TYPE.CREATE === type) {
                    this.create(result);
                } else if (CategoryDialogComponent.TYPE.UPDATE === type) {
                    this.update(result);
                }
            }
        });
    }

    setDataSource() {
        this.dataSource = new MatTableDataSource(this.categories);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();
    }

    private update(data: Category) {
        this.spinnerService.show();

        this.portalService.category.update(data).subscribe(result => {
            const itemIndex = this.categories.findIndex(cate => cate.id === data.id);
            if (itemIndex !== -1) {
                this.categories[itemIndex] = result;
                this.setDataSource();
            }
            this.spinnerService.hide();
        }, err => {
            this.spinnerService.hide();
            this.snackBar.open(err.error ? err.error.message : err, 'OK', {
                duration: 6000
            });
        });
    }

    private create(data: any) {
        this.spinnerService.show();
        let category = new Category();
        category.createdBy = 'baotoan.95@gmail.com';
        category.name = data.name;
        category.description = data.description;
        category.iconFile = data.iconFile;

        this.portalService.category.create(category).subscribe(result => {
            this.categories.unshift(result);
            this.setDataSource();
            this.spinnerService.hide();
        }, err => {
            this.spinnerService.hide();
            this.snackBar.open(err.error ? err.error.message : err, 'OK', {
                duration: 6000
            });
        });
    }

    delete(category: Category) {
        const confirmDialog = this.dialog.open(ConfirmDialogComponent, {
            data: {
                title: 'Confirmation',
                message: 'Are you sure you want to delete?'
            }
        });
        confirmDialog.afterClosed().subscribe(result => {
            if(result) {
                this.spinnerService.show();
                this.portalService.category.delete(category.id).subscribe(result => {
                    this.categories = this.categories.filter(cate => cate.id != category.id);
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