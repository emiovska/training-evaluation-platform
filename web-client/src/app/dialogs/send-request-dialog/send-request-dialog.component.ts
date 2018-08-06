import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TrainingService } from '../../services/training.service';
import { Training } from '../../models/training';
import { ToastNotificationService } from '../../services/toast-notification.service';

@Component({
  selector: 'app-delete-training-dialog',
  templateUrl: './send-request-dialog.component.html',
  styleUrls: ['./send-request-dialog.component.css']
})
export class SendRequestDialogComponent {

  training: Training;

  constructor(public dialogRef: MatDialogRef<SendRequestDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private trainingService: TrainingService,
    private toastNotificationService: ToastNotificationService) { };

  ngOnInit(): void {
    this.training = this.data;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  confirm(): void {
    this.toastNotificationService.showNotification(`The request for training ${this.training.name} is send. Please wait for approval by trainer !`);
    this.dialogRef.close(null);
  }

}
