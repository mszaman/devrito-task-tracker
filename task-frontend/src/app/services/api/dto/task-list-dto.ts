import { TaskDto } from './task-dto';

export class TaskListDto {
  public id?: string;
  public title?: string;
  public description?: string;
  public counnt?: number;
  public progress?: number;
  public tasks?: TaskDto;
}
