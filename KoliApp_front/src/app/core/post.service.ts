import { Injectable } from '@angular/core';
import { Post } from './post';
import { HttpClient } from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})

export class PostService {

    posts: Post[] = [{
        username: 'viktor',
        title: 'teszt1',
        text: 'Az Alföld az új idő negyedidőszakában a pleisztocén korban keletkezett.',
        room: '103',
        comments: [
          "xd",
          "lol"
        ]
      },{
        username: 'kukoár23',
        title: 'teszt2',
        text: 'A terület tökéletes síkság, a szintkülönbségek elenyészőek. A felszínt a folyók munkája alakította.',
        room: '235',
        comments: []
      },{
        username: 'jano',
        title: 'teszt3',
        text: 'Barabás községtől északkeletre fekszik az Alföld legmagasabb, nem homokvidékekhez tartozó pontja.',
        room: '342',
        comments: []
      }
      ];

    constructor(
        private httpClient: HttpClient
    ) { }
    
    async getPosts() : Promise<Post[]> {
        //szerintem ezután lehet törölni a this.posts-t úgy ahogy van
        //ezt kommenteld ki, és ne a this.posts-ot hanem a posts-ot add vissza meg írd át az egyel lejjebbi sorban a '/posts'-ot a jó url-re
        //const posts = await this.httpClient.get<Post[]>('/posts').toPromise();
        return this.posts;
    }
}