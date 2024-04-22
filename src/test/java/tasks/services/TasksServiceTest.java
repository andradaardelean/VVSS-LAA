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
    @Test
    @Tag("ECP")
    void testParseFromStringToSecondsValidInput() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "12:30"; // valid input

        // Act
        int result = tasksService.parseFromStringToSeconds(inputTime);

        // Assert
        assertEquals(45000, result); // 12 * 3600 + 30 * 60
    }
    @Test
    @Tag("ECP")
    void testParseFromStringToSecondsInvalidFormat() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "12h00m"; // valid input

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }

    @Test
    @Tag("ECP")
    void testParseFromStringToSecondsInvalidHours() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "25:00"; // invalid hour

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }

    @Test
    @Tag("ECP")
    void testParseFromStringToSecondsNegativeMinutes() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "13:-12"; // invalid minutes

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }

    @Test
    @Tag("ECP")
    void testParseFromStringToSecondsInvalidInput() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "1322"; // invalid format

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }

    @Test
    @Tag("ECP")
    void testParseFromStringToSecondsInvalidInputNegative() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "-12:00"; // invalid format

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }

    @Test
    @Tag("BVA")
    void testParseFromStringToSecondsInvalidHr() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "24:00"; // invalid format

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }
    @Test
    @Tag("BVA")
    void testParseFromStringToSecondsInvalidMin() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "15:60"; // invalid format

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }

    @Test
    @Tag("BVA")
    void testParseFromStringToSecondsInvalidMin2() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "13:59"; // invalid format

        // Act
        try {
            int result = tasksService.parseFromStringToSeconds(inputTime);
            // Assert
            assertEquals(13*3600+59*60, result); // Dacă intrarea este invalidă, ar trebui să returnăm 0
        }
        catch(IllegalArgumentException ex){
            fail();
        }
    }

    @Test
    @Tag("BVA")
    void testParseFromStringToSecondsInvalidMin3() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "13:1"; // invalid format

        // Act
        try {
            int result = tasksService.parseFromStringToSeconds(inputTime);
            // Assert
            assertEquals(13*3600+60, result); // Dacă intrarea este invalidă, ar trebui să returnăm 0
        }
        catch(IllegalArgumentException ex){
            fail();
        }
    }

    @Test
    @Tag("BVA")
    void testParseFromStringToSecondsInvalidMin4() {
        // Arrange
        TasksService tasksService = new TasksService(new ArrayTaskList());
        String inputTime = "13:-1"; // invalid format

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.parseFromStringToSeconds(inputTime);
        });
    }
}
