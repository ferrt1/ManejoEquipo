package equipo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import personas.*;

public class ManejoEquipo {
	
    private List<Persona> personas;
    private List<Rol> roles;
    private List<Incompatibilidad> incompatibilities;
    

    public ManejoEquipo() {
        this.personas = new ArrayList<>();
        this.roles = new ArrayList<>();
        this.incompatibilities = new ArrayList<>();

        
    }

    // Método para agregar una persona
    public void agregarPersona(Persona person) {
    	personas.add(person);
    }

    // Método para agregar un rol
    public void agregarRol(Rol role) {
        roles.add(role);
    }

    // Método para agregar una incompatibilidad
    public void agregarIncompatibilidad(Incompatibilidad incompatibilidad) {
    	incompatibilities.add(incompatibilidad);
    }

    // Método para visualizar las personas
    public void mostrarPersonas() {
        for (Persona persona : this.personas) {
            System.out.println("Nombre: " + persona.getNombre() + ", Rol: " + persona.getRol() + ", Rating: " + persona.getRating());
        }
    }

    // Método para visualizar los roles
    public void mostrarRoles() {
        for (Rol rol : this.roles) {
            System.out.println("Rol: " + rol.getNombre() + ", Cantidad necesaria: " + rol.getCantidad());
        }
    }

    // Método para visualizar las incompatibilidades
    public void mostrarIncompatibilidades() {
        for (Incompatibilidad incompatibilidad : this.incompatibilities) {
            System.out.println("Incompatible: " + incompatibilidad.getPersona1().getNombre() + " y " +
            		incompatibilidad.getPersona2().getNombre());
        }
    }
   
    
    // Getters
    public List<Persona> getPersonas() {
        return personas;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public List<Incompatibilidad> getIncompatibilidades() {
        return incompatibilities;
    }
}
