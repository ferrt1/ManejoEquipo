package personas;

import java.util.Objects;

public class Persona {
	private String nombre;
	private String rol;
	private int rating;
	
	public Persona(String nombre, String rol, int rating) {
		this.nombre = nombre;
		this.rol = rol;
		this.rating = rating;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	@Override
	public int hashCode() {
		return Objects.hash(nombre, rating, rol);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(nombre, other.nombre) && rating == other.rating && Objects.equals(rol, other.rol);
	}


	@Override
	public String toString() {
		return nombre + ", Rol: " + rol + ", Rating: " + rating;
	}

	
	
}
