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
    @DisplayName("Testing creating a Group")
    public void testGroupInvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", -1, 5, 100.0); // Invalid nplazas
        });
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", 0, -1, 0); // Invalid matriculados
        });
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", 10, 5, -1); // Invalid tarifa
        });
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", 10, 15, 100.0); // matriculados > nplazas
        });
    }

    @Test
    @DisplayName("Testing Sufficient Places")
    public void SufficientPlaces() {
        assertEquals(5, grupo.plazasLibres());

    }

    @Test
    @DisplayName("Testing Insufficient Places")
    public void InsufficientPlaces() throws ClubException {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo("G1", "Futbol", 10, 15, 100.0);
            assertEquals(0, grupo.plazasLibres());
        });

    }

    @Test
    public void testUpdatePlaces() throws ClubException {
        grupo.actualizarPlazas(30);
        assertEquals(30, grupo.getPlazas());
    }

    @Test
    public void testUpdatePlacesInvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(4); // Value less than matriculados
        });
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(-1); // Negative value
        });

    }

    @Test
    public void testEnroll() throws ClubException {
        grupo.matricular(1);
        assertEquals(6, grupo.getMatriculados());
    }

    @Test
    public void testEnrollInvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            grupo.matricular(20); // More than available places
        });
        assertThrows(ClubException.class, () -> {
            grupo.matricular(-1); // Negative value
        });

    }

    @Test
    public void testToString() {
        assertEquals("(G1 - Futbol - 100.0 euros - P:10 - M:5)", grupo.toString());
    }

    @Test
    public void testEquals() throws ClubException {
        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 5, 100.0);
        assertTrue(grupo.equals(grupo2)); // Igualdad con otro objeto Grupo
        assertFalse(grupo.equals(new Object())); // Desigualdad con otro tipo de objeto
        assertTrue(grupo.equals(grupo)); // Reflexividad
        assertTrue(grupo.equals(grupo2) == grupo2.equals(grupo)); // Simetría
        assertTrue(grupo.equals(grupo2) && grupo2.equals(grupo)); // Transitividad
        assertEquals(grupo.equals(grupo2), grupo.equals(grupo2)); // Consistencia
        assertFalse(grupo.equals(null)); // Prueba de nullabilidad
    }

    @Test
    void testNoEqual() throws ClubException {
        Grupo grupo2 = new Grupo("g2", "FUTBOL", 5, 0, 100);
        assertNotEquals(grupo, grupo2);
        assertFalse(grupo.equals(grupo2));
    }

    //
    // public void testNoEqualGroup() throws ClubException {
    // assertFalse(grupo.equals(new Object()));
    // }

    @Test
    public void testHashCode() throws ClubException {
        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 5, 100.0);
        assertEquals(grupo.hashCode(), grupo2.hashCode());
    }
}
