import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersComponent } from './users/users.component';
import { PostlistComponent } from './postlist/postlist.component';
import { PostCreatorComponent } from './post-creator/post-creator.component';
import { RoomCreatorComponent } from './room-creator/room-creator.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'post',
    component: PostlistComponent
  },
  {
    path: 'create-post',
    component: PostCreatorComponent
  },
  {
    path: 'create-room',
    component: RoomCreatorComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }