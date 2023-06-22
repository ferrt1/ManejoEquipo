package equipo;

import java.util.List;

import personas.*;

public class Main {
	public static void main(String[] args) {

	    // Crear personas
	    Persona jefe0 = new Persona("Fer", "Líder de proyecto", 3);
	    Persona jefe1 = new Persona("John", "Líder de proyecto", 4);
	    
	    Persona arquitecto1 = new Persona("Sally", "Arquitecto", 3);
	    Persona arquitecto2 = new Persona("Rayo", "Arquitecto", 4);
	    Persona arquitecto3 = new Persona("Mate", "Arquitecto", 5);
	    Persona arquitecto4 = new Persona("Doc", "Arquitecto", 1);
	    Persona arquitecto5 = new Persona("Guido", "Arquitecto", 3);
	    Persona arquitecto6 = new Persona("Francesco", "Arquitecto", 5);

	    
	    Persona programador1 = new Persona("Hann", "Programador", 5);
	    Persona programador2 = new Persona("Serpi", "Programador", 4);
	    Persona programador3 = new Persona("Kuta", "Programador", 3);
	    Persona programador4 = new Persona("Pan", "Programador", 2);
	    Persona programador5 = new Persona("Haff", "Programador", 3);
	    Persona programador6 = new Persona("Maxi", "Programador", 5);

	    
	    Persona tester0 = new Persona("Amy", "Tester", 4);
	    Persona tester1 = new Persona("Coscu", "Tester", 1);
	    Persona tester2 = new Persona("Momo", "Tester", 3);
	    Persona tester3 = new Persona("Frankkaster", "Tester", 5);
	    Persona tester4 = new Persona("Goncho", "Tester", 4);
	    Persona tester5 = new Persona("Straka", "Tester", 3);
	    Persona tester6 = new Persona("Luken", "Tester", 2);
	    Persona tester7 = new Persona("Mike", "Tester", 5);

	    
	    // Crear roles
	    Rol r1 = new Rol("Líder de proyecto", 1);
	    Rol r2 = new Rol("Arquitecto", 3);
	    
	    Rol r3 = new Rol("Programador", 4);
	    
	    Rol r4 = new Rol("Tester", 4);

	    // Crear incompatibilidades
	    Incompatibilidad i1 = new Incompatibilidad(jefe0, programador2);
	    Incompatibilidad i2 = new Incompatibilidad(jefe1, arquitecto6);
	    Incompatibilidad i3 = new Incompatibilidad(jefe1, arquitecto2);
	    Incompatibilidad i4 = new Incompatibilidad(jefe1, arquitecto3);
	    Incompatibilidad i5 = new Incompatibilidad(jefe1, arquitecto4);
	    Incompatibilidad i6 = new Incompatibilidad(jefe1, arquitecto5);

	    // Crear ManejoEquipo
	    ManejoEquipo manejoEquipo = new ManejoEquipo();
	    
	    // Agregar personas, roles e incompatibilidades
	    manejoEquipo.agregarPersona(jefe0);
	    manejoEquipo.agregarPersona(jefe1);
	    
	    manejoEquipo.agregarPersona(arquitecto1);
	    manejoEquipo.agregarPersona(arquitecto2);
	    manejoEquipo.agregarPersona(arquitecto3);
	    manejoEquipo.agregarPersona(arquitecto4);
	    manejoEquipo.agregarPersona(arquitecto5);
	    manejoEquipo.agregarPersona(arquitecto6);
	    
	    manejoEquipo.agregarPersona(programador1);
	    manejoEquipo.agregarPersona(programador2);
	    manejoEquipo.agregarPersona(programador3);
	    manejoEquipo.agregarPersona(programador4);
	    manejoEquipo.agregarPersona(programador5);
	    manejoEquipo.agregarPersona(programador6);

	    manejoEquipo.agregarPersona(tester0);
	    manejoEquipo.agregarPersona(tester1);
	    manejoEquipo.agregarPersona(tester2);
	    manejoEquipo.agregarPersona(tester3);
	    manejoEquipo.agregarPersona(tester4);
	    manejoEquipo.agregarPersona(tester5);
	    manejoEquipo.agregarPersona(tester6);
	    manejoEquipo.agregarPersona(tester7);

	    manejoEquipo.agregarRol(r1);
	    manejoEquipo.agregarRol(r2);
	    manejoEquipo.agregarRol(r3);
	    manejoEquipo.agregarRol(r4);
	    
//	    manejoEquipo.agregarIncompatibilidad(i1);
	    manejoEquipo.agregarIncompatibilidad(i2);
//	    manejoEquipo.agregarIncompatibilidad(i3);
//	    manejoEquipo.agregarIncompatibilidad(i4);
//	    manejoEquipo.agregarIncompatibilidad(i5);
//	    manejoEquipo.agregarIncompatibilidad(i6);

	  	manejoEquipo.mostrarIncompatibilidades();
	    // Crear y ejecutar Solver
	    Solver solver = new Solver(manejoEquipo);
	    solver.start();
	    
	    
	    // Esperar a que el solver termine y mostrar la solución
	    try {
	        solver.join();
	        List<Persona> solucion = solver.getSolucion();
	        System.out.println(solucion);
	        System.out.println("La solución es: ");
	        for (Persona persona : solucion) {
	            System.out.println(persona.getNombre() + " - " + persona.getRol());
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
}