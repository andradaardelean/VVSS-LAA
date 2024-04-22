package tasks.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskIOTest {

    @Test
    void testGetFormattedInterval_ValidInterval_DaysOnly() {
        int interval = 86400; // 1 day (24 hours)
        String expected = "1 day";
        String result = TaskIO.getFormattedInterval(interval);
        assertEquals(expected, result);
    }

    @Test
    void testGetFormattedInterval_ValidInterval_HoursOnly() {
        int interval = 7200; // 2 hours
        String expected = "2 hours";
        String result = TaskIO.getFormattedInterval(interval);
        assertEquals(expected, result);
    }

    @Test
    void testGetFormattedInterval_ValidInterval_MinutesOnly() {
        int interval = 180; // 3 minutes
        String expected = "3 minutes";
        String result = TaskIO.getFormattedInterval(interval);
        assertEquals(expected, result);
    }

    @Test
    void testGetFormattedInterval_ValidInterval_SecondsOnly() {
        int interval = 45; // 45 seconds
        String expected = "45 seconds";
        String result = TaskIO.getFormattedInterval(interval);
        assertEquals(expected, result);
    }

    @Test
    void testGetFormattedInterval_ValidInterval_MultipleUnits() {
        int interval = 93723;
        String expected = "1 day 2 hours 2 minutes 3 seconds";
        String result = TaskIO.getFormattedInterval(interval);
        assertEquals(expected, result);
    }

    @Test
    void testGetFormattedInterval_ValidInterval_NoDays() {
        int interval = 3660; // 1 hour and 1 minute
        String expected = "1 hour 1 minute";
        String result = TaskIO.getFormattedInterval(interval);
        assertEquals(expected, result);
    }

    @Test
    void testGetFormattedInterval_ValidInterval_NoHoursNoMinutes() {
        int interval = 86430; // 1 hour and 1 minute
        String expected = "1 day 30 seconds";
        String result = TaskIO.getFormattedInterval(interval);
        assertEquals(expected, result); // ii defapt "1 day 0 hour 0 minute 30 seconds"
    }

    @Test
    void testGetFormattedInterval_ZeroInterval() {
        int interval = 0;
        assertThrows(IllegalArgumentException.class, () -> TaskIO.getFormattedInterval(interval));
    }

    @Test
    void testGetFormattedInterval_NegativeInterval() {
        int interval = -100;
        assertThrows(IllegalArgumentException.class, () -> TaskIO.getFormattedInterval(interval));
    }
}