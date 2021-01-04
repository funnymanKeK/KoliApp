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
        const posts = await this.httpClient.get<Post[]>('/api/post/').toPromise();
        return posts;
    }

    async createPost(post: Post): Promise<void> {
        await this.httpClient.post<Post>('/api/post/', post).toPromise();
    }

}