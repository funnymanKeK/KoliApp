import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from './loginresponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public authenticated: boolean;
  public id?: number;
  private token?: string;
  private username?: string;

  constructor(
    private httpClient: HttpClient
  ) { 
    this.authenticated = false;
  }

  async login(username: string, password: string): Promise<void> {
    const response = await this.httpClient.post<LoginResponse>('/api/api/auth/login', {'username': username, 'password': password}).toPromise();
    this.id = response.id;
    this.authenticated = response.id != null;
    this.token = response.refreshToken;
    this.username = response.username;
    console.log(response);
  }

  async logout(): Promise<void> {
    this.authenticated = false;
    await this.httpClient.post<any>('/api/api/auth/logout', {'username': this.username, 'refreshToken': this.token});
  }
}
