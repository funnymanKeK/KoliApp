import { Component, OnInit } from '@angular/core';
import { Post } from '../core/post';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../core/post.service';
import { CommentService } from '../core/comment.service';
import { Comment } from '../core/comment';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  posts: Post[] = [];

  public form: FormGroup = this.fb.group({
    text: ['', Validators.required]
  })

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private commentService: CommentService
  ) { }

  async ngOnInit(): Promise<void> {
    this.posts = await this.postService.getPosts();
  }

  submit(): void {
    if(!this.form.valid){
      return;
    }
    console.log(this.form.value);

    const comment: Comment = {
      text: this.form.value['text'],
    };

    const postId = this.form.value['postId']; 

    this.commentService.createComment(comment, postId);
  }

}
