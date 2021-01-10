import { Injectable } from '@angular/core';
import { Post } from './post';
import { User } from './user';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';


@Injectable({
    providedIn: 'root'
})

export class PostService {

    constructor(
        private httpClient: HttpClient,
        private authService: AuthService
    ) { }
    
    async getPosts(): Promise<Post[]> {
        const posts = await this.httpClient.get<Post[]>('/api/post/').toPromise();
        return posts;
    }

    async createPost(title: string, roomId: number, text: string): Promise<void> {
        await this.httpClient.post<Post>('/api/post/', {'title': title, 'roomId': roomId, 'content': text, 'userId': this.authService.id}).toPromise();
    }
}