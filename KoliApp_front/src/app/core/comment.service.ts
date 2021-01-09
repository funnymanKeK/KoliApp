import { Injectable } from '@angular/core';
import { User } from './user';
import { Comment } from './comment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root'
})

export class CommentService {
    constructor(
        private httpClient: HttpClient,
        private authService: AuthService
    ) { }

    async createComment(comment: string, postId: number): Promise<void> {
        await this.httpClient.post<any>('/api/comment/', {'text': comment, 'userId': this.authService.id, 'postId': postId}).toPromise();
    }
}