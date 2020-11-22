import { Component, OnInit } from '@angular/core';
import { Post } from '../core/post';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {


  posts: Post[] = [{
    username: 'viktor',
    title: 'teszt1',
    text: 'Az Alföld az új idő negyedidőszakában a pleisztocén korban keletkezett.',
    room: '103',
    comments: [
      "xd",
      "lol"
    ]
  },{
    username: 'viktor',
    title: 'teszt2',
    text: 'A terület tökéletes síkság, a szintkülönbségek elenyészőek. A felszínt a folyók munkája alakította.',
    room: '235',
    comments: []
  },{
    username: 'jano',
    title: 'teszt3',
    text: 'Barabás községtől északkeletre fekszik az Alföld legmagasabb, nem homokvidékekhez tartozó pontja.',
    room: '342',
    comments: []
  }
  ];

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
