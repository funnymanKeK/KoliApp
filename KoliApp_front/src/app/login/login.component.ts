import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../core/auth.service';

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

  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  submit(): void{
    if(!this.form.valid){
      return;
    }
    this.authService.login(this.form.value.username, this.form.value.password);
  }

}
