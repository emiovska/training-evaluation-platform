import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Training } from '../models/training';

@Injectable()
export class TrainingService {

  private trainingUrl = '/api';

  constructor(private http: HttpClient) { }

  getAllTrainings(): Observable<any> {
    return this.http.get(`${this.trainingUrl}/trainings`);
  }

  getTraining(id: number): Observable<Object> {
    return this.http.get(`${this.trainingUrl}/training/${id}`)
  }

  createNewTraining(newTraining: Training): Observable<Object> {
    return this.http.post(`${this.trainingUrl}/`, newTraining);
  }

  updateTraining(id: number, updateTraining: Training): Observable<Object> {
    return this.http.post(`${this.trainingUrl}/training/${id}`, updateTraining);
  }

  deleteTraining(id: number): Observable<any> {
    return this.http.delete(`${this.trainingUrl}/training/${id}`, { responseType: 'text' })
  }

  filterByName(name: string): Observable<Object> {
    return this.http.get(`${this.trainingUrl}/training/filterByName/${name}`)
  }

  filterByLevel(level: string): Observable<Object> {
    return this.http.get(`${this.trainingUrl}/training/filterByLevel/${level}`)
  }
}
