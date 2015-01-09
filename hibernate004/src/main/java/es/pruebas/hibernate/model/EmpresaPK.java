package es.pruebas.hibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmpresaPK implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2939479856292705158L;


	private String cif;
	private Integer ordinal;


	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public Integer getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((ordinal == null) ? 0 : ordinal.hashCode());
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
		EmpresaPK other = (EmpresaPK) obj;
		if (cif == null) {
			if (other.cif != null) {
				return false;
			}
		} else if (!cif.equals(other.cif)) {
			return false;
		}
		if (ordinal == null) {
			if (other.ordinal != null) {
				return false;
			}
		} else if (!ordinal.equals(other.ordinal)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "EmpresaPK [cif=" + cif + ", ordinal=" + ordinal + "]";
	}
}
