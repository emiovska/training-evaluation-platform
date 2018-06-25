import { Component, OnInit } from '@angular/core';
import { Training } from '../models/training';
import { MatDialog } from '@angular/material';
import { TrainingDialogComponent } from '../dialogs/training-dialog/training-dialog.component';
import { DeleteTrainingDialogComponent } from '../dialogs/delete-training-dialog/delete-training-dialog.component';
import { TrainingItemDialogComponent } from '../dialogs/training-item-dialog/training-item-dialog.component';
import { trainings } from '../../api/trainings/trainings';
import { levelsApi } from '../../api/levels/levels';

@Component({
  selector: 'app-trainings-list',
  templateUrl: './trainings-list.component.html',
  styleUrls: ['./trainings-list.component.css']
})
export class TrainingsListComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  displayedColumns = ['id', 'name', 'level', 'description', 'actions'];
  dataSource = trainings;

  levels = levelsApi;

  createNewItem(): void {
    let dialogRef = this.dialog.open(TrainingDialogComponent, {
      width: '600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  previewDetailsDialog(training: Training): void {
    console.log("Training: ", training);
    console.log("Training name: ", training.name)
    let dialogRef = this.dialog.open(TrainingItemDialogComponent, {
      width: '250px',
      data: { name: training.name, level: training.level, description: training.description }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteDialog(): void {
    let dialogRef = this.dialog.open(DeleteTrainingDialogComponent, {
      width: '250px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
