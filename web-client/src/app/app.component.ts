import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  pageTitle = 'Training evaluation platform';

  defaultLanguage = 'en';

  constructor(private authService: AuthenticationService,
    private translate: TranslateService) { };

  ngOnInit(): void {
    this.translate.use(this.defaultLanguage);
  }

  logout() {
    return this.authService.logout();
  }

  isAuthenticated() {
    const currentUser = localStorage.getItem('currentUser');
    return currentUser != null;
  }

  switchLanguage(language: string) {
    this.translate.use(language);
  }

}
