import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Post } from '../core/post';
import { PostService } from '../core/post.service';
import { Room } from '../core/room';
import { RoomService } from '../core/room.service';
import { User } from '../core/user';

@Component({
  selector: 'app-post-creator',
  templateUrl: './post-creator.component.html',
  styleUrls: ['./post-creator.component.scss']
})
export class PostCreatorComponent implements OnInit {

  public form: FormGroup = this.fb.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
  })

  constructor(private fb: FormBuilder,
    private postService : PostService,
    private roomService : RoomService) {
  }

  public rooms: Room[] = [];
  public selectedRoom: Room = {
    level: 0,
    number: 0,
  };

  async ngOnInit(): Promise<void> {
    this.rooms = await this.roomService.getPosts();

    
  }

  submit(): void {
    if(!this.form.valid){
      return;
    }

    const user: User = {
      username : 'Jani'
    }

    const post: Post = {
      user : user,
      title : this.form.value['title'],
      room : this.selectedRoom,
      text : this.form.value['description'],
    }
    this.postService.createPost(post);
  }

}
