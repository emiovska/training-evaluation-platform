import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MaterialModule } from './material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TrainingsListComponent } from './trainings-list/trainings-list.component';
import { TrainingDialogComponent } from './dialogs/training-dialog/training-dialog.component';
import { DeleteTrainingDialogComponent } from './dialogs/delete-training-dialog/delete-training-dialog.component';
import { TrainingItemDialogComponent } from './dialogs/training-item-dialog/training-item-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainingsListComponent,
    TrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule
  ],
  entryComponents: [
    TrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
