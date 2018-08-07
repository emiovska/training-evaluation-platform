import { Routes, RouterModule } from "@angular/router";

import { TrainingsListComponent } from "./trainings-list/trainings-list.component";
import { AuthGuard } from "./security/auth-guard";
import { LoginComponent } from "./login/login.component";
import { RegisterUserComponent } from "./register-user/register-user.component";
import { UsersComponent } from "./users/users.component";
import { HomeComponent } from "./home/home.component";

const appRoutes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'trainings', component: TrainingsListComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterUserComponent },
    { path: 'users', component: UsersComponent, canActivate: [AuthGuard] },

    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
