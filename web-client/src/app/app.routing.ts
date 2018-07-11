import { Routes, RouterModule } from "@angular/router";

import { TrainingsListComponent } from "./trainings-list/trainings-list.component";
import { AuthGuard } from "./security/auth-guard";
import { LoginComponent } from "./login/login.component";

const appRoutes: Routes = [
    { path: '', component: TrainingsListComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },

    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
