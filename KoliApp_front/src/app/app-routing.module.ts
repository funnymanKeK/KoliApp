import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersComponent } from './users/users.component';
import { PostComponent } from './post/post.component';
import { PostCreatorComponent } from './post-creator/post-creator.component';
import { RoomCreatorComponent } from './room-creator/room-creator.component';

const routes: Routes = [
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'post',
    component: PostComponent
  },
  {
    path: 'create-post',
    component: PostCreatorComponent
  },
  {
    path: 'create-room',
    component: RoomCreatorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
