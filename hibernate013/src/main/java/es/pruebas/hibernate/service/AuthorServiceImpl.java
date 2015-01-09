package es.pruebas.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.hibernate.dao.AuthorDao;
import es.pruebas.hibernate.filtro.AuthorFiltro;
import es.pruebas.hibernate.model.Author;

@Service("authorService")
@Transactional
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorDao authorDao;


	@Override
	public void clearAll() {
		authorDao.clearAll();
	}

	@Override
	public Author save(Author author) {
		return authorDao.save(author);
	}

	@Override
	public List<Author> buscar(AuthorFiltro filtro) {
		return authorDao.buscar(filtro);
	}





}
