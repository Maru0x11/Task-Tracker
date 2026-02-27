# Task Tracker

A CLI-based task manager built in Java. Tasks are persisted locally in JSON format 
and support statuses (TODO, IN_PROGRESS, DONE), timestamps, and filtering by status.

## Features

- Add tasks with a description
- Update and delete tasks
- Mark tasks as todo, in progress,completed or failed
- List all tasks or filter by status
- Tasks persist locally in JSON format

## Tech Stack

- Java
- Gson (JSON serialization)
- Maven (Build System)

## Getting Started

### Prerequisites
- Java 17+
- Maven

### Installation
```bash
git clone https://github.com/Maru0x11/Task-Tracker.git
cd Task-Tracker
mvn compile
mvn exec:java -Dexec.mainClass="com.tasktracker.Main"
```

## Roadmap

- [ ] JavaFX GUI
- [ ] Task priorities
- [ ] Due dates and reminders
- [ ] Multiple task lists
- [ ] Export to markdown  
- [ ] Daily Todo list

## License

MIT
