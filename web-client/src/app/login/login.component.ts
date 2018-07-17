import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '../../../node_modules/@angular/material';
import { HttpResponse } from '../../../node_modules/@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { UserService } from '../services/user.service';
import { User } from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  returnUrl: string;
  submitting = false;

  //for snack bar toast notification position
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.authenticationService.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit(signInForm: NgForm) {
    if (signInForm.valid) {
      this.submitting = true;

      return this.authenticationService.login(signInForm.value.username, signInForm.value.password)
        .subscribe((response: HttpResponse<any>) => {
          const authToken = this.getAuthToken(response);
          this.saveUserToStorage(signInForm.value.username, authToken);
        }, error => {
          this.submitting = false;
          this.showNotification(error.message);
        });
    }
  }


  getAuthToken(response: HttpResponse<any>) {
    return response.headers.get('Authorization');
  }

  saveUserToStorage(username: string, token: string) {
    return this.userService.getByUsername(username, token).subscribe((user: User) => {
      user.token = token;
      localStorage.setItem('currentUser', JSON.stringify(user));
      this.router.navigate(['/authenticated']);
    });
  }

  extractData(res: Response) {
    let body = res.json();
    return body || {};
  }
  handleErrorObservable(error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.message || error);
  }

  showNotification(message: string) {
    this.snackBar.open(message, "Close", {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
}
