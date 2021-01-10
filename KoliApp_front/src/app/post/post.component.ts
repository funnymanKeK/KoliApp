import { Component, Input, OnInit, Output } from '@angular/core';
import { Post } from '../core/post';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../core/post.service';
import { CommentService } from '../core/comment.service';
import { Comment } from '../core/comment';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';
import { PostlistComponent } from '../postlist/postlist.component';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  @Input() post!: Post;
  @Input() postList!: PostlistComponent;

  public form: FormGroup = this.fb.group({
    text: ['', Validators.required]
  })

  hasUserLiked: boolean = false;

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private commentService: CommentService,
    public authService: AuthService,
    private router: Router
  ) { }

  async ngOnInit(): Promise<void> {
    if (this.authService.authenticated) {
      this.hasUserLiked = await this.postService.hasUserLiked(this.post.id);
    }
  }

  async submit(): Promise<void> {
    if(!this.form.valid){
      return;
    }

    const postId = this.form.value['postId']; 

    await this.commentService.createComment(this.form.value['text'], this.post.id);

    this.postList.reload();
  }

  async delete(): Promise<void> {
    await this.postService.deletePost(this.post.id);

    this.postList.reload();
  }
  
  like(): void {
    this.postService.like(this.post.id);

    if (this.hasUserLiked) {
      this.hasUserLiked = false;
      this.post.numberOfLikes--;
    } else {
      this.hasUserLiked = true;
      this.post.numberOfLikes++;
    }
  }
}