import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Post } from '../core/post';
import { PostService } from '../core/post.service';
import { Room } from '../core/room';
import { RoomService } from '../core/room.service';
import { User } from '../core/user';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';

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

  constructor(
    private fb: FormBuilder,
    private postService : PostService,
    private roomService : RoomService,
    private authService: AuthService,
    private router: Router
  ) {
    if (!this.authService.authenticated) {
      this.router.navigate(['/post']);
    }
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
    this.rooms = await this.roomService.getRooms();
  }

  submit(): void {
    if(!this.form.valid || this.selectedRoom.id == 0){
      this.showError = true;
      this.showSuccess = false;
      return;
    }

    this.postService.createPost(this.form.value['title'], this.selectedRoom.id, this.form.value['description']);
    this.showSuccess = true;
    this.showError = false;
  }
}