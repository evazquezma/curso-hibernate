package es.pruebas.hibernate.dao;

import java.util.List;

import es.pruebas.hibernate.filtro.AuthorFiltro;
import es.pruebas.hibernate.model.Author;


public interface AuthorDao {
	public void clearAll();

	public Author save (Author author);

	public List<Author> buscar(AuthorFiltro filtro);
}
