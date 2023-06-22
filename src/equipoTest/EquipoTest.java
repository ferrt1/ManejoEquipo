package equipoTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    private static Incompatibilidad incompatibilidad;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		persona1 = new Persona("John Doe", "Programador", 4);
        manejoEquipo = new ManejoEquipo();
        persona2 = new Persona("Jane Smith", "Diseñador", 5);
        rol1 = new Rol("Programador", 2);
        rol2 = new Rol("Diseñador", 3);
        incompatibilidad = new Incompatibilidad(persona1, persona2);
}

	@Test
    public void testAddPersona() {
		
		manejoEquipo.agregarPersona(persona1);
        assertEquals(1, manejoEquipo.getPersonas().size());
        assertTrue(manejoEquipo.getPersonas().contains(persona1));
    }
 
 @Test
    public void testAddRol() {
	 	manejoEquipo = new ManejoEquipo();
        manejoEquipo.agregarRol(rol1);
        assertEquals(1, manejoEquipo.getRoles().size());
        assertTrue(manejoEquipo.getRoles().contains(rol1));
    }

 @Test
 public void testAddIncompatibilidad() {
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
    public void testAddRolMultipleTimes() {
        final ManejoEquipo manejoEquipo2;
        manejoEquipo2 = new ManejoEquipo();
        manejoEquipo2.agregarRol(rol1);
        manejoEquipo2.agregarRol(rol1);
        assertEquals(2, manejoEquipo2.getRoles().size());
    }
    
    @Test
    public void testAddIncompatibilidadMultipleTimes() {
        Incompatibilidad incompatibilidad = new Incompatibilidad(persona1, persona2);
        manejoEquipo.agregarIncompatibilidad(incompatibilidad);
        manejoEquipo.agregarIncompatibilidad(incompatibilidad);
        assertEquals(2, manejoEquipo.getIncompatibilidades().size());
    }

    

//     @Test
//    public void testMostrarPersonas() {
//    	 
//    	  final ManejoEquipo manejoEquipo2;
//          manejoEquipo2 = new ManejoEquipo();
//    	 
//        manejoEquipo2.agregarPersona(persona1);
//        manejoEquipo2.agregarPersona(persona2);
//        String expectedOutput = "Nombre: John Doe, Rol: Programador, Rating: 4\n" +
//                                "Nombre: Jane Smith, Rol: Diseñador, Rating: 5\n";
//        assertEquals(expectedOutput, getConsoleOutput(manejoEquipo2::mostrarPersonas));
//    }
//
//    @Test
//    public void testMostrarRoles() {
//        manejoEquipo.agregarRol(rol1);
//        manejoEquipo.agregarRol(rol2);
//        String expectedOutput = "Rol: Programador, Cantidad necesaria: 2\n" +
//                "Rol: Diseñador, Cantidad necesaria: 3";
//
//        assertEquals(expectedOutput, getConsoleOutput(manejoEquipo::mostrarRoles));
//    }
//    
//    private String getConsoleOutput(Runnable runnable) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(outputStream);
//        PrintStream originalPrintStream = System.out;
//        System.setOut(printStream);
//        runnable.run();
//        System.out.flush();
//        System.setOut(originalPrintStream);
//        return outputStream.toString();
//    }



}
