package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksServiceTest2 {
    @Spy
    private ArrayTaskList taskList;

    @Spy
    private Task task = new Task("Title", new Date());
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void filterTaskTest(){
        TasksService ts = new TasksService(taskList);

        ts.filterTasks(new Date(), new Date());

        Mockito.verify(taskList).getAll();
    }

    @Test
    public void getIntervalInHoursTest(){
        TasksService ts = new TasksService(taskList);

        ts.getIntervalInHours(task);

        Mockito.verify(task).getRepeatInterval();
    }

    @Test
    public void filterTaskTestIntegration() throws ParseException {
        ArrayTaskList taskList = new ArrayTaskList();
        TasksService service = new TasksService(taskList);
        String startSource = "2012-02-29";
        String endSource = "2013-02-29";
        String currentSource = "2012-03-29";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse(startSource);
        Date current = format.parse(currentSource);
        Date end = format.parse(endSource);
        Task taskToAdd = new Task("Title", current);
        taskToAdd.setActive(true);

        taskList.add(taskToAdd);
        Task firstElementOfFilteredTask = service.filterTasks(start,end).iterator().next();

        assertEquals(1, service.getObservableList().size());
        assertEquals(taskToAdd, firstElementOfFilteredTask);
    }

    @Test
    public void getIntervalInHoursTestIntegration(){
        ArrayTaskList taskList = new ArrayTaskList();
        TasksService service = new TasksService(taskList);
        Date currentDate = new Date();
        currentDate.setTime(600000);
        Task taskToTest = new Task("Title", new Date(), new Date(), 1 * 60 * 60 + 12 * 60);

        String interval = service.getIntervalInHours(taskToTest);

        assertEquals(interval, "01:12");
    }

}
