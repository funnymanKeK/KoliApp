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
    id: 0,
    level: 0,
    number: 0,
  };

  showSuccess: boolean = false;
  showError: boolean = false;

  async ngOnInit(): Promise<void> {
    this.rooms = await this.roomService.getPosts();

    
  }

  submit(): void {
    if(!this.form.valid){
      this.showError = true;
      return;
    }

    const user: User = {
      id: 1,
      username : 'Jani'
    }

    //this.postService.createPost(post);
    this.showSuccess= true;
  }

}
