import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { TrainingRequest } from '../models/training-request';
import { TrainingRequestService } from '../services/training-request.service';
import { DialogWidth } from '../interfaces/dialog-width';
import { TrainingItemDialogComponent } from '../dialogs/training-item-dialog/training-item-dialog.component';

namespace STATUS {
  export const APPROVED = "APPROVED";
  export const CANCELED = "CANCELED"
}

@Component({
  selector: 'app-training-requests-list',
  templateUrl: './training-requests-list.component.html',
  styleUrls: ['./training-requests-list.component.css']
})
export class TrainingRequestsListComponent implements OnInit {

  dataSource: MatTableDataSource<TrainingRequest>;

  constructor(public dialog: MatDialog,
    private trainingRequestService: TrainingRequestService) { }

  displayedColumns = ['id', 'user', 'training', 'details', 'actions'];
  requestIcon: string;
  requestTitle: string;


  ngOnInit() {
    const trainingRequests = this.trainingRequestService.getAllTrainingRequestByUser();
    this.dataSource = new MatTableDataSource(trainingRequests);
    this.requestIcon = 'more_horiz';
    this.requestTitle = 'Approved / Canceled Training requests records!';
  }

  approveRequest(trainingRequest: TrainingRequest) {
    trainingRequest.status = STATUS.APPROVED;
  }

  cancelRequest(trainingRequest: TrainingRequest) {
    trainingRequest.status = STATUS.CANCELED;
  }

  previewDetailsDialog(trainingRequest: TrainingRequest): void {
    const { name, level, description } = trainingRequest.training;
    this.dialog.open(TrainingItemDialogComponent, {
      width: DialogWidth.previewDialog,
      data: { name, level, description }
    });
  }
}
