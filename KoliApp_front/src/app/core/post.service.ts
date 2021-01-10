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
    
    async deletePost(postId: number): Promise<void> {
        const response = await this.httpClient.post<any>('/api/post/archive', {'userId': this.authService.id, 'postId': postId}).toPromise();
    }

    async hasUserLiked(postId: number): Promise<boolean> {
        const response = await this.httpClient.post<boolean>('/api/post/liked', {'userId': this.authService.id, 'postId': postId}).toPromise();
        return response;
    }
    
    async like(postId: number): Promise<void> {
        const response = await this.httpClient.post<any>('/api/post/like', {'userId': this.authService.id, 'postId': postId}).toPromise();
    }
}