import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskListDto } from '../dto/task-list-dto';

@Injectable({
  providedIn: 'root',
})
export class TaskListService {
  private baseUrl: string = 'http://localhost:8080/api/task-lists';

  constructor(private http: HttpClient) {}

  getAllTaskLists(): Observable<TaskListDto> {
    return this.http.get<TaskListDto>(this.baseUrl);
  }

  createTaskList(taskListData: any): Observable<HttpResponse<any>> {
    return this.http.post<any>(this.baseUrl, taskListData, {
      observe: 'response'
    });
  }

  updateTaskList(id: string, taskListDataToUpdate: any): Observable<HttpResponse<any>> {
    const taskListToUpdate = `${this.baseUrl}/${id}`;
    return this.http.put<HttpResponse<any>>(taskListToUpdate, taskListDataToUpdate, {
      observe: 'response'
    });
  }

  deleteTaskList(id: string) : Observable<HttpResponse<any>> {
    const taskListToDelete = `${this.baseUrl}/${id}`;
    return this.http.delete<any>(taskListToDelete, {
      observe: 'response'
    });
  }
}
