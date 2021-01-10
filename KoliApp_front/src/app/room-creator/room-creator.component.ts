import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDatepicker } from '@angular/material/datepicker';
import { Room } from '../core/room';
import { RoomService } from '../core/room.service';
import { RoomCreatorService } from '../core/room-creator.service';
import { AuthService } from '../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-room-creator',
  templateUrl: './room-creator.component.html',
  styleUrls: ['./room-creator.component.scss']
})
export class RoomCreatorComponent implements OnInit {

  public form: FormGroup = this.fb.group({
    date: ['', Validators.required],
    fromTime: ['', Validators.required],
    toTime: ['', Validators.required]
  })
  showError: boolean = false;
  showSuccess: boolean = false;

  public rooms: Room[] = [];
  public selectedRoom: Room = {
    id: 0,
    level: 0,
    number: 0,
  };

  constructor(
    private fb : FormBuilder,
    private roomService: RoomService,
    private roomCreatorService: RoomCreatorService,
    private authService: AuthService,
    private router: Router
  ) {
    if (!this.authService.authenticated) {
      this.router.navigate(['/post']);
    }
  }

  async ngOnInit(): Promise<void> {
    this.rooms = await this.roomService.getRooms();
  }

  submit(){
    if (!this.form.valid || this.selectedRoom.id == 0) {
      this.showError = true;
      this.showSuccess = false;
      return;
    }
    this.roomCreatorService.createSchedule(this.selectedRoom.id, this.form.value['date'], this.form.value['fromTime'], this.form.value['toTime']);
    this.showSuccess = true;
    this.showError = false;
  }
}
