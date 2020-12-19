import { Injectable } from '@angular/core';
import { Post } from './post';
import { User } from './user';
import { Comment } from './comment';
import { HttpClient } from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})

export class CommentService {
    constructor(
        private httpClient: HttpClient
    ) { }

    async createComment(comment: Comment, postId: number): Promise<void> {
        await this.httpClient.post<Comment>('/api/comments/', comment).toPromise();
    }
}