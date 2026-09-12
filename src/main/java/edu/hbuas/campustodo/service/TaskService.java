package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.enumeration.Priority;
import edu.hbuas.campustodo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务应用服务。学生将在功能分支中逐步扩展该类。
 */
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public Task addTask(String title) {
        return addTask(title, Priority.MEDIUM);
    }

    public Task addTask(String title, Priority priority) {
        Task task = new Task(nextId++, title, priority);
        tasks.add(task);
        return task;
    }

    public List<Task> listAll() {
        return List.copyOf(tasks);
    }

    public void completeTask(long taskId) {
        Task task = tasks.stream()
                .filter(candidate -> candidate.getId() == taskId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + taskId));
        if (task.isCompleted()) {
            throw new IllegalStateException("Task already completed: " + taskId);
        }
        task.complete();
    }

    public List<Task> filterByPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("筛选优先级不能为空");
        }
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
    }
    }
}
