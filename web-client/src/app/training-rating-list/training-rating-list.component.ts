import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '../../../node_modules/@angular/material';
import { TrainingRating } from '../models/training-rate';
import { TrainingRatingService } from '../services/training-rating.service';
import { RatingChangeEvent } from 'angular-star-rating';
import { ToastNotificationService } from '../services/toast-notification.service';

@Component({
  selector: 'app-training-rating-list',
  templateUrl: './training-rating-list.component.html',
  styleUrls: ['./training-rating-list.component.css']
})
export class TrainingRatingListComponent implements OnInit {

  dataSource: MatTableDataSource<TrainingRating>;
  numStars: number;
  onRatingChangeResult: RatingChangeEvent;
  rateIcon: string;
  rateTitle: string;

  constructor(public dialog: MatDialog,
              private trainingRatingService: TrainingRatingService,
              private toastNotificationService: ToastNotificationService) { }

  displayedColumns = ['id', 'user', 'training', 'rate', 'actions'];

  ngOnInit() {
    const trainingRatings = this.trainingRatingService.getAllTrainingRatings();
    this.dataSource = new MatTableDataSource(trainingRatings);
    this.numStars = 0;
    this.rateIcon="star";
    this.rateTitle = "Rate completed training records!"
  }

  rateRequest(trainingRating: TrainingRating) {
    trainingRating.rating = this.numStars;
    const messageInfo = `Training ${trainingRating.training.name} is rated with ${this.numStars} stars!`;
    let message = this.numStars == 0 ? 'You must select some star' : this.numStars >= 4 ? `${messageInfo} Congratulation you achieve cup!` : `${messageInfo}You must learn more to achieve cup`;
    this.toastNotificationService.showNotification(message);
    this.numStars = 0;
  }

  onRatingChange = ($event: RatingChangeEvent) => {
    console.log('onRatingUpdated $event: ', $event);
    this.numStars = $event.rating;
    this.onRatingChangeResult = $event;
  };
}
