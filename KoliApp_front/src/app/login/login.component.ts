import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  })

  showErrorMessage: boolean = false;

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
    const success = await this.authService.login(this.form.value.username, this.form.value.password);
    console.log(success);
    if (success) {
      this.router.navigate(['/post']);
    } else {
      this.showErrorMessage = true;
    }
  }

}
