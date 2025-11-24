import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TaskListService {
  private baseUrl: string = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAllTaskLists(): Observable<Object> {
    const taskListsUrl = `${this.baseUrl}/task-lists`;

    return this.http.get(taskListsUrl);
  }
}
