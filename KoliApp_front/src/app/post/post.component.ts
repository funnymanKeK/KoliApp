import { Component, Input, OnInit, Output } from '@angular/core';
import { Post } from '../core/post';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../core/post.service';
import { CommentService } from '../core/comment.service';
import { Comment } from '../core/comment';
import { AuthService } from '../core/auth.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  @Input() post!: Post;

  public form: FormGroup = this.fb.group({
    text: ['', Validators.required]
  })

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private commentService: CommentService,
    private authServive: AuthService
  ) { }

  async ngOnInit(): Promise<void> {
    
  }

  submit(): void {
    if(!this.form.valid){
      return;
    }
    console.log(this.form.value);

    const postId = this.form.value['postId']; 

    this.commentService.createComment(this.form.value['text'], this.post.id);
  }
}