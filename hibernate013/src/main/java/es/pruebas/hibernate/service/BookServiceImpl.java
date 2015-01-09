package es.pruebas.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.hibernate.dao.BookDao;
import es.pruebas.hibernate.filtro.BookFiltro;
import es.pruebas.hibernate.model.Book;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;


	@Override
	public void clearAll() {
		bookDao.clearAll();
	}

	@Override
	public Book save(Book book) {
		return bookDao.save(book);
	}

	@Override
	public List<Book> buscar(BookFiltro filtro) {
		return bookDao.buscar(filtro);
	}
}
