import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { levelsApi } from '../../../api/levels/levels';
import { TrainingService } from '../../services/training.service';

@Component({
  templateUrl: './update-training-dialog.component.html',
  styleUrls: ['./update-training-dialog.component.css']
})
export class UpdateTrainingDialogComponent {

  constructor(public dialogRef: MatDialogRef<UpdateTrainingDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
            private trainingService: TrainingService) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  levels = levelsApi;

  save() {
    this.trainingService.updateTraining(this.data).subscribe(training => {
      this.dialogRef.close(training);
    });
  }

  dismiss() {
    this.dialogRef.close(null);
  }
}
