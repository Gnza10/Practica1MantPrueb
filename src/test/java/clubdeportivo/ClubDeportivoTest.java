/*Realizado por:
 * David Zarzavilla Borrego
 * Gonzalo Muñoz Rubio
 */
package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    ClubDeportivo club;

    @BeforeEach
    void setUp() {
        try {
            club = new ClubDeportivo("TestClub");
        } catch (ClubException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing creating a club")
    void test_ClubCreation_ReturnsNotNull() {
        int n = 10;

        try {
            club = new ClubDeportivo("TestClub", n);
        } catch (ClubException e) {
            e.printStackTrace();
        }

        assertNotEquals(club, null);
    }

    @Test
    @DisplayName("Testing adding a group with negative numbers")
    void test_CreatingClub_WithNegativeNumbers_ThrowsClubException() {
        int n = -10;

        assertThrows(ClubException.class, () -> {
            club = new ClubDeportivo("TestClub", n);
        });
    }

    @Test
    @DisplayName("Testing adding an activity")
    void test_CreatingNewGroup_ReturnsCorrectToString() {
        String[] groupData = { "1", "ActividadTest", "10", "0", "10.0" };

        try {
            club.anyadirActividad(groupData);
        } catch (ClubException e) {
            e.printStackTrace();
        }
        assertEquals(club.toString(), "TestClub --> [ (1 - ActividadTest - 10.0 euros - P:10 - M:0) ]");
    }

    @Test
    @DisplayName("Testing adding a group with negative numbers")
    void test_CreatingNewGroup_IncorrectValues_ThowsClubException() {
        String[] groupData = { "1", "ActividadTest", "10", "hola", "10.0" };

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(groupData);
        });
    }

    @Test
    @DisplayName("Testing adding an already existing group but with more spots")
    void test_AddingExistingGroupWithMoreSpots_ReturnsCorrectToString() {
        String[] groupData = { "1", "ActividadTest", "10", "0", "10.0" };
        String[] groupData2 = { "1", "ActividadTest", "15", "0", "10.0" };

        try {
            club.anyadirActividad(groupData);
            club.anyadirActividad(groupData2);
        } catch (ClubException e) {
            e.printStackTrace();
        }

        assertEquals("TestClub --> [ (1 - ActividadTest - 10.0 euros - P:15 - M:0) ]", club.toString());
    }

    @Test
    @DisplayName("Testing adding a null group")
    void test_NullGroup_ThrowsClubException() {
        Grupo grupo = null;

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(grupo);
        });
    }

    @Test
    @DisplayName("Testing free spots")
    void test_FreeSpots_Returns10() {
        try {
            Grupo test = new Grupo("123A", "Kizomba", 10, 10, 25.0);
            club.anyadirActividad(test);
            Grupo pilates = new Grupo("456B", "Pilates", 15, 5, 50.0);
            club.anyadirActividad(pilates);
        } catch (ClubException e) {
            e.printStackTrace();
        }

        int n = club.plazasLibres("Pilates");

        assertEquals(n, 10);
    }

    @Test
    @DisplayName("Testing enroll to an activity with enough spots in only one group")
    void test_EnrollToActivityWithEnoughSpots_ReturnsTrue() {
        try {
            Grupo test = new Grupo("123A", "Kizomba", 10, 0, 10);
            club.anyadirActividad(test);

            club.matricular("Kizomba", 5);
        } catch (ClubException e) {
            e.printStackTrace();
        }

        assertEquals("TestClub --> [ (123A - Kizomba - 10.0 euros - P:10 - M:5) ]", club.toString());
    }

    @Test
    @DisplayName("Testing enroll to an activity with not enough spots ")
    void test_EnrollToActivityWithNotEnoughSpots_ThrowsClubException() {
        try {
            Grupo test = new Grupo("123A", "Kizomba", 10, 10, 25.0);
            club.anyadirActividad(test);

        } catch (ClubException e) {
            assertEquals("ERROR: no hay suficientes plazas libres para esa actividad en el club.", e.getMessage());
        }

        assertThrows(ClubException.class, () -> {
            club.matricular("Kizomba", 15);
        });
    }

    @Test
    @DisplayName("Testing enroll to an activity with enough spots in multiple groups")
    void test_EnrollToActivityWithEnoughSpotsInMultipleGroups_ReturnsTrue() {
        try {
            Grupo test1 = new Grupo("1234", "Kizomba", 5, 0, 10);
            Grupo noActividad = new Grupo("1289", "Pilates", 10, 0, 10);
            Grupo test2 = new Grupo("4321", "Kizomba", 5, 0, 10);
            club.anyadirActividad(test1);
            club.anyadirActividad(noActividad);
            club.anyadirActividad(test2);

            club.matricular("Kizomba", 10);

        } catch (ClubException e) {
            e.printStackTrace();
        }

        assertEquals(
                "TestClub --> [ (1234 - Kizomba - 10.0 euros - P:5 - M:5), (1289 - Pilates - 10.0 euros - P:10 - M:0), (4321 - Kizomba - 10.0 euros - P:5 - M:5) ]",
                club.toString());
    }

    @Test
    @DisplayName("Testing the income")
    void test_Income_Returns500() {
        try {
            String[] grupo1 = { "123A", "Kizomba", "10", "10", "25.0" };
            Grupo pilates = new Grupo("456B", "Pilates", 8, 5, 50.0);
            club.anyadirActividad(grupo1);
            club.anyadirActividad(pilates);
        } catch (ClubException e) {
            e.printStackTrace();
        }

        double income = club.ingresos();

        assertEquals(income, 500);
    }

    @Test
    @DisplayName("Testing toString")
    void test_ToString_ReturnsString() {
        try {
            String[] grupo1 = { "123A", "Kizomba", "10", "10", "25.0" };
            Grupo pilates = new Grupo("456B", "Pilates", 8, 5, 50.0);
            club.anyadirActividad(grupo1);
            club.anyadirActividad(pilates);
        } catch (ClubException e) {
            e.printStackTrace();
        }

        String toString = club.toString();

        assertEquals(toString,
                "TestClub --> [ (123A - Kizomba - 25.0 euros - P:10 - M:10), (456B - Pilates - 50.0 euros - P:8 - M:5) ]");
    }
}
