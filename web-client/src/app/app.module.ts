import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MaterialModule } from './material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TrainingsListComponent } from './trainings-list/trainings-list.component';
import { DeleteTrainingDialogComponent } from './dialogs/delete-training-dialog/delete-training-dialog.component';
import { TrainingItemDialogComponent } from './dialogs/training-item-dialog/training-item-dialog.component';
import { UpdateTrainingDialogComponent } from './dialogs/update-training-dialog/update-training-dialog.component';
import { AddNewTrainingDialogComponent } from './dialogs/add-new-training-dialog/add-new-training-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainingsListComponent,
    UpdateTrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent,
    AddNewTrainingDialogComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule
  ],
  entryComponents: [
    UpdateTrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent,
    AddNewTrainingDialogComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
