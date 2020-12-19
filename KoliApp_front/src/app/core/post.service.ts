import { Injectable } from '@angular/core';
import { Post } from './post';
import { User } from './user';
import { HttpClient } from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})

export class PostService {

    constructor(
        private httpClient: HttpClient
    ) { }
    
    async getPosts(): Promise<Post[]> {
        //szerintem ezután lehet törölni a this.posts-t úgy ahogy van
        //ezt kommenteld ki, és ne a this.posts-ot hanem a posts-ot add vissza meg írd át az egyel lejjebbi sorban a '/posts'-ot a jó url-re
        const posts = await this.httpClient.get<Post[]>('/api/posts/').toPromise();
        return posts;
    }

    async createPost(post: Post): Promise<void> {
        await this.httpClient.post<Post>('/api/posts/', post).toPromise();
    }

}