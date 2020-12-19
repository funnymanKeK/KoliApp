import { User }  from './user';
import { Comment }  from './comment';
import { Room }  from './room';

export interface Post {
    id? : number;
    user : User;
    title : string;
    text : string;
    room : Room;
    comments?: Array<Comment>;
}