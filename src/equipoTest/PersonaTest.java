package equipoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;

import personas.Persona;

public class PersonaTest {
    private Persona persona;

    @Before
    public void setUp() {
        persona = new Persona("John Doe", "Developer", 5);
    }

    @Test
    public void testNombre() {
        assertEquals("John Doe", persona.getNombre());
        persona.setNombre("Jane Doe");
        assertEquals("Jane Doe", persona.getNombre());
    }

    @Test
    public void testRol() {
        assertEquals("Developer", persona.getRol());
        persona.setRol("Manager");
        assertEquals("Manager", persona.getRol());
    }

    @Test
    public void testRating() {
        assertEquals(5, persona.getRating());
        persona.setRating(4);
        assertEquals(4, persona.getRating());
    }
    
    @Test
    public void testEquals() {
        Persona samePersona = new Persona("John Doe", "Developer", 5);
        Persona diferentePersona = new Persona("Jane Doe", "Manager", 4);

        assertTrue(persona.equals(samePersona));
        assertFalse(persona.equals(diferentePersona));
    }
}
