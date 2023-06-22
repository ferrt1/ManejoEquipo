package equipoTest;

import org.junit.Before;
import org.junit.Test;

import personas.Rol;

import static org.junit.Assert.*;

public class RolesTest {
    
    private Rol rol;

    @Before
    public void setUp() {
        rol = new Rol("Desarrollador", 5);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Desarrollador", rol.getNombre());
    }

    @Test
    public void testSetNombre() {
        rol.setNombre("Analista");
        assertEquals("Analista", rol.getNombre());
    }
    
    @Test
    public void testEquals() {
        Rol rol2 = new Rol("Desarrollador", 5);
        assertTrue(rol.equals(rol2));
        
        Rol rol3 = new Rol("Desarrollador", 4);
        assertFalse(rol.equals(rol3));
        
        Rol rol4 = new Rol("Analista", 5);
        assertFalse(rol.equals(rol4));
        
        Rol rol5 = new Rol("Analista", 4);
        assertFalse(rol.equals(rol5));
    }

}