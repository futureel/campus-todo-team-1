package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.enumeration.Priority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskServiceTest {

    @Test
    void shouldAddTask() {
        TaskService service = new TaskService();

        var task = service.addTask("完成需求评审");

        assertEquals(1L, task.getId());
        assertEquals("完成需求评审", task.getTitle());
        assertFalse(task.isCompleted());
        assertEquals(1, service.listAll().size());
    }

    @Test
    void shouldRejectBlankTitle() {
        TaskService service = new TaskService();

        assertThrows(IllegalArgumentException.class,
                () -> service.addTask("   "));
    }

    @Test
    void shouldUseMediumAsDefaultPriority() {
        TaskService service = new TaskService();

        var task = service.addTask("参加社团例会");

        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void shouldFilterTasksByPriority() {
        TaskService service = new TaskService();
        var highPriorityTask = service.addTask("提交课程作业", Priority.HIGH);
        service.addTask("整理课堂笔记");
        service.addTask("购买文具", Priority.LOW);

        var result = service.filterByPriority(Priority.HIGH);

        assertEquals(1, result.size());
        assertEquals(highPriorityTask, result.get(0));
    }

    @Test
    void shouldReturnEmptyListWhenPriorityHasNoMatch() {
        TaskService service = new TaskService();
        service.addTask("整理课堂笔记");

        var result = service.filterByPriority(Priority.LOW);

        assertEquals(0, result.size());
    }

    @Test
    void shouldRejectNullPriority() {
        TaskService service = new TaskService();

        assertThrows(IllegalArgumentException.class,
                () -> service.filterByPriority(null));
    }
}
