import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Post } from '../core/post';
import { PostService } from '../core/post.service';
import { Room } from '../core/room';
import { User } from '../core/user';

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

  constructor(private fb: FormBuilder,
    private postService : PostService) {
  }

  ngOnInit(): void {
  }

  submit(): void {
    if(!this.form.valid){
      return;
    }

    const user: User = {
      username : 'Jani'
    }

    const room: Room = {
      level : 2,
      number : 333,
    }

    const post: Post = {
      user : user,
      title : this.form.get('title').value,
      room : room,
      text : this.form.get('description').value,
    }

    this.postService.createPost(post);

  }

}
