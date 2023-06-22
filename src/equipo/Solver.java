package equipo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import personas.*;

public class Solver extends Thread {
	private ManejoEquipo manejoDeEquipo;

	public Map<String, Integer> contadorPersonasEnRol;
	private int bestRating = 0;
	private List<Persona> solucion;
	private List<Persona> solucionActual;  // Nueva lista para la solución actual
	

	public Solver(ManejoEquipo manejoDeEquipo) {
		this.manejoDeEquipo = manejoDeEquipo;
		this.solucion = new ArrayList<>();
		this.solucionActual = new ArrayList<>();  // Inicializamos la nueva lista
		this.contadorPersonasEnRol = new HashMap<>();

		for (Rol rol : manejoDeEquipo.getRoles()) {
			this.contadorPersonasEnRol.put(rol.getNombre(), rol.getCantidad());
		}
	}

	public void run() {
		solve(0);
		if (bestRating == 0) {
			solucion.clear(); 
		}
	}

	public void solve(int indicePersona) {
	    if (indicePersona == manejoDeEquipo.getPersonas().size()) { 
	        if (estanTodosLosRolesRepresentados()) {
	            int currentRating = solucionActual.stream().mapToInt(Persona::getRating).sum();  
	            if (currentRating > bestRating) {
	                bestRating = currentRating;
	                solucion = new ArrayList<Persona>(solucionActual);  
	            }
	        }
	    } else {
	        Persona personaActual = manejoDeEquipo.getPersonas().get(indicePersona);
	        if (seNecesitaPersonaParaRol(personaActual.getRol()) && esCompatible(personaActual)) {
	            agregarPersonaAEquipo(personaActual);
	            solve(indicePersona + 1);
	            removerPersonaDeEquipo(personaActual);
	        }
	        solve(indicePersona + 1);
	    }
	}
	
	public boolean estanTodosLosRolesRepresentados() {
	    for (Map.Entry<String, Integer> entry : contadorPersonasEnRol.entrySet()) {
	        if (entry.getValue() > 0) { 
	            return false;
	        }
	    }
	    return true;
	}

	public boolean esCompatible(Persona persona) {
	    for (Incompatibilidad incompatibilidad : manejoDeEquipo.getIncompatibilidades()) {
	        if ((incompatibilidad.getPersona1().equals(persona) && solucionActual.contains(incompatibilidad.getPersona2())) ||
	            (incompatibilidad.getPersona2().equals(persona) && solucionActual.contains(incompatibilidad.getPersona1()))) {
	            return false;
	        }
	    }
	    return true;
	}

	private boolean seNecesitaPersonaParaRol(String rol) {
		if (!contadorPersonasEnRol.containsKey(rol)) {
			return false;
		}
		return contadorPersonasEnRol.get(rol) > 0;
	}

	private void agregarPersonaAEquipo(Persona personaActual) {
		solucionActual.add(personaActual);  
		contadorPersonasEnRol.put(personaActual.getRol(), contadorPersonasEnRol.get(personaActual.getRol()) - 1);
	}

	private void removerPersonaDeEquipo(Persona personaActual) {
		solucionActual.remove(personaActual);  
		contadorPersonasEnRol.put(personaActual.getRol(), contadorPersonasEnRol.get(personaActual.getRol()) + 1);
	}


	public int getBestRating() {
		return bestRating;
	}

	public List<Persona> getSolucion() {
		return solucion;
	}
	
	public String toString() {
	    StringBuilder str = new StringBuilder();
	    str.append("Solución encontrada:\n");
	    
	    for (Persona persona : solucion) {
	        str.append(persona.toString()).append("\n");
	    }
	    
	    return str.toString();
	}

}
