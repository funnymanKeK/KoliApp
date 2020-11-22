import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-post-creator',
  templateUrl: './post-creator.component.html',
  styleUrls: ['./post-creator.component.scss']
})
export class PostCreatorComponent implements OnInit {

  public form: FormGroup = this.fb.group({
    title: ['', Validators.required],
    description: ['', Validators.required]
  })

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
  }

  submit(): void {
    if(!this.form.valid){
      return;
    }
    console.log(this.form.value);
  }

}
