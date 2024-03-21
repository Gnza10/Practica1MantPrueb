package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GrupoTest {
    private Grupo grupo;

    @BeforeEach
    public void setUp() throws ClubException {
        try {
            grupo = new Grupo("G1", "Futbol", 10, 5, 100.0);
        } catch (ClubException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing creating a Group")
    public void testGroup() {
        try {
            Grupo grupoTest = new Grupo("G1", "Futbol", 10, 5, 100.0);

            assertEquals("(G1 - Futbol - 100.0 euros - P:10 - M:5)", grupoTest.toString());
        } catch (ClubException e) {

            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Testing getCodigo()")
    public void test_GetCodigo_ReturnsG1() {
        try {
            Grupo grupoTest = new Grupo("G1", "Futbol", 10, 5, 100.0);

            assertEquals("G1", grupoTest.getCodigo());
        } catch (ClubException e) {

            e.printStackTrace();
        }

    }

    @ParameterizedTest
    @CsvSource({
            "0, 5, 10.0",
            "-5, 5, 10.0",
            "5, -5, 10.0",
            "5, 5, 0.0",
            "5, 5, -10.0",
            "5, 10, 10.0"
    })
    @DisplayName("Testing creating a Group with invalid data")
    public void test_GroupInvalidData_throwsClubException(int plazas, int matriculados, double tarifa)
            throws ClubException {

        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", plazas, matriculados, tarifa);
        });
    }

    @Test
    @DisplayName("Testing Sufficient Places")
    public void test_SufficientPlaces_returnTrue() {
        try {
            Grupo grupoTest = new Grupo("G1", "Futbol", 10, 5, 100.0);

            assertEquals(5, grupoTest.plazasLibres());
        } catch (ClubException e) {

            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("Testing Limit value for Places")
    public void test_limitValuesPlaces_ReturnTrue() throws ClubException {
        Grupo grupoTest = new Grupo("G1", "Futbol", 10, 10, 100.0);

        int plazasLib = grupoTest.plazasLibres();

        assertEquals(plazasLib, 0);
    }

    @Test
    @DisplayName("Testing updatePlaces method")
    public void test_UpdatePlaces_ReturnTrue() throws ClubException {
        grupo.actualizarPlazas(35);

        int plazasLib = grupo.plazasLibres();

        assertEquals(30, plazasLib);
    }

    @Test
    @DisplayName("Testing updatePlaces method with invalid data")
    public void test_updateLessPlaces_throwsClubException() {
        int plazas = grupo.getMatriculados() - 1;

        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(plazas); // Valor menos que matriculados
        });

    }

    @Test
    @DisplayName("Testing updatePlaces method with invalid data")
    void test_updateNegativePlaces_throwsClubException() {
        int plazas = -1;

        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(plazas); // Valor negativo
        });
    }

    @Test
    @DisplayName("Testing enroll method")
    public void testEnroll() throws ClubException {
        grupo.matricular(1);

        int matriculados = grupo.getMatriculados();

        assertEquals(6, matriculados);
    }

    @Test
    @DisplayName("Testing enroll method with invalid data")
    public void testEnrollInvalidData() throws ClubException {
        int matriculados = grupo.getPlazas() + 1;

        assertThrows(ClubException.class, () -> {
            grupo.matricular(matriculados); // Mas que plazas disponibles
        });

    }

    @Test
    @DisplayName("Testing enroll method with invalid data")
    void testEnrollNegative_throwsClubException() {
        int matriculados = -1;

        assertThrows(ClubException.class, () -> {
            grupo.matricular(matriculados); // Valor negativo
        });
    }

    @Test
    @DisplayName("Testing toString method")
    public void testToString() {
        assertEquals("(G1 - Futbol - 100.0 euros - P:10 - M:5)", grupo.toString());
    }

    @Test
    @DisplayName("Testing equals method with equal groups")
    public void testEquals_EqualGroups() throws ClubException {
        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 5, 100.0);

        assertTrue(grupo.equals(grupo2)); // Deben ser iguales
    }

    @Test
    @DisplayName("Testing equals method with different groups")
    public void testEquals_DifferentGroups() throws ClubException {

        Grupo grupo2 = new Grupo("G2", "Baloncesto", 15, 7, 150.0);
        assertFalse(grupo.equals(grupo2)); // Deben ser diferentes
    }

    @Test
    @DisplayName("Testing equals method with null")
    public void testEquals_Null() throws ClubException {
        Grupo test = null;
        assertFalse(grupo.equals(test)); // Debe ser falso porque no es igual a null
    }

    @Test
    @DisplayName("Testing equals method with the same instance")
    public void testEquals_SameInstance() throws ClubException {
        assertTrue(grupo.equals(grupo)); // Debe ser verdadero porque es la misma instancia
    }

    @Test
    @DisplayName("Testing equals method with different class")
    public void testEquals_DifferentClass() throws ClubException {

        assertFalse(grupo.equals(new Object())); // Debe ser falso porque no es igual a un objeto de una clase
                                                 // diferente
    }

    @Test
    @DisplayName("Testing equals method with different group codes")
    public void testEquals_DifferentGroupCodes() throws ClubException {

        Grupo grupo2 = new Grupo("G2", "Futbol", 10, 5, 100.0);
        assertFalse(grupo.equals(grupo2)); // Deben ser diferentes debido a diferentes códigos de grupo
    }

    @Test
    @DisplayName("Testing equals method with different activity names")
    public void testEquals_DifferentActivityNames() throws ClubException {

        Grupo grupo2 = new Grupo("G1", "Baloncesto", 10, 5, 100.0);
        assertFalse(grupo.equals(grupo2)); // Deben ser diferentes debido a diferentes nombres de actividad
    }

    @Test
    @DisplayName("Testing equals method with different number of seats")
    public void testEquals_DifferentNumberOfSeats() throws ClubException {

        Grupo grupo2 = new Grupo("G1", "Futbol", 15, 5, 100.0);
        assertTrue(grupo.equals(grupo2)); // Deben ser iguales debido a mismo codigo y nombre
    }

    @Test
    @DisplayName("Testing equals method with different number of enrolled")
    public void testEquals_DifferentNumberOfEnrolled() throws ClubException {

        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 10, 100.0);
        assertTrue(grupo.equals(grupo2)); // Deben ser iguales debido a mismo codigo y nombre
    }

    @Test
    @DisplayName("Testing hashCode method")
    public void testHashCode() throws ClubException {
        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 5, 100.0);
        assertEquals(grupo.hashCode(), grupo2.hashCode());
    }
}
