package es.pruebas.hibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Responsable implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7030624949051803549L;


	private String nombre;
	private String cargo;
	private String telefono;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Responsable other = (Responsable) obj;
		if (cargo == null) {
			if (other.cargo != null) {
				return false;
			}
		} else if (!cargo.equals(other.cargo)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (telefono == null) {
			if (other.telefono != null) {
				return false;
			}
		} else if (!telefono.equals(other.telefono)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "Responsable [nombre=" + nombre + ", cargo=" + cargo
				+ ", telefono=" + telefono + "]";
	}

}
