import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from './loginresponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public authenticated: boolean;
  public id?: number;
  public isAdmin?: boolean;
  private token?: string;
  private username?: string;

  constructor(
    private httpClient: HttpClient
  ) { 
    this.authenticated = false;
  }

  async login(username: string, password: string): Promise<boolean> {
    const response = await this.httpClient.post<LoginResponse>('/api/auth/login', {'username': username, 'password': password}).toPromise();
    this.id = response.id;
    this.authenticated = response.id != null;
    this.token = response.refreshToken;
    this.username = response.username;
    this.isAdmin = response.role == "ROLE_ADMIN";
    return this.authenticated;
  }

  async register(username: string, password: string): Promise<boolean> {
    const response = await this.httpClient.post<boolean>('/api/auth/signup', {'username': username, 'password': password}).toPromise();
    return response;
  }

  async logout(): Promise<void> {
    this.authenticated = false;
    await this.httpClient.post<any>('/api/auth/logout', {'username': this.username, 'refreshToken': this.token});
  }
}
