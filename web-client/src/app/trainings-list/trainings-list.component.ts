import { Component, OnInit } from '@angular/core';
import { Training } from '../models/training';
import { MatDialog, MatTableDataSource } from '@angular/material';
import { UpdateTrainingDialogComponent } from '../dialogs/update-training-dialog/update-training-dialog.component';
import { DeleteTrainingDialogComponent } from '../dialogs/delete-training-dialog/delete-training-dialog.component';
import { TrainingItemDialogComponent } from '../dialogs/training-item-dialog/training-item-dialog.component';
import { levelsApi } from '../../api/levels/levels';
import { AddNewTrainingDialogComponent } from '../dialogs/add-new-training-dialog/add-new-training-dialog.component';
import { TrainingService } from '../services/training.service';

@Component({
  selector: 'app-trainings-list',
  templateUrl: './trainings-list.component.html',
  styleUrls: ['./trainings-list.component.css']
})
export class TrainingsListComponent implements OnInit {

  dataSource: MatTableDataSource<Training>;
  levels;
  trainings: Training[];
  selectedTable: boolean;
  filterByName: string;
  filterByLevel: string;

  constructor(public dialog: MatDialog,
    private trainingService: TrainingService) { }

  ngOnInit() {
    this.reloadTrainings();
    this.levels = levelsApi;
    this.selectedTable = true;
    this.filterByName = '';
    this.filterByLevel = '';
  }

  displayedColumns = ['id', 'name', 'level', 'description', 'actions'];

  applyFilter(filterValue: string) {
    this.filterByLevel = '';
    filterValue = filterValue.trim(); // Remove whitespace
    this.trainingService.filterByName(filterValue).subscribe((filteredTrainings: Training[]) => {
      this.trainings = filteredTrainings;
      this.dataSource = new MatTableDataSource(filteredTrainings);
    });
  }

  selectedLevel(filterLevel: string) {
    this.filterByName = '';
    this.trainingService.filterByLevel(filterLevel).subscribe((filteredTrainings: Training[]) => {
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
    this.trainingService.getAllTrainings().subscribe(trainings => {
      this.dataSource = new MatTableDataSource(trainings);
      this.trainings = trainings;
    });
  }

  viewTrainingCard() {
    this.selectedTable = !this.selectedTable;
  }
}
