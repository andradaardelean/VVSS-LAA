package tasks.services;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class TasksServiceTest {
    @DisplayName("TaskService")
    @Timeout(20)
    @ParameterizedTest
    @ValueSource(strings = {"10:10"})
//  1.Valid
    @Tag("ECP")
    @Test
    void testParseFromStringToSecondsValidInput() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "12:30"; // valid input

        // Act
        int result = tasksService.parseFromStringToSeconds(inputTime);

        // Assert
        assertEquals(45000, result); // 12 * 3600 + 30 * 60
    }

//  1.Invalid
    @Tag("ECP")
    @Test
    void testParseFromStringToSecondsInvalidInput() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "25:70"; // invalid input

        // Act
        try {
            int result = tasksService.parseFromStringToSeconds(inputTime);
            // Assert
            assertEquals(0, result); // Dacă intrarea este invalidă, ar trebui să returnăm 0
        }
        catch(IllegalArgumentException ex){
            assertTrue(false);
        }
    }

//  2.Valid
    @Tag("ECP")
    @Test
    void testGetIntervalInHoursValidInput() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        Task task = new Task("Test Task", new Date(), new Date(), 7200); // valid interval

        // Act
        String result = tasksService.getIntervalInHours(task);

        // Assert
        assertEquals("02:00", result); // 7200 seconds = 2 hours
    }

//  1.Invalid
    @Tag("BVA")
    @Test
    void testGetIntervalInHoursZeroInterval() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        try{
            Task task = new Task("Test Task", new Date(), new Date(), 0); // zero interval

            // Act
            String result = tasksService.getIntervalInHours(task);

            // Assert
            assertEquals("00:00", result); // Should default to 0:00 for zero interval
        }
        catch(IllegalArgumentException ex){
            assertTrue(false);
        }
    }

    //  2.Invalid
    @Tag("BVA")
    @Test
    void testGetIntervalInHoursDateOutBounded() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        try{
            // Create a Calendar instance
            Calendar calendar = Calendar.getInstance();

            // Set the date to December 31, 1969, 23:59:59 GMT
            calendar.set(1969, Calendar.DECEMBER, 31, 23, 59, 59);

            // Convert Calendar to Date
            Date date = calendar.getTime();
            Task task = new Task("Test Task", date, new Date(), 10800); // zero interval

            // Act
            String result = tasksService.getIntervalInHours(task);

            // Assert
            assertEquals("03:00", result); // Should default to 0:00 for zero interval
        }
        catch(IllegalArgumentException ex){
            assertTrue(false);
        }
    }

//  1.Valid
    @Tag("BVA")
    @Test
    void testGetIntervalInHoursLargeInterval() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        Task task = new Task("Test Task", new Date(), new Date(), 86400); // large interval (24 hours)

        // Act
        String result = tasksService.getIntervalInHours(task);

        // Assert
        assertEquals("24:00", result); // 86400 seconds = 24 hours
    }

    //  2.Valid
    @Tag("BVA")
    @Test
    void testGetIntervalInHoursEqualDates() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        try{
            Date date = new Date();
            Task task = new Task("Test Task", date, date, 10800); // zero interval

            // Act
            String result = tasksService.getIntervalInHours(task);

            // Assert
            assertEquals("03:00", result);
        }
        catch(IllegalArgumentException ex){
            assertTrue(false);
        }
    }
}
