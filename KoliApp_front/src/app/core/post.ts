import { User }  from './user';
import { Comment }  from './comment';
import { Room }  from './room';

export interface Post {
    id : number;
    creatorLiked: boolean,
    numberOfLikes: number,
    roomLevel: number,
    roomNumber: number,
    title: string,
    text: string,
    username: string,
    comments?: Array<Comment>
}