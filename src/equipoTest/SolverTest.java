package equipoTest;

import org.junit.Test;

import equipo.*;
import personas.*;
import static org.junit.Assert.*;
import java.util.List;

public class SolverTest {

	@Test
	public void testEligePersonaConMayorRating() {
		ManejoEquipo manejoEquipo = new ManejoEquipo();

		Persona jefe0 = new Persona("Fer", "Líder de proyecto", 3);
		Persona jefe1 = new Persona("John", "Líder de proyecto", 4);

		Rol r1 = new Rol("Líder de proyecto", 1);

		manejoEquipo.agregarPersona(jefe0);
		manejoEquipo.agregarPersona(jefe1);
		manejoEquipo.agregarRol(r1);

		Solver solver = new Solver(manejoEquipo);
		solver.start();

		try {
			solver.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<Persona> solucion = solver.getSolucion();
		assertNotNull(solucion);
		assertEquals(1, solucion.size());
		assertEquals(jefe1, solucion.get(0));
	}

	@Test
	public void testEvitaIncompatibilidades() {
		ManejoEquipo manejoEquipo = new ManejoEquipo();

		Persona jefe0 = new Persona("Fer", "Líder de proyecto", 3);
		Persona jefe1 = new Persona("John", "Líder de proyecto", 4);
		Persona arquitecto = new Persona("Sally", "Arquitecto", 5);
		Persona programador = new Persona("Maxi", "Programador", 4);

		Rol r1 = new Rol("Líder de proyecto", 1);
		Rol r2 = new Rol("Arquitecto", 1);
		Rol r3 = new Rol("Programador", 1);

		manejoEquipo.agregarPersona(jefe0);
		manejoEquipo.agregarPersona(jefe1);
		manejoEquipo.agregarPersona(arquitecto);
		manejoEquipo.agregarPersona(programador);
		manejoEquipo.agregarRol(r1);
		manejoEquipo.agregarRol(r2);
		manejoEquipo.agregarRol(r3);

		Incompatibilidad i1 = new Incompatibilidad(jefe1, arquitecto);
		manejoEquipo.agregarIncompatibilidad(i1);

		Solver solver = new Solver(manejoEquipo);
		solver.start();

		try {
			solver.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Persona> solucion = solver.getSolucion();
		assertNotNull(solucion);
		assertFalse(solucion.isEmpty());
		assertEquals(3, solucion.size());

		assertTrue(solucion.contains(jefe0));
		assertFalse(solucion.contains(jefe1));
		assertTrue(solucion.contains(arquitecto));
		assertTrue(solucion.contains(programador));
	}
	
	@Test
	public void testIncompatibilidadNoEncuentraSolucion() {
		ManejoEquipo manejoEquipo = new ManejoEquipo();

		Persona jefe0 = new Persona("Fer", "Líder de proyecto", 3);
		Persona jefe1 = new Persona("John", "Líder de proyecto", 4);
		Persona arquitecto = new Persona("Sally", "Arquitecto", 5);
		Persona programador = new Persona("Maxi", "Programador", 4);

		Rol r1 = new Rol("Líder de proyecto", 1);
		Rol r2 = new Rol("Arquitecto", 1);
		Rol r3 = new Rol("Programador", 1);

		manejoEquipo.agregarPersona(jefe0);
		manejoEquipo.agregarPersona(jefe1);
		manejoEquipo.agregarPersona(arquitecto);
		manejoEquipo.agregarPersona(programador);
		manejoEquipo.agregarRol(r1);
		manejoEquipo.agregarRol(r2);
		manejoEquipo.agregarRol(r3);

		Incompatibilidad i1 = new Incompatibilidad(jefe1, arquitecto);
		Incompatibilidad i2 = new Incompatibilidad(jefe0, arquitecto);

		manejoEquipo.agregarIncompatibilidad(i1);
		manejoEquipo.agregarIncompatibilidad(i2);

		Solver solver = new Solver(manejoEquipo);
		solver.start();

		try {
			solver.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Persona> solucion = solver.getSolucion();
		assertTrue(solucion.isEmpty());
	}
	
	@Test
	public void testMejorEquipoCompleto() {
		ManejoEquipo manejoEquipo = new ManejoEquipo();
		 // Crear personas
        Persona p0 = new Persona("Fer", "Líder de proyecto", 5);
        Persona p1 = new Persona("John", "Líder de proyecto", 4);
        Persona p2 = new Persona("Sally", "Arquitecto", 4);
        Persona p3 = new Persona("Sol", "Programador", 3);
        Persona p4 = new Persona("Amy", "Tester", 4);
        Persona p5 = new Persona("Mike", "Tester", 2);
        Persona p6 = new Persona("Matias", "Tester", 4);
        Persona p7 = new Persona("Jorge", "Tester", 5);
        Persona p8 = new Persona("Graciela", "Programador", 4);
        Persona p9 = new Persona("Dave", "Programador", 4);
        
    	Rol r1 = new Rol("Líder de proyecto", 1);
		Rol r2 = new Rol("Arquitecto", 1);
		Rol r3 = new Rol("Tester", 2);
		Rol r4 = new Rol("Programador", 2);
		
		
		manejoEquipo.agregarPersona(p0);
		manejoEquipo.agregarPersona(p1);
		manejoEquipo.agregarPersona(p2);
		manejoEquipo.agregarPersona(p3);
		manejoEquipo.agregarPersona(p4);
		manejoEquipo.agregarPersona(p5);
		manejoEquipo.agregarPersona(p6);
		manejoEquipo.agregarPersona(p7);
		manejoEquipo.agregarPersona(p8);
		manejoEquipo.agregarPersona(p9);
		
		manejoEquipo.agregarRol(r1);
		manejoEquipo.agregarRol(r2);
		manejoEquipo.agregarRol(r3);
		manejoEquipo.agregarRol(r4);

		Incompatibilidad i1 = new Incompatibilidad(p4, p3);
		Incompatibilidad i2 = new Incompatibilidad(p0, p7);

		manejoEquipo.agregarIncompatibilidad(i1);
		manejoEquipo.agregarIncompatibilidad(i2);
		
		Solver solver = new Solver(manejoEquipo);
		solver.start();

		try {
			solver.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Persona> solucion = solver.getSolucion();
		
	
		
		assertNotNull(solucion);
		assertFalse(solucion.isEmpty());
		assertEquals(6, solucion.size());

	
		
		assertTrue(solucion.contains(p0));
		assertTrue(solucion.contains(p2));
		assertTrue(solucion.contains(p8));
		assertTrue(solucion.contains(p9));
		assertTrue(solucion.contains(p6));
		assertTrue(solucion.contains(p4));
	
	
	}
}



