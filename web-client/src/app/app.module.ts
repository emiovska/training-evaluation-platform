import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppComponent } from "./app.component";
import { MaterialModule } from "./material/material.module";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { TrainingsListComponent } from "./trainings-list/trainings-list.component";
import { DeleteTrainingDialogComponent } from "./dialogs/delete-training-dialog/delete-training-dialog.component";
import { TrainingItemDialogComponent } from "./dialogs/training-item-dialog/training-item-dialog.component";
import { UpdateTrainingDialogComponent } from "./dialogs/update-training-dialog/update-training-dialog.component";
import { AddNewTrainingDialogComponent } from "./dialogs/add-new-training-dialog/add-new-training-dialog.component";
import { TrainingService } from "./services/mock-training.service";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { TrainingCardComponent } from "./training-card/training-card.component";

@NgModule({
  declarations: [
    AppComponent,
    TrainingsListComponent,
    UpdateTrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent,
    AddNewTrainingDialogComponent,
    TrainingCardComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  entryComponents: [
    UpdateTrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent,
    AddNewTrainingDialogComponent
  ],
  providers: [TrainingService],
  bootstrap: [AppComponent]
})
export class AppModule {}
