package yeyAI;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList initialized with existing tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to remove.
     */
    public void removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index out of bounds!";
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the given index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index out of bounds!";
        return tasks.get(index);
    }

    /**
     * Retrieves the most recently added task.
     *
     * @return The last Task object in the list.
     */
    public Task getLastTask() {
        assert !tasks.isEmpty() : "Task list is empty, cannot get last task!";
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Retrieves the total number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Creates and returns a new TaskList of tasks that have matching descriptions with the query
     *
     * @param query Substring that has to match with the description of a task
     * @return a TaskList of all matching Tasks
     */
    public String findTasks(String query) {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(query)) {
                result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return result.toString();
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list";
        }
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i =0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }
}

