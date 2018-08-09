
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UploaderService } from '../services/uploader.service';
import { TrainingRatingService } from '../services/training-rating.service';
import { TrainingRating } from '../models/training-rate';

@Component({
  selector: 'app-self-profile',
  templateUrl: './self-profile.component.html',
  styleUrls: ['./self-profile.component.css']
})
export class SelfProfileComponent implements OnInit {

  currentUser: User;
  trainingRate: TrainingRating[];

  constructor(private uploadService: UploaderService,
    private trainingRatingService: TrainingRatingService) { }

  ngOnInit() {
    this.currentUser = this.getCurrentUser();

    this.uploadService.uploadImage(this.currentUser.username, this.currentUser.token);
    this.trainingRate = this.trainingRatingService.getAllTrainingRatings();
    console.log("Self training rate", this.trainingRate);
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

}
