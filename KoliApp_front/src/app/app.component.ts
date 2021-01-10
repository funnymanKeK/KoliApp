import { Component } from '@angular/core';
import { AuthService } from './core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(
    public authService: AuthService,
    private route: Router
  ) { }

  title = 'koliappf';

  toLogin() {
    this.route.navigate(['/login']);
  }

  toRegister() {
    this.route.navigate(['/register']);
  }

  logout() {
    this.authService.logout();
  }
}
