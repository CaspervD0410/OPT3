package sample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkHourTest {
    Employee emp1 = new Employee("Hank","Technician",40);
    Client cli1 = new Client("Plus");

    @Test
    public void SaveWorkHour() {
        assertTrue(WorkHour.saveWorkHour("06-05-2021", "10:00", "15:00", "Plus", emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("06-05-2021", "10:00", "15:00", "glopr", emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("05-12-2028", "10:00", "15:00", "Plus", emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("06-05-2021", "10:00", "08:00", "Plus", emp1, "Werk"));
    }

    @Test
    public void showWorkHours() {
        //Bij deze test moet er naast de uitkomst van de Assert ook gekeken worden naar de uitgeprinte regels
        WorkHour.saveWorkHour("08-05-2021", "08:30", "10:30", "Plus", emp1, "Werk");
        assertEquals(2.0,cli1.showWorkHours(),0.01);
        WorkHour.saveWorkHour("03-05-2021", "16:00", "17:00", "Plus", emp1, "Werk");
        assertEquals(3.0,cli1.showWorkHours(),0.01);
        WorkHour.saveWorkHour("05-05-2021", "17:00", "19:00", "Plus", emp1, "Werk");
        assertEquals(5.0,cli1.showWorkHours(),0.01);
        WorkHour.saveWorkHour("08-05-2021", "16:45", "17:15", "Plus", emp1, "Werk");
        assertEquals(5.5,cli1.showWorkHours(),0.01);
        WorkHour.saveWorkHour("04-05-2021", "08:00", "09:00", "Plus", emp1, "Werk");
        assertEquals(6.5,cli1.showWorkHours(),0.01);
        WorkHour.saveWorkHour("09-05-2021", "02:00", "02:15", "Plus", emp1, "Werk");
        assertEquals(6.75,cli1.showWorkHours(),0.01);
        WorkHour.saveWorkHour("09-05-2021", "13:00", "14:15", "Plus", emp1, "Werk");
        assertEquals(8.0,cli1.showWorkHours(),0.01);
    }
}