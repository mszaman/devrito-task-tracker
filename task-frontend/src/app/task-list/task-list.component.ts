import { Component, OnInit } from '@angular/core';
import { TaskList } from '../services/api/models/TaskList';
import { TaskListService } from '../services/api/tasks/task-list.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {
  public taskLists: any;

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

  onFormSubmission(taskListData: any) {
    // console.log(taskListData);
    this.service.createTaskList(taskListData).subscribe({
      next: (result) => {
        console.log(result);
      },
    });
  }
}
