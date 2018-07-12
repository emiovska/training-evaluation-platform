import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { first } from '../../../node_modules/rxjs/operators';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '../../../node_modules/@angular/material';

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
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.authenticationService.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit(signInForm: NgForm) {
    if (signInForm.valid) {
      this.submitting = true;

      this.authenticationService.login(signInForm.value.username, signInForm.value.password)
        .pipe(first()).subscribe(
          data => {
            console.log('got valid: ', data);
            this.router.navigate(['/authenticated']);
          }, error => {
            this.submitting = false;
            this.showNotification(error.message);
          }
        );
    }
  }

  showNotification(message: string) {
    this.snackBar.open(message, "Close", {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
}
