import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { formatDate } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RoomCreatorService {
  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) { }

  async createSchedule(roomId: number, date: Date, fromTime: string, toTime: string): Promise<boolean> {
    const fromDate = formatDate(date, "yyyy/MM/dd", 'en-EN') + "-" + fromTime;
    const toDate = formatDate(date, "yyyy/MM/dd", 'en-EN') + "-" + toTime;
    const answer = await this.httpClient.post<any>('/api/schedule/check', {'userId': this.authService.id, 'roomId': roomId, 'fromDate': fromDate, 'toDate': toDate}).toPromise();
    if (!answer) {
        return false;
    }
    await this.httpClient.post<any>('/api/schedule', {'userId': this.authService.id, 'roomId': roomId, 'fromDate': fromDate, 'toDate': toDate}).toPromise();
    await this.httpClient.post<any>('/api/post/schedule', {'userId': 2, 'roomId': roomId, 'fromDate': fromDate, 'toDate': toDate}).toPromise();
    return true;
  }
}
