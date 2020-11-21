import { Component, OnInit } from '@angular/core';
import { Post } from '../core/post';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {


  posts: Post[] = [{
    username: 'viktor',
    title: 'teszt1',
    text: 'Az Alföld az új idő negyedidőszakában a pleisztocén korban keletkezett.',
    room: '103',
  },{
    username: 'viktor',
    title: 'teszt2',
    text: 'A terület tökéletes síkság, a szintkülönbségek elenyészőek. A felszínt a folyók munkája alakította.',
    room: '235', 
  },{
    username: 'jano',
    title: 'teszt3',
    text: 'Barabás községtől északkeletre fekszik az Alföld legmagasabb, nem homokvidékekhez tartozó pontja.',
    room: '342'
  }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
