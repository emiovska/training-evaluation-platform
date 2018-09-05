import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { TrainingRequest } from '../models/training-request';
import { trainingRequests } from '../../api/training-requests/training-requests';
import { allTrainingRequests } from '../../api/training-requests/all-training-request';
import { Observable } from '../../../node_modules/rxjs';


@Injectable()
export class TrainingRequestService {
    private trainingRequestUrl = 'http://localhost:8080/trainingRequest';

    constructor(private http: HttpClient) { }

    getTrainingRequestsByUser(): TrainingRequest[] {
        return trainingRequests;
    }

    getAllTrainingRequestByUser() {
        return allTrainingRequests;
    }

    createNewTrainingRequest(trainingRequest): Observable<Object>{
        return this.http.post(`${this.trainingRequestUrl}/new`, trainingRequest);
    }
}
