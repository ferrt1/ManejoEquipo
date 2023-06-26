package equipoTest;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;

import equipo.*;
import personas.*;

public class EquipoTest {
	private static Persona persona1;
	private static Persona persona2;
    private static ManejoEquipo manejoEquipo;
    private static Rol rol1;
    private static Rol rol2;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		persona1 = new Persona("John Doe", "Programador", 4);
        manejoEquipo = new ManejoEquipo();
        persona2 = new Persona("Jane Smith", "Diseñador", 5);
        rol1 = new Rol("Programador", 2);
        rol2 = new Rol("Diseñador", 3);
}

	@Test
    public void testAddPersona() {
		
		manejoEquipo.agregarPersona(persona1);
        assertEquals(1, manejoEquipo.getPersonas().size());
        assertTrue(manejoEquipo.getPersonas().contains(persona1));
    }
 
 @Test
    public void testAgregarRol() {
	 	manejoEquipo = new ManejoEquipo();
        manejoEquipo.agregarRol(rol1);
        assertEquals(1, manejoEquipo.getRoles().size());
        assertTrue(manejoEquipo.getRoles().contains(rol1));
    }

 @Test
 public void testAgregarIncompatibilidad() {
     final ManejoEquipo manejoEquipo1;
     final Incompatibilidad incompatibilidad1 = null;
     
     
     manejoEquipo1 = new ManejoEquipo();
     manejoEquipo1.agregarIncompatibilidad(incompatibilidad1);
     assertEquals(1, manejoEquipo1.getIncompatibilidades().size());
     assertTrue(manejoEquipo1.getIncompatibilidades().contains(incompatibilidad1));
 }
    
    @Test
    public void testNoIncompatibilidadEntrePersonas() {
        assertFalse(manejoEquipo.getIncompatibilidades().contains(new Incompatibilidad(persona1, persona2)));
    }
    
    @Test
    public void testAddRolMultiplesVeces() {
        final ManejoEquipo manejoEquipo2;
        manejoEquipo2 = new ManejoEquipo();
        manejoEquipo2.agregarRol(rol1);
        manejoEquipo2.agregarRol(rol2);
        assertEquals(2, manejoEquipo2.getRoles().size());
    }
    
    @Test
    public void testAddIncompatibilidadMultiplesVeces() {
        Incompatibilidad incompatibilidad = new Incompatibilidad(persona1, persona2);
        manejoEquipo.agregarIncompatibilidad(incompatibilidad);
        manejoEquipo.agregarIncompatibilidad(incompatibilidad);
        assertEquals(2, manejoEquipo.getIncompatibilidades().size());
    }



}
