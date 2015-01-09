package es.pruebas.hibernate.dao;

import java.util.List;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import es.pruebas.hibernate.filtro.BookFiltro;
import es.pruebas.hibernate.model.Book;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public BookDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	@Override
	public void clearAll() {
		getSession().createQuery("DELETE FROM Book").executeUpdate();

		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		fullTextSession.purgeAll(Book.class);
	}


	@Override
	public Book save(Book book) {
		getSession().save(book);
		return book;
	}


	@Override
	public List<Book> buscar(BookFiltro filtro) {
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();

		BooleanQuery booleanQuery = new BooleanQuery();

		if (! StringUtils.isEmpty(filtro.getTextoLibre())) {
			org.apache.lucene.search.Query textoQuery = qb
					  .keyword()
					  .onFields("title", "subtitle", "authors.name")
					  .matching(filtro.getTextoLibre())
					  .createQuery();

			booleanQuery.add(textoQuery, Occur.MUST);
		}


		if (filtro.getFechaPublicacionHasta() != null) {
			org.apache.lucene.search.Query textoQuery = qb
					.range()
					.onField("publicationDate")
					.below(filtro.getFechaPublicacionHasta())
					.createQuery();

			booleanQuery.add(textoQuery, Occur.MUST);
		}

		// Pasar de query de lucene a query de hibernate
		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(booleanQuery, Book.class);

		return hibQuery.list();
	}

}
