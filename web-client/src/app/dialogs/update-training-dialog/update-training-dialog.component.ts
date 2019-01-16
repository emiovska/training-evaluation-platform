import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { levelsApi } from "../../../api/levels/levels";
import { TrainingService } from "../../services/mock-training.service";
import { Training } from "../../models/training";

@Component({
  templateUrl: "./update-training-dialog.component.html",
  styleUrls: ["./update-training-dialog.component.css"]
})
export class UpdateTrainingDialogComponent {
  training: Training;

  constructor(
    public dialogRef: MatDialogRef<UpdateTrainingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private trainingService: TrainingService
  ) {}

  ngOnInit(): void {
    this.training = this.data;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  levels = levelsApi;

  save() {
    this.trainingService.updateTraining(this.training.id, this.training);
    console.log("Update training ", this.training);
    this.dialogRef.close(this.training);
  }

  dismiss() {
    this.dialogRef.close(null);
  }
}
