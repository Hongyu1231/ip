# Batman User Guide

**Batman** is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Batman can get your task and schedule management done faster than traditional GUI apps.

---

## Quick start

1. Ensure you have Java `17` installed in your Computer.
2. Download the latest `batman.jar` file from the releases section.
3. Copy the file to the folder you want to use as the home folder for your Batman Task Manager.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar batman.jar` command to run the application.
5. A CLI interface should appear in your terminal. Type the command in the command box and press Enter to execute it.

Some example commands you can try:

- `list` : Lists all current tasks.
- `todo read book` : Adds a generic task named "read book" to the list.
- `delete 1` : Deletes the 1st task shown in the current list.
- `bye` : Exits the app.

Refer to the [Features](#features) below for details of each command.

---

## Features

> **Notes about the command format:**
>
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
>   e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
> - Dates should follow the standard ISO format: `yyyy-MM-dd` or `yyyy-MM-dd HHmm`.<br>
>   e.g. `2026-10-15` or `2026-10-15 1800`.
> - Extra parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.<br>
>   e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Adding a todo : `todo`

Adds a task without any date or time attached to it.

**Format:** `todo DESCRIPTION`

**Examples:**

- `todo read CS2113 textbook`
- `todo wash coffee mug`

### Adding a deadline : `deadline`

Adds a task that needs to be done before a specific date and time. You can use other format for time, however, the `view` function will not work.

**Format:** `deadline DESCRIPTION /by yyyy-MM-dd [HHmm]` / `deadline DESCRIPTION /by TIME`

**Examples:**

- `deadline submit assignment /by 2026-10-15`
- `deadline return library book /by 2026-10-15 1800`

### Adding an event : `event`

Adds a task that starts at a specific time and ends at a specific time. You can use other format for time, however, the `view` function will not work.

**Format:** `event DESCRIPTION /from yyyy-MM-dd [HHmm] /to yyyy-MM-dd [HHmm]` / `event DESCRIPTION /from TIME /to TIME`

**Examples:**

- `event project meeting /from 2026-10-14 1400 /to 2026-10-14 1600`
- `event hackathon /from 2026-10-15 /to 2026-10-17`

### Listing all tasks : `list`

Shows a list of all tasks currently in your task manager.

**Format:** `list`

### Marking a task as done : `mark`

Marks an existing task in the list as completed.

**Format:** `mark INDEX`

- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, …

**Examples:**

- `mark 2` marks the 2nd task in the list as done.

### Unmarking a task : `unmark`

Marks a previously completed task as incomplete.

**Format:** `unmark INDEX`

**Examples:**

- `unmark 1` removes the completion status of the 1st task.

### Locating tasks by keyword : `find`

Finds tasks who contains the given keyword.

**Format:** `find KEYWORD`

- The search is case-insensitive. e.g `book` will match `Book`.
- Only full words or partial word matches within the description string will be returned.

**Examples:**

- `find book` returns all tasks containing the word "book".

### Viewing tasks on a specific date : `view`

Displays all deadlines and events that occur on a specific target date.

**Format:** `view yyyy-MM-dd`

**Examples:**

- `view 2026-10-15` returns all deadlines due on Oct 15, 2026, and events that intersect with this date.

### Deleting a task : `delete`

Deletes the specified task from the list.

**Format:** `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.

**Examples:**

- `delete 3` deletes the 3rd task in the list.

### Exiting the program : `bye`

Exits the program.

**Format:** `bye`

---

## Saving the data

Batman data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

Batman data is saved automatically as a text file `[JAR file location]/data/tasks.txt`. Advanced users are welcome to update data directly by editing that data file.

> **Caution:** If your changes to the data file make its format invalid, Batman may discard all data or fail to load. Hence, it is recommended to take a backup of the file before editing it.

---

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and copy your existing `data/tasks.txt` file into the `data` folder of your new installation directory.

---

## Command summary

| Action       | Format, Examples                                                                                                                       |
| ------------ | -------------------------------------------------------------------------------------------------------------------------------------- |
| **Todo**     | `todo DESCRIPTION` <br> e.g., `todo read book`                                                                                         |
| **Deadline** | `deadline DESCRIPTION /by yyyy-MM-dd [HHmm]` <br> e.g., `deadline submit report /by 2026-10-15 2359`                                   |
| **Event**    | `event DESCRIPTION /from yyyy-MM-dd [HHmm] /to yyyy-MM-dd [HHmm]` <br> e.g., `event meeting /from 2026-10-14 1400 /to 2026-10-14 1600` |
| **List**     | `list`                                                                                                                                 |
| **Mark**     | `mark INDEX` <br> e.g., `mark 2`                                                                                                       |
| **Unmark**   | `unmark INDEX` <br> e.g., `unmark 1`                                                                                                   |
| **Find**     | `find KEYWORD` <br> e.g., `find homework`                                                                                              |
| **View**     | `view yyyy-MM-dd` <br> e.g., `view 2026-10-15`                                                                                         |
| **Delete**   | `delete INDEX` <br> e.g., `delete 3`                                                                                                   |
| **Bye**      | `bye`                                                                                                                                  |
