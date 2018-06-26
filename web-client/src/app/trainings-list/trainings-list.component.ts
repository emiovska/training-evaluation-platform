import { Component, OnInit } from '@angular/core';
import { Training } from '../models/training';
import { MatDialog } from '@angular/material';
import { UpdateTrainingDialogComponent } from '../dialogs/update-training-dialog/update-training-dialog.component';
import { DeleteTrainingDialogComponent } from '../dialogs/delete-training-dialog/delete-training-dialog.component';
import { TrainingItemDialogComponent } from '../dialogs/training-item-dialog/training-item-dialog.component';
import { trainings } from '../../api/trainings/trainings';
import { levelsApi } from '../../api/levels/levels';
import { AddNewTrainingDialogComponent } from '../dialogs/add-new-training-dialog/add-new-training-dialog.component';

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

  previewDetailsDialog(training: Training): void {
    const { name, level, description } = training;
    this.dialog.open(TrainingItemDialogComponent, {
      width: '400px',
      data: { name, level, description }
    });
  }

  createNewItem(): void {
    let dialogRef = this.dialog.open(AddNewTrainingDialogComponent, {
      width: '600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  editDialog(training: Training): void {
    const { name, level, description } = training;
    this.dialog.open(UpdateTrainingDialogComponent, {
      width: '600px',
      data: { name, level, description }
    });
  }

  deleteDialog(): void {
    this.dialog.open(DeleteTrainingDialogComponent, {
      width: '250px'
    });
  }
}
