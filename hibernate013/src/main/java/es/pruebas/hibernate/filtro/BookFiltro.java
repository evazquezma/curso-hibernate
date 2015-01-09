package es.pruebas.hibernate.filtro;

import java.util.Date;

public class BookFiltro {

	private String textoLibre;
	private Date fechaPublicacionHasta;

	public String getTextoLibre() {
		return textoLibre;
	}
	public void setTextoLibre(String textoLibre) {
		this.textoLibre = textoLibre;
	}

	public Date getFechaPublicacionHasta() {
		return fechaPublicacionHasta;
	}
	public void setFechaPublicacionHasta(Date fechaPublicacionHasta) {
		this.fechaPublicacionHasta = fechaPublicacionHasta;
	}
}
