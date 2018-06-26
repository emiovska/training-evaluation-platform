import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { levelsApi } from '../../../api/levels/levels';

@Component({
  selector: 'app-add-new-training-dialog',
  templateUrl: './add-new-training-dialog.component.html',
  styleUrls: ['./add-new-training-dialog.component.css']
})
export class AddNewTrainingDialogComponent {

  constructor(public dialogRef: MatDialogRef<AddNewTrainingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  levels = levelsApi;
}
