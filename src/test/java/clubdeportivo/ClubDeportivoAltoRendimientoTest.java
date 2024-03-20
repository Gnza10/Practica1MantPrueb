package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoAltoRendimientoTest {
    private ClubDeportivoAltoRendimiento club;

    @BeforeEach
    public void setUp() throws ClubException {
        try {
            club = new ClubDeportivoAltoRendimiento("TestClub", 5, 10, 20.0);
        } catch (ClubException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing creating a High Performance Club")
    public void testCreatingHighPerformanceClub() {
        assertEquals("TestClub", club.toString().substring(0, 8));
        // Aseguramos que la representación del club en forma de cadena contiene
        // "TestClub"
        // Así verificamos que el nombre del club se haya establecido correctamente
    }

    @Test
    @DisplayName("Testing creating a High Performance Club with zero or negative maximo value")
    public void testCreatingHighPerformanceClubWithZeroOrNegativeMaximoValue() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("TestClub", 0, 10.0));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("TestClub", -5, 10.0));
    }

    @Test
    @DisplayName("Testing creating a High Performance Club with zero or negative incremento value")
    public void testCreatingHighPerformanceClubWithZeroOrNegativeIncrementoValue() {
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("TestClub", 5, 0));
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento("TestClub", 5, -10.0));
    }

    @Test
    @DisplayName("Testing adding activity with maximo personas grupo")
    public void testAddingActivityWithMaximoPersonasGrupo() {
        String[] activityData = { "1", "Activity", "15", "5", "10.0" };
        try {
            club.anyadirActividad(activityData);
            assertEquals(5, club.plazasLibres("Activity"));
        } catch (ClubException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing adding activity with more than maximo personas grupo")
    public void testAddingActivityWithMoreThanMaximoPersonasGrupo() {
        String[] activityData = { "1", "Activity", "20", "5", "10.0" };
        try {
            club.anyadirActividad(activityData);
            assertEquals(5, club.plazasLibres("Activity"));
        } catch (ClubException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing adding activity with incorrect data")
    public void testAddingActivityWithIncorrectData() {
        String[] activityData = { "1", "Activity" };
        assertThrows(ClubException.class, () -> club.anyadirActividad(activityData));
    }

    @Test
    @DisplayName("Testing ingresos")
    public void testIngresos() {
        String[] activityData = { "1", "Activity", "5", "5", "10.0" };
        try {
            club.anyadirActividad(activityData);
        } catch (ClubException e) {
            e.printStackTrace();
        }
        assertEquals(60.0, club.ingresos());
    }
}
