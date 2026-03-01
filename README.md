# TaskTracker

A CLI task management application built in Java — created as part of a Software Engineering course to learn a new language from scratch and apply OOP design principles.

---

## ✨ Features

- **Create tasks** with a name, description, and status
- **Update tasks**  edit name, description, or status individually
- **Delete tasks** by ID
- **List all tasks** in your file
- **Filter tasks by status**  see only what's TODO, in progress, completed, or failed
- **JSON persistence**  tasks are saved to a `.json` file and loaded back on next run
- **Colored CLI**  clean terminal UI with color-coded output
---

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven

### Build & Run

```bash
# Clone the repo
git clone https://github.com/Maru0x11/Task-Tracker.git
cd Task-Tracker

# Build the jar (includes Gson via maven-shade-plugin)
mvn package

# Run
java -jar target/Taskstacker-1.0-SNAPSHOT.jar
```

---

## 🖥️ Usage

On launch you'll get two options:

```
── Main Menu ──
  1. Create a new JSON file
  2. Load an existing JSON file
  0. Exit
```

From there you can create or load a `.json` file and manage your tasks through the task menu.

### Task Statuses

| Status | Meaning |
|---|---|
| `TODO` | Not started yet |
| `IN_PROGRESS` | Currently working on it |
| `COMPLETED` | Done |
| `FAILED` | Did not complete |

---

## 📦 Dependencies

- [Gson](https://github.com/google/gson) `2.10` — JSON serialization

---

## 🧠 What I Learned

This was my first Java project, coming from a C++ background. Key things I picked up:

- Java OOP vs C++  no headers, no manual memory management, garbage collection
- `try-with-resources` for automatic file cleanup
- `HashMap` vs `ArrayList` tradeoffs
- Gson and generic type deserialization with `TypeToken`
- Separation of concerns and dependency injection in practice

---
🗺️ Roadmap

 GUI  replace the CLI with a proper graphical interface
 Markdown support  write and render task descriptions in Markdown
 Note taking  attach notes to tasks or create standalone notes
 API integration connect to external endpoints for sync and data persistence

---


## 📄 License

MIT
