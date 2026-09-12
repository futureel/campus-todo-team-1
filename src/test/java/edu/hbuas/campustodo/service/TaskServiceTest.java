package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    // 测试：正常完成已存在的任务，任务状态应变为已完成
    @Test
    void completeTask_existingId_taskMarkedCompleted() {
        TaskService service = new TaskService();
        Task task = service.addTask("测试完成任务");
        long taskId = task.getId();

        service.completeTask(taskId);

        assertTrue(service.listAll().get(0).isCompleted());
    }

    // 测试：完成不存在的任务编号，应抛出非法参数异常
    @Test
    void completeTask_nonExistingId_throwsIllegalArgumentException() {
        TaskService service = new TaskService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.completeTask(999L);
        });
    }

    // 测试：重复完成同一个任务，应抛出非法状态异常
    @Test
    void completeTask_alreadyCompleted_throwsIllegalStateException() {
        TaskService service = new TaskService();
        Task task = service.addTask("重复完成测试任务");
        long taskId = task.getId();
        // 第一次完成
        service.completeTask(taskId);

        // 第二次重复完成，应该报错
        assertThrows(IllegalStateException.class,
                () -> service.completeTask(taskId));
    }
}
