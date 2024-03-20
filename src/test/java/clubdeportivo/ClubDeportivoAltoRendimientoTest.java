package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    @Test
    @DisplayName("Testing creating a high performance club")
    public void testCreatingHighPerformanceClub() {
        try {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            assertEquals("TestClub", club.toString().substring(0, 8));
            assertNotNull(club); // Check if the object is not null
        } catch (ClubException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    @DisplayName("Testing creating a high performance club with invalid data")
    public void testCreatingHighPerformanceClub_InvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", 20, -10.0); // Negative incremento
        });

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", -20, 10.0); // Negative maximo
        });

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", -20, -10.0); // Negative maximo and incremento
        });

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", 0, 10.0); // Zero maximo
        });

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", 20, 0); // Zero incremento
        });

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", 0, 0); // Zero incremento and maximo
        });
    }

    @Test
    @DisplayName("Testing adding activity to high performance club")
    public void testAddingActivityToHighPerformanceClub() {
        try {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] activityData = { "G1", "Futbol", "10", "5", "100.0" };
            club.anyadirActividad(activityData);
            assertTrue(club.toString().contains("Futbol")); // Check if the activity was added using toString()
            assertTrue(club.toString().contains("G1"));
        } catch (ClubException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    @DisplayName("Testing adding activity with invalid data to high performance club")
    public void testAddingInvalidActivityToHighPerformanceClub() throws ClubException {
        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] invalidActivityData = { "G1", "Futbol", "10", "5" }; // Missing tarifa
            club.anyadirActividad(invalidActivityData);
        });

        assertThrows(ClubException.class, () -> {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] invalidActivityData = { "G1", "Futbol", "10", "a", "100.0" }; // Invalid matriculados
            club.anyadirActividad(invalidActivityData);
        });
    }

    @Test
    @DisplayName("Testing club income calculation")
    public void testClubIncomeCalculation() {
        try {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] activityData = { "G1", "Futbol", "10", "5", "100.0" };
            club.anyadirActividad(activityData);
            assertTrue(club.ingresos() > 0); // Check if income is greater than 0
        } catch (ClubException e) {
            fail("Exception should not be thrown");
        }
    }
}
