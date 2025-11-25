import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TaskListService {
  private baseUrl: string = 'http://localhost:8080/api/task-lists';

  constructor(private http: HttpClient) {}

  getAllTaskLists(): Observable<Object> {
    return this.http.get(this.baseUrl);
  }

  createTaskList(taskListData: any): Observable<Object> {
    return this.http.post(this.baseUrl, taskListData);
  }
}
