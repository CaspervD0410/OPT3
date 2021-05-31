package sample;

import org.junit.jupiter.api.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WorkHourTest {
    Employee emp1 = new Employee("Hank","123","Technicus",40,false,false);
    Employee emp2 = new Employee("John","password","Administratie",30,false,false);
    Employee emp3 = new Employee("Marc","password123","Administratie",40,true,false);
    Employee emp4 = new Employee("Dean","P@ssw0rd!","Technicus",40,false,true);
    Client cli1 = new Client("Plus");
    Client vda = new Client("VDA");

    @Order(1)
    @Test
    public void showWorkHours() {
        //Bij deze test moet er naast de uitkomst van de Assert ook gekeken worden naar de uitgeprinte regels
        WorkHour.saveWorkHour("08-05-2021", "08:30", "10:30", "Plus", emp1, "Werk");
        assertEquals(2.0,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("03-05-2021", "16:00", "17:00", "Plus", emp1, "Werk");
        assertEquals(3.0,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("05-05-2021", "17:00", "19:00", "Plus", emp1, "Werk");
        assertEquals(5.0,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("08-05-2021", "16:45", "17:15", "Plus", emp1, "Werk");
        assertEquals(5.5,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("04-05-2021", "08:00", "09:00", "Plus", emp1, "Werk");
        assertEquals(6.5,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("09-05-2021", "02:00", "02:15", "Plus", emp1, "Werk");
        assertEquals(6.75,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("09-05-2021", "13:00", "14:15", "Plus", emp1, "Werk");
        assertEquals(8.0,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("03-05-2021", "08:31", "16:59", "Plus", emp1, "Werk");
        assertEquals(16.47,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("05-05-2021", "08:29", "08:31", "Plus", emp1, "Werk");
        assertEquals(16.50,cli1.showWorkHours(),0.1);
        WorkHour.saveWorkHour("07-05-2021", "16:59", "17:01", "Plus", emp1, "Werk");
        assertEquals(16.53,cli1.showWorkHours(),0.1);
    }

    @Order(2)
    @Test
    public void SaveWorkHour() {
        assertTrue(WorkHour.saveWorkHour("06-05-2021", "10:00", "15:00", "Plus", emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("06-05-2021", "10:00", "15:00", "glopr", emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("05-12-2028", "10:00", "15:00", "Plus", emp1, "Werk"));
        assertFalse(WorkHour.saveWorkHour("06-05-2021", "10:00", "08:00", "Plus", emp1, "Werk"));
    }

    @Order(3)
    @Test
    public void checkWorkHoursTest() {
        assertTrue(WorkHour.checkWorkHour("08-05-2021", "08:30", "09:00", "Plus", emp3, "Werk"));
        assertTrue(WorkHour.checkWorkHour("08-05-2021", "09:00", "09:30", "VDA", emp3, "Vrij"));
        assertTrue(WorkHour.checkWorkHour("09:30", "10:00", "VDA", emp3, "Werk"));
        assertTrue(WorkHour.checkWorkHour("08-05-2021", "10:00", "10:30", "VDA", emp1, "Werk"));
        assertFalse(WorkHour.checkWorkHour("10:30", "11:00", "Plus", emp1, "Vrij"));
        assertTrue(WorkHour.checkWorkHour("11:00", "11:30", "VDA", emp1, "Werk"));
        assertTrue(WorkHour.checkWorkHour("08-05-2021", "11:30", "12:00", "VDA", emp2, "Werk"));
        assertFalse(WorkHour.checkWorkHour("08-05-2021","12:00","12:30","Plus",emp2,"Werk"));
        assertTrue(WorkHour.checkWorkHour("12:30","13:00","VDA",emp2,"Vrij"));
    }
}