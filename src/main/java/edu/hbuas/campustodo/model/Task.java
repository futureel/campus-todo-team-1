package edu.hbuas.campustodo.model;

import edu.hbuas.campustodo.enumeration.Priority;

import java.util.Objects;

/**
 * 校园待办任务。
 */
public class Task {
    private final long id;
    private final String title;
    private boolean completed;
    private Priority priority;

    public Task(long id, String title) {
        this(id, title, Priority.MEDIUM);
    }

    public Task(long id, String title, Priority priority) {
        if (id <= 0) {
            throw new IllegalArgumentException("任务编号必须为正数");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        if (priority == null) {
            throw new IllegalArgumentException("任务优先级不能为空");
        }
        this.id = id;
        this.title = title.trim();
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("任务优先级不能为空");
        }
        this.priority = priority;
    }

    public void complete() {
        completed = true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task task)) {
            return false;
        }
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
