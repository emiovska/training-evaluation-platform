import { Injectable } from '@angular/core';
import { TrainingRequest } from '../models/training-request';
import { trainingRequests } from '../../api/training-requests/training-requests';

@Injectable()
export class TrainingRequestService {
    constructor() { }

    getTrainingRequestsByUser(): TrainingRequest[] {
        return trainingRequests;
    }

}
