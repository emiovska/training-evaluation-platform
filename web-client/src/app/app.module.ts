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
import { TrainingService } from './services/training.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TrainingCardComponent } from './training-card/training-card.component';
import { FilterTrainingService } from './services/filter-trainings.service';
import { FilterTrainingsComponent } from './filter-trainings/filter-trainings.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './security/auth-guard';
import { AuthenticationService } from './services/authentication.service';
import { JwtInterceptor } from './helpers/jwt.interceptor';
import { fakeBackendProvider } from './helpers/fake-backend';
import { routing } from './app.routing';
import { RegisterUserComponent } from './register-user/register-user.component';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [
    AppComponent,
    TrainingsListComponent,
    UpdateTrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent,
    AddNewTrainingDialogComponent,
    TrainingCardComponent,
    FilterTrainingsComponent,
    LoginComponent,
    RegisterUserComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    routing
  ],
  entryComponents: [
    UpdateTrainingDialogComponent,
    DeleteTrainingDialogComponent,
    TrainingItemDialogComponent,
    AddNewTrainingDialogComponent
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    TrainingService,
    FilterTrainingService,
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    fakeBackendProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
