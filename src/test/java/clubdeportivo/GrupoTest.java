package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrupoTest {
    private Grupo grupo;

    @BeforeEach
    public void setUp() throws ClubException {
        grupo = new Grupo("G1", "Futbol", 10, 5, 100.0);
    }

    @Test
    public void testConstructor() {
        assertEquals("G1", grupo.getCodigo());
        assertEquals("Futbol", grupo.getActividad());
        assertEquals(10, grupo.getPlazas());
        assertEquals(5, grupo.getMatriculados());
        assertEquals(100.0, grupo.getTarifa(), 0.01);
    }

    @Test
    public void testConstructorInvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            new Grupo("G2", "Actividad2", -1, 5, 100.0);
            new Grupo("G2", "Actividad2", 0, -1, 0);
            new Grupo("G2", "Actividad2", 10, 5, -1);
        });

    }

    @Test
    public void SufficientPlaces() {
        assertEquals(5, grupo.plazasLibres());

    }

    @Test
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
    public void testUpadtePlacesInvalidData() throws ClubException {
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(4); // esta por debajo del numero de matriculados
            grupo.actualizarPlazas(-1);
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
            grupo.matricular(20);
            grupo.matricular(-1);
        });

    }

    @Test
    public void testToString() {
        assertEquals("(G1 - Futbol - 100.0 euros - P:10 - M:5)", grupo.toString());
    }

    @Test
    public void testEquals() throws ClubException {
        Grupo grupo2 = new Grupo("g1", "futbol", 10, 5, 100.0);
        assertTrue(grupo.equals(grupo2));
    }

    @Test
    public void testNoEqual() throws ClubException {
        Grupo grupo2 = new Grupo("g2", "FUTBOL", 5, 0, 100.0);
        assertFalse(grupo.equals(grupo2));
    }

    @Test
    public void testNoEqualGroup() throws ClubException {
        assertFalse(grupo.equals(new Object()));
    }

    @Test
    public void testHashCode() throws ClubException {
        Grupo grupo2 = new Grupo("G1", "Futbol", 10, 5, 100.0);
        assertEquals(grupo.hashCode(), grupo2.hashCode());
    }
}
