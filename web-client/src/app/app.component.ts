import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  pageTitle = 'Training evaluation platform';

  constructor(private authService: AuthenticationService){};
  
  logout(){
    return this.authService.logout();
  }

  isAuthenticated(){
    const currentUser = localStorage.getItem('currentUser');
    return currentUser != null;
  }
}
