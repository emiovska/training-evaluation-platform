import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { levelsApi } from "../../../api/levels/levels";
import { TrainingService } from "../../services/mock-training.service";
import { Training } from "../../models/training";
import { FormControl, Validators } from "@angular/forms";

@Component({
  selector: "app-add-new-training-dialog",
  templateUrl: "./add-new-training-dialog.component.html",
  styleUrls: ["./add-new-training-dialog.component.css"]
})
export class AddNewTrainingDialogComponent implements OnInit {
  training: Training;
  constructor(
    private dialogRef: MatDialogRef<AddNewTrainingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private trainingService: TrainingService
  ) {}

  levels = levelsApi;

  ngOnInit(): void {
    this.training = new Training();
  }

  name = new FormControl("", [Validators.required]);
  level = new FormControl("", [Validators.required]);

  getErrorMessage(paramName: string) {
    return this.name.hasError("required")
      ? `You must enter a ${paramName}`
      : "";
  }

  save() {
    console.log("Add new save btn", this.training);
    this.trainingService
      .createNewTraining(this.training)
      .subscribe(training => {
        this.dialogRef.close(training);
      });
  }

  dismiss() {
    this.dialogRef.close(null);
  }
}
