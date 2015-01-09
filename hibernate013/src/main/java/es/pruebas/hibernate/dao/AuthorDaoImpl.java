package es.pruebas.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.hibernate.filtro.AuthorFiltro;
import es.pruebas.hibernate.model.Author;


@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public AuthorDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	@Override
	public void clearAll() {
		getSession().createQuery("DELETE FROM Author").executeUpdate();
	}


	@Override
	public Author save(Author author) {
		getSession().save(author);
		return author;
	}


	@Override
	public List<Author> buscar(AuthorFiltro filtro) {
		// TODO Auto-generated method stub
		return null;
	}




}
