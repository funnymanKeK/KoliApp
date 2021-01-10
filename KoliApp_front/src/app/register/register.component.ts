import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    passwordAgain: ['', Validators.required]
  })

  errorMessage: string = "";

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  async submit(): Promise<void>{
    if(!this.form.valid){
      return;
    }
    if (this.form.value.password != this.form.value.passwordAgain) {
      this.errorMessage = "A megadott két jelszó nem egyezik.";
      return;
    }
    const success = await this.authService.register(this.form.value.username, this.form.value.password);
    if (!success) {
      this.errorMessage = "Sikertelen regisztráció.";
    } else {
      this.router.navigate(['/login']);
    }
  }

}
