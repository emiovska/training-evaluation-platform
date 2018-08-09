import { Injectable } from '@angular/core';
import { TrainingRating } from '../models/training-rate';
import { trainingRatings } from '../../api/training-ratings/training-ratings';

@Injectable()
export class TrainingRatingService {
    constructor() { }

    getAllTrainingRatings(): TrainingRating[] {
        return trainingRatings;
    }
}

