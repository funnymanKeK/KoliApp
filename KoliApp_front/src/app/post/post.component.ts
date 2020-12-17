import { Component, OnInit } from '@angular/core';
import { Post } from '../core/post';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../core/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {


  posts: Post[] = [];

  public form: FormGroup = this.fb.group({
    title: ['', Validators.required],
    description: ['', Validators.required]
  })

  constructor(
    private fb: FormBuilder,
    private postService: PostService
  ) { }

  async ngOnInit(): Promise<void> {
    this.posts = await this.postService.getPosts();
  }

  submit(): void {
    if(!this.form.valid){
      return;
    }
    console.log(this.form.value);
  }

}
