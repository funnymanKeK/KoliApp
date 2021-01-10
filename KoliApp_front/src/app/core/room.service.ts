import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Room } from './room';


@Injectable({
    providedIn: 'root'
})

export class RoomService {
    constructor(
        private httpClient: HttpClient
    ) { }

    async getRooms(): Promise<Room[]> {
        const rooms = await this.httpClient.get<Room[]>('/api/room/').toPromise();
        return rooms;
    }
}