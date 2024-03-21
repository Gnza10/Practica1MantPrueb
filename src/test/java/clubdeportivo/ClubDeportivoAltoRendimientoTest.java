/*Realizado por:
 * David Zarzavilla Borrego
 * Gonzalo Muñoz Rubio
 */
package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ClubDeportivoAltoRendimientoTest {
    @Test
    @DisplayName("Testing creating a high performance club")
    public void test_CreatingHighPerformanceClub() {
        try {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);

            assertEquals("TestClub", club.toString().substring(0, 8));
        } catch (ClubException e) {
            fail("Exception should not be thrown");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "-20, 10.0",
            "20, -10.0",
            "0, 100.0",
            "20, 0"
    })
    @DisplayName("Testing creating a high performance club with invalid data")
    public void test_CreatingHighPerformanceClub_InvalidData_ThrowsClubException(int max, double inc)
            throws ClubException {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", max, inc); // Negative incremento
        });

    }

    @Test
    @DisplayName("Testing creating a high performance club")
    public void test_CreatingHighPerformanceClub2Constructor() {
        try {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 20, 10.0);

            assertEquals("TestClub", club.toString().substring(0, 8));
        } catch (ClubException e) {
            fail("Exception should not be thrown");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "-20, 10.0",
            "20, -10.0",
            "0, 100.0",
            "20, 0"
    })
    @DisplayName("Testing creating a high performance club with invalid data")
    public void test_CreatingHighPerformanceClub_InvalidData_ThrowsClubException2Constructor(int max, double inc)
            throws ClubException {
        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento("TestClub", 20, max, inc); // Negative incremento
        });

    }

    @Test
    @DisplayName("Testing adding activity to high performance club")
    public void test_AddingActivityToHighPerformanceClub_ReturnsCorrectToString() {
        try {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] activityData = { "G1", "Futbol", "10", "5", "100.0" };

            club.anyadirActividad(activityData);

            assertTrue(club.toString().contains("Futbol")); // Check if the activity was added using toString()
        } catch (ClubException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    @DisplayName("Testing adding activity to high performance club with too much spots")
    void test_AddingActivityToHighPerformanceClub_TooManySpots_ReturnsSpotsEqualToCapacity() {
        try {
            ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] activityData = { "G1", "Futbol", "30", "5", "100.0" };

            club.anyadirActividad(activityData);

            assertEquals("TestClub --> [ (G1 - Futbol - 100.0 euros - P:20 - M:5) ]", club.toString()); // Check if the
                                                                                                        // activity was
                                                                                                        // added with
                                                                                                        // max spots
        } catch (ClubException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    @DisplayName("Testing adding activity with less data to high performance club")
    public void test_AddingLessArgumentsToActivity_throwsClubException() {
        ClubDeportivoAltoRendimiento club;
        try {
            club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] invalidActivityData = { "G1", "Futbol", "10", "5" }; // Missing tarifa

            assertThrows(ClubException.class, () -> {
                club.anyadirActividad(invalidActivityData);
            });
        } catch (ClubException e) {

            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing adding activity with invalid data to high performance club")
    void test_AddingActivityWithInvalidData_throwsClubException() {
        ClubDeportivoAltoRendimiento club;
        try {
            club = new ClubDeportivoAltoRendimiento("TestClub", 20, 10.0);
            String[] invalidActivityData = { "G1", "Futbol", "h", "5", "100.0" }; // Negative spots

            assertThrows(ClubException.class, () -> {
                club.anyadirActividad(invalidActivityData);
            });
        } catch (ClubException e) {

            e.printStackTrace();
        }
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
