package es.pruebas.hibernate.service;

import java.util.List;

import es.pruebas.hibernate.filtro.BookFiltro;
import es.pruebas.hibernate.model.Book;


public interface BookService {

	public void clearAll();

	public Book save (Book author);

	public List<Book> buscar(BookFiltro filtro);
}
