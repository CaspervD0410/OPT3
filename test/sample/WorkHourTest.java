package sample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkHourTest {
    Employee emp1 = new Employee();
    Client Plus = new Client();

    @Test
    public void SaveWorkHour() {
        assertTrue(WorkHour.saveWorkHour("06-05-2021", "10:00", "15:00", Plus, emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("06-05-2021", "10:00", "15:00", "glopr", emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("05-14-2028", "10:00", "15:00", Plus, emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("06-05-2021", "10:00", "08:00", Plus, emp1, "Werk"));
    }
}