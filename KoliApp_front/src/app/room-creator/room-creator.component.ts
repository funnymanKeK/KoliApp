import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-room-creator',
  templateUrl: './room-creator.component.html',
  styleUrls: ['./room-creator.component.scss']
})
export class RoomCreatorComponent implements OnInit {

  public form: FormGroup = this.fb.group({
    
  })

  constructor(private fb : FormBuilder) { }

  ngOnInit(): void {
  }

  submit(){
  }

}
