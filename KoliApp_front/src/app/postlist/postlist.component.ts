import { Component, OnInit } from '@angular/core';
import { Post } from '../core/post';
import { PostService } from '../core/post.service';

@Component({
  selector: 'app-postlist',
  templateUrl: './postlist.component.html',
  styleUrls: ['./postlist.component.scss']
})
export class PostlistComponent implements OnInit {
  posts: Post[] = [];

  constructor(
    private postService: PostService
  ) { }

  async ngOnInit(): Promise<void> {
    this.posts = await this.postService.getPosts();
    console.log(this.posts);
  }

  async reload(): Promise<void> {
    this.posts = await this.postService.getPosts();
  } 
}