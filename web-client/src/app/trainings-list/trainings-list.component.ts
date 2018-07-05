import { Component, OnInit } from '@angular/core';
import { Training } from '../models/training';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { UpdateTrainingDialogComponent } from '../dialogs/update-training-dialog/update-training-dialog.component';
import { DeleteTrainingDialogComponent } from '../dialogs/delete-training-dialog/delete-training-dialog.component';
import { TrainingItemDialogComponent } from '../dialogs/training-item-dialog/training-item-dialog.component';
import { levelsApi } from '../../api/levels/levels';
import { AddNewTrainingDialogComponent } from '../dialogs/add-new-training-dialog/add-new-training-dialog.component';
import { TrainingService } from '../services/training.service';
import { Observable } from 'rxjs';

const ALL_LEVELS: string = 'All levels';

@Component({
  selector: 'app-trainings-list',
  templateUrl: './trainings-list.component.html',
  styleUrls: ['./trainings-list.component.css']
})
export class TrainingsListComponent implements OnInit {
  dataSource: MatTableDataSource<Training>;
  levels: string[];
  trainings: Training[];
  selectedTable: boolean;
  nameFilter: string;
  levelFilter: string;
  viewTitle: string;

  constructor(public dialog: MatDialog,
    private trainingService: TrainingService) { }

  ngOnInit() {
    this.reloadTrainings();
    this.levels = [ALL_LEVELS].concat(levelsApi);
    this.selectedTable = true;
    this.nameFilter = '';
    this.levelFilter = ALL_LEVELS;
    this.viewTitle = 'View Training cards';
  }

  displayedColumns = ['id', 'name', 'level', 'description', 'actions'];

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace

    this.filterTrainings().subscribe((filteredTrainings: Training[]) => {
      this.trainings = filteredTrainings;
      this.dataSource = new MatTableDataSource(filteredTrainings);
    });
  }

  selectedLevel(filterLevel: string) {
    this.levelFilter = filterLevel;

    this.filterTrainings().subscribe((filteredTrainings: Training[]) => {
      this.trainings = filteredTrainings;
      this.dataSource = new MatTableDataSource(filteredTrainings);
    });
  }

  previewDetailsDialog(training: Training): void {
    const { name, level, description } = training;
    this.dialog.open(TrainingItemDialogComponent, {
      width: '400px',
      data: { name, level, description }
    });
  }

  createNewItem(): void {
    let dialogRef = this.dialog.open(AddNewTrainingDialogComponent, {
      width: '510px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) this.reloadTrainings();
    });
  }

  editDialog(training: Training): void {
    const { id, name, level, description } = training;
    let dialogRef = this.dialog.open(UpdateTrainingDialogComponent, {
      width: '600px',
      data: { id, name, level, description }
    });
    dialogRef.afterClosed().subscribe(() => this.reloadTrainings());
  }

  deleteDialog(id: number): void {
    let dialogRef = this.dialog.open(DeleteTrainingDialogComponent, {
      width: '250px',
      data: { id }
    });
    dialogRef.afterClosed().subscribe(() => this.reloadTrainings());
  }

  reloadTrainings(): void {
    this.filterTrainings().subscribe((trainings: Training[]) => {
      this.dataSource = new MatTableDataSource(trainings);
      this.trainings = trainings;
    });
  }

  viewTrainingCard() {
    this.selectedTable = !this.selectedTable;
    this.selectedTable? this.viewTitle = 'Card view of training records': this.viewTitle='Table view of training records'
  }

  filterTrainings() {
    return this.levelFilter == ALL_LEVELS ?
      this.filterByName(this.nameFilter) :
      this.levelFilter && this.nameFilter ?
        this.trainingService.filterByNameAndLevel(this.nameFilter, this.levelFilter) :
        this.filterByLevel(this.levelFilter);
  }

  private filterByLevel(filterValue: string): Observable<Training[]> {
    return (!filterValue) ? this.trainingService.getAllTrainings() : this.trainingService.filterByLevel(filterValue);
  }

  private filterByName(filterValue: string) {
    return (!filterValue) ? this.trainingService.getAllTrainings() : this.trainingService.filterByName(filterValue);
  }

}
