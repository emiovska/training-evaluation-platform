import { Component, OnInit } from '@angular/core';
import { TrainingRequest } from '../models/training-request';
import { TrainingRequestService } from '../services/training-request.service';
import { DialogWidth } from '../interfaces/dialog-width';
import { MatDialog } from '@angular/material';
import { CheckProgressOfTrainingDialogComponent } from '../dialogs/check-progreess-of-training-dialog/check-progreess-of-training-dialog.component';
import { TrainingItemDialogComponent } from '../dialogs/training-item-dialog/training-item-dialog.component';

@Component({
  selector: 'app-approved-training-requests',
  templateUrl: './approved-training-requests.component.html',
  styleUrls: ['./approved-training-requests.component.css']
})
export class ApprovedTrainingRequestsComponent implements OnInit {

  trainingRequests: TrainingRequest[];
  approveIcon: string;
  approveTitle: string;
  constructor(public dialog: MatDialog,
              private trainingRequestSevice: TrainingRequestService) { }

  ngOnInit() {
    this.trainingRequests = this.trainingRequestSevice.getTrainingRequestsByUser();
    this.approveIcon = "check_circle";
    this.approveTitle = "Approved Training requests by trainer";
  }

  previewDetailsDialog(trainingRequest: TrainingRequest): void {
    const { name, level, description } = trainingRequest.training;
    this.dialog.open(TrainingItemDialogComponent, {
      width: DialogWidth.previewDialog,
      data: { name, level, description }
    });
  }

  giveProgressOfTraining(trainingRequest: TrainingRequest): void {
    let dialogRef = this.dialog.open(CheckProgressOfTrainingDialogComponent, {
      width: DialogWidth.checkProgressDialog,
      data: { trainingRequest }
    });
  }

}
