package personas;

import java.util.Objects;

public class Rol {
	private String nombre;
	private int cantidad;
	
	public Rol(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cantidad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return cantidad == other.cantidad && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return " Rol: " + nombre + ", cantidad necesaria para rol: " + cantidad;
	}
	
	
	
	
}
