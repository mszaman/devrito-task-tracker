export class TaskList {
  private title: string;
  private description: string;
  constructor(title: string, description: string) {
    this.title = title;
    this.description = description;
  }

  public setTitle(title: string) {
    this.title = title;
  }

  public getTitle(): string {
    return this.title;
  }

  public setDescription(description: string) {
    this.description = description;
  }

  public getDescription(): string {
    return this.description;
  }
}
