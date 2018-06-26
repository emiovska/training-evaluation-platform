import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { levelsApi } from '../../../api/levels/levels';

@Component({
  templateUrl: './update-training-dialog.component.html',
  styleUrls: ['./update-training-dialog.component.css']
})
export class UpdateTrainingDialogComponent {

  constructor(public dialogRef: MatDialogRef<UpdateTrainingDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  levels = levelsApi;
}
