import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { levelsApi } from '../../../api/levels/levels';

@Component({
  selector: 'training-dialog',
  templateUrl: './training-dialog.component.html',
  styleUrls: ['./training-dialog.component.css']
})
export class TrainingDialogComponent {

  constructor(public dialogRef: MatDialogRef<TrainingDialogComponent>) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  levels = levelsApi;
}
