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
    void test_CreatingNewGroup_IncreasesGroupSize(){
        String[] groupData = {"1", "ActividadTest", "10", "0", "10.0"};

        try {
            club.anyadirActividad(groupData);
        } catch (ClubException e) {
            e.printStackTrace();
        }
        assertEquals(club.toString(), "TestClub --> [ (1 - ActividadTest - 10.0 euros - P:10 - M:0) ]");
    }

    @Test
    @DisplayName("Testing adding a group with negative numbers")
    void test_CreatingNewGroup_IncorrectValues_ThowsClubException(){
        String[] groupData = {"1", "ActividadTest", "10", "-1", "10.0"};

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(groupData);
        });
    }

    @Test
    @DisplayName("Testing adding a null group")
    void test_NullGroup_ThrowsClubException(){
        Grupo grupo = null;

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(grupo);
        });
    }

    @Test
    @DisplayName("Testing the income")
    void test_Income_Returns500(){
        try{
            String [] grupo1 = {"123A","Kizomba","10","10","25.0"};
            Grupo pilates = new Grupo("456B","Pilates",8,5,50.0);
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
    void test_ToString_ReturnsString(){
        try{
            String [] grupo1 = {"123A","Kizomba","10","10","25.0"};
            Grupo pilates = new Grupo("456B","Pilates",8,5,50.0);
            club.anyadirActividad(grupo1);
		    club.anyadirActividad(pilates);
        } catch (ClubException e) {
            e.printStackTrace();
        }
        
        String toString = club.toString();

        assertEquals(toString, "TestClub --> [ (123A - Kizomba - 25.0 euros - P:10 - M:10), (456B - Pilates - 50.0 euros - P:8 - M:5) ]");
    }
}
