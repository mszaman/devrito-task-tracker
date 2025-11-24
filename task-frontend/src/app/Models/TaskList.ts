export class TaskList {
  private title: string;
  private description: string;

  constructor() {
    this.title = '';
    this.description = '';
  }

  getTitle(): string {
    return this.title;
  }

  setTitle(title: string): void {
    this.title = title;
  }

  getDescription(): string {
    return this.description;
  }

  setDescription(description: string): void {
    this.description = description;
  }
}
