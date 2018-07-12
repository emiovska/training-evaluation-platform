import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';

import { UserService } from '../services/user.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '../../../node_modules/@angular/material';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.scss']
})
export class RegisterUserComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;

  //for snack bar toast notification position
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit(signUpForm: NgForm) {
    console.log("Registe on Submit");
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    console.log("On submit register", this.registerForm.value)

    this.userService.register(this.registerForm.value)
      .pipe(first()).subscribe(
        data => {
          this.router.navigate(['/login']);
        },
        error => {
          console.log("Log error", error);
          console.log("Register error:",error.message);
          this.showNotification(error.message);
          this.registerForm.reset();
        }
      );
  }

  showNotification(message: string) {
    this.snackBar.open(message, "Close", {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }
}
