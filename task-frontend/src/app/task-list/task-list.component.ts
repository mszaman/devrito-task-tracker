import { Component, OnInit } from '@angular/core';
import { TaskList } from '../Models/TaskList';
import { TaskListService } from '../services/api/tasks/task-list.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {
  taskLists: any;

  isAddTaskListFormOpen: boolean = false;

  isShowAddTaskListButton: boolean = true;

  constructor(private service: TaskListService) {}

  ngOnInit(): void {
    this.service.getAllTaskLists().subscribe({
      next: (data) => {
        console.log(data);
        this.taskLists = data;
      },
    });
  }

  onClickAddTaskList() {
    this.isAddTaskListFormOpen = true;
    this.isShowAddTaskListButton = false;
  }

  closeAddTaskListForm(value: boolean) {
    this.isAddTaskListFormOpen = value;
    this.isShowAddTaskListButton = true;
  }

  getSubmittedFormData(taskListData: TaskList) {
    console.log(taskListData);
  }
}
