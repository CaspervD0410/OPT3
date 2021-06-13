package sample;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WorkHourTest {
    Employee emp1 = new Technician("Hank","123",40);
    Employee emp2 = new Administration("John","password",30);
    Employee emp3 = new Technician("Marc","password123",40);
    Employee emp4 = new Supervisor("Dean","P@ssw0rd!",40);
    Client cli1 = new Client("Plus");
    Client vda = new Client("VDA");

    //@Order(1)
    //@Test
    //public void showWorkHours() {
    //    Login.getInstance().setLoggedInUser(emp2);
    //    //Bij deze test moet er naast de uitkomst van de Assert ook gekeken worden naar de uitgeprinte regels
    //    WorkHour.checkWorkHour("08-05-2021", "08:30", "10:30", "Plus", "Werk");
    //    assertEquals(2.0,emp2.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("03-05-2021", "16:00", "17:00", "Plus", "Werk");
    //    assertEquals(3.0,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("05-05-2021", "17:00", "19:00", "Plus", "Werk");
    //    assertEquals(5.0,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("08-05-2021", "16:45", "17:15", "Plus", "Werk");
    //    assertEquals(5.5,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("04-05-2021", "08:00", "09:00", "Plus", "Werk");
    //    assertEquals(6.5,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("09-05-2021", "02:00", "02:15", "Plus", "Werk");
    //    assertEquals(6.75,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("09-05-2021", "13:00", "14:15", "Plus", "Werk");
    //    assertEquals(8.0,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("03-05-2021", "08:31", "16:59", "Plus", "Werk");
    //    assertEquals(16.47,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("05-05-2021", "08:29", "08:31", "Plus", "Werk");
    //    assertEquals(16.50,cli1.showWorkHours(),0.1);
    //    WorkHour.checkWorkHour("07-05-2021", "16:59", "17:01", "Plus", "Werk");
    //    assertEquals(16.53,cli1.showWorkHours(),0.1);
    //}

    @Order(2)
    @Test
    public void checkWorkHour() {
        Login.getInstance().setLoggedInUser(emp1);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("06-05-2021", "10:00", "15:00"), "Plus", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("06-05-2021", "10:00", "15:00"), "glopr", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("05-12-2028", "10:00", "15:00"), "Plus", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("06-05-2021", "10:00", "08:00"), "Plus", "Werk"));
    }

    @Order(3)
    @Test
    public void checkWorkHoursTest() {
        Login.getInstance().setLoggedInUser(emp3);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "08:30", "09:00"), "Plus", "Werk"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "09:00", "09:30"), "VDA", "Vrij"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"09:30", "10:00"), "VDA", "Werk"));
        Login.getInstance().setLoggedInUser(emp1);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "10:00", "10:30"), "VDA", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"10:30", "11:00"), "Plus", "Vrij"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"11:00", "11:30"), "VDA", "Werk"));
        Login.getInstance().setLoggedInUser(emp2);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "11:30", "12:00"), "VDA", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021","12:00","12:30"),"Plus","Werk"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"12:30","13:00"),"VDA","Vrij"));
    }
}