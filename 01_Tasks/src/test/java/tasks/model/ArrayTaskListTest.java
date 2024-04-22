package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListTest {
    @Spy
    private ArrayTaskList taskList;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTaskUnit() {
        Task task = new Task("Title", new Date());

        taskList.add(task);

        Mockito.verify(taskList).add(task);
        Mockito.doReturn(1).when(taskList).size();
        assertEquals(1, taskList.size());
    }

    @Test
    void testRemoveTaskUnit() {
        Task task = new Task("Title", new Date());

        taskList.add(task);
        taskList.remove(task);

        Mockito.verify(taskList).remove(task);
        Mockito.doReturn(0).when(taskList).size();
        assertEquals(0, taskList.size());
    }

    @Test
    void testAddTaskIntegration() {
        Task task = Mockito.mock(Task.class);
        ArrayTaskList taskList = new ArrayTaskList();
        TasksService service = new TasksService(taskList);

        taskList.add(task);

        assertEquals(1, service.getObservableList().size());
    }

    @Test
    void testRemoveTaskIntegration() {
        Task task = Mockito.mock(Task.class);
        ArrayTaskList taskList = new ArrayTaskList();
        TasksService service = new TasksService(taskList);

        taskList.add(task);
        taskList.remove(task);

        assertEquals(0, service.getObservableList().size());
    }

}