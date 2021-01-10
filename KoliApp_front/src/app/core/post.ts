import { User }  from './user';
import { Comment }  from './comment';
import { Room }  from './room';

export interface Post {
    id : number;
    numberOfLikes: number,
    roomLevel: number,
    roomNumber: number,
    title: string,
    text: string,
    username: string,
    comments?: Array<Comment>
}