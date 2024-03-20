package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertEquals("G1", grupo.getCodigo());
        assertEquals("Futbol", grupo.getActividad());
        assertEquals(10, grupo.getPlazas());
        assertEquals(5, grupo.getMatriculados());
        assertEquals(100.0, grupo.getTarifa(), 0.01);
    }

    @Test
    @DisplayName("Testing creating a Group with invalid data")
    public void testGroupInvalidData() throws ClubException {
        // Caso 1: nplazas <= 0
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", 0, 5, 100.0);
        });

        // Caso 2: matriculados < 0
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", 10, -1, 100.0);
        });

        // Caso 3: tarifa <= 0
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", 10, 5, 0);
        });

        // Caso 4: matriculados > nplazas
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", 10, 15, 100.0);
        });
    }

    @Test
    @DisplayName("Testing Sufficient Places")
    public void testSufficientPlaces() {
        assertEquals(5, grupo.plazasLibres());
    }

    @Test
    @DisplayName("Testing Insufficient Places")
    public void testInsufficientPlaces() throws ClubException {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo("G1", "Futbol", 10, 15, 100.0);
            assertEquals(0, grupo.plazasLibres());
        });
    }

    @Test
    @DisplayName("Testing updatePlaces method")
    public void testUpdatePlaces() throws ClubException {
        grupo.actualizarPlazas(30);
        assertEquals(30, grupo.getPlazas());
    }

    @Test
    @DisplayName("Testing updatePlaces method with invalid data")
    public void testUpdatePlacesInvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(4); // Value less than matriculados
        });
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(-1); // Negative value
        });
    }

    @Test
    @DisplayName("Testing enroll method")
    public void testEnroll() throws ClubException {
        grupo.matricular(1);
        assertEquals(6, grupo.getMatriculados());
    }

    @Test
    @DisplayName("Testing enroll method with invalid data")
    public void testEnrollInvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            grupo.matricular(20); // More than available places
        });
        assertThrows(ClubException.class, () -> {
            grupo.matricular(-1); // Negative value
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

        assertFalse(grupo.equals(null)); // Debe ser falso porque no es igual a null
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
        assertTrue(grupo.equals(grupo2)); // Deben ser diferentes debido a diferentes números de plazas
    }

    @Test
    @DisplayName("Testing equals method with different number of enrolled")
    public void testEquals_DifferentNumberOfEnrolled() throws ClubException {

        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 10, 100.0);
        assertTrue(grupo.equals(grupo2)); // Deben ser diferentes debido a diferentes números de inscritos
    }

    @Test
    @DisplayName("Testing hashCode method")
    public void testHashCode() throws ClubException {
        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 5, 100.0);
        assertEquals(grupo.hashCode(), grupo2.hashCode());
    }
}
