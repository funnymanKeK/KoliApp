import { User }  from './user';
import { Comment }  from './comment';


export interface Post {
    user : User;
    title : string;
    text : string;
    room : string;
    comments: Array<Comment>;
}