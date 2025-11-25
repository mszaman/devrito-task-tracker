import {
  Component,
  ElementRef,
  EventEmitter,
  Output,
  ViewChild,
} from '@angular/core';
import { NgForm } from '@angular/forms';
import { TaskList } from 'src/app/services/api/models/TaskList';

@Component({
  selector: 'app-task-list-form',
  templateUrl: './task-list-form.component.html',
  styleUrls: ['./task-list-form.component.css'],
})
export class TaskListFormComponent {
  @ViewChild('titleInput')
  titleInput!: ElementRef;

  @Output()
  isAddTaskListFormOpen: EventEmitter<boolean> = new EventEmitter<boolean>();

  @Output()
  emitTaskListFormData: EventEmitter<TaskList> = new EventEmitter<TaskList>();

  ngAfterViewInit() {
    this.titleInput.nativeElement.focus();
  }

  onCancle() {
    this.isAddTaskListFormOpen.emit(false);
  }

  onSubmit(form: NgForm) {
    this.emitTaskListFormData.emit(form.value);

    this.isAddTaskListFormOpen.emit(false);
  }
}
