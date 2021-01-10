import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modifypassword',
  templateUrl: './modifypassword.component.html',
  styleUrls: ['./modifypassword.component.scss']
})
export class ModifypasswordComponent implements OnInit {

  form: FormGroup = this.fb.group({
    password: ['', Validators.required],
    passwordAgain: ['', Validators.required]
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
    if (this.form.value.password != this.form.value.passwordAgain) {
      this.showErrorMessage = true;
      return;
    }
    await this.authService.modifyPassword(this.form.value.password);

    this.router.navigate(['/post']);
  }
}
