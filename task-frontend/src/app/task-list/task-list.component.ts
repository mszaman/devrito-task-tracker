import { Component, OnInit } from '@angular/core';
import { TaskList } from '../services/api/models/TaskList';
import { TaskListService } from '../services/api/tasks/task-list.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {
  public taskLists: any;

  public message: string = "";

  public id: string = "";

  isAddTaskListFormOpen: boolean = false;

  isShowAddTaskListButton: boolean = true;

  constructor(private service: TaskListService) {}

  ngOnInit(): void {
    this.getTaskLists();
  }

  onClickAddTaskList() {
    this.isAddTaskListFormOpen = true;
    this.isShowAddTaskListButton = false;
  }

  closeAddTaskListForm(value: boolean) {
    this.isAddTaskListFormOpen = value;
    this.isShowAddTaskListButton = true;
  }

  getTaskLists() {
    this.service.getAllTaskLists().subscribe({
      next: (data) => {
        this.taskLists = data;
      },
    });
  }

  onFormSubmission(taskListData: any) {
    this.service.createTaskList(taskListData).subscribe({
      next: (response: any) => {
        if(response.status == 201) {
          this.message = "Task list created successfully";
          this.getTaskLists();
        }
      },
      error: (response: any) => {
        this.message = response.error.message;
      },

    });
  }

  onClickEditButton(id: string) {

  }

  onDelete(id: string) {
    this.service.deleteTaskList(id).subscribe({
      next: (response: HttpResponse<any>) => {
        if(response.status == 204) {
          this.message = "Task list deleted successfully";
          this.getTaskLists();
        }
      },
    })

  }
}
