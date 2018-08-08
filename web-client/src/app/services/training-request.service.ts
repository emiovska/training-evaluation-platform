import { Injectable } from '@angular/core';
import { TrainingRequest } from '../models/training-request';
import { trainingRequests } from '../../api/training-requests/training-requests';
import { allTrainingRequests } from '../../api/training-requests/all-training-request';

@Injectable()
export class TrainingRequestService {
    constructor() { }

    getTrainingRequestsByUser(): TrainingRequest[] {
        return trainingRequests;
    }

    getAllTrainingRequestByUser() {
        return allTrainingRequests;
    }

}
