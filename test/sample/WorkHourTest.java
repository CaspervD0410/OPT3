package sample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkHourTest {
    Employee emp1 = new Employee();
    Client cli1 = new Client("Plus");
    WorkHour hour = new WorkHour();

    @Test
    public void SaveWorkHour() {
        assertTrue(hour.saveWorkHour("06-05-2021", "10:00", "15:00", "Plus", emp1, "Werk"));
        assertFalse(hour.saveWorkHour("06-05-2021", "10:00", "15:00", "glopr", emp1, "Werk"));
        assertFalse(hour.saveWorkHour("05-12-2028", "10:00", "15:00", "Plus", emp1, "Werk"));
        assertFalse(hour.saveWorkHour("06-05-2021", "10:00", "08:00", "Plus", emp1, "Werk"));
    }
}