import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-delete-training-dialog',
  templateUrl: './delete-training-dialog.component.html',
  styleUrls: ['./delete-training-dialog.component.css']
})
export class DeleteTrainingDialogComponent {

  constructor(public dialogRef: MatDialogRef<DeleteTrainingDialogComponent>) { }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

}
