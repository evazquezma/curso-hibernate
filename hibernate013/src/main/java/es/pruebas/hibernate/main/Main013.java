package es.pruebas.hibernate.main;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.pruebas.hibernate.filtro.BookFiltro;
import es.pruebas.hibernate.model.Author;
import es.pruebas.hibernate.model.Book;
import es.pruebas.hibernate.service.AuthorService;
import es.pruebas.hibernate.service.BookService;

public class Main013 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context.xml");

		AuthorService authorService = (AuthorService) context.getBean("authorService");
		BookService bookService = (BookService) context.getBean("bookService");

		bookService.clearAll();
		authorService.clearAll();


		Author autor1 = new Author();
		autor1.setName("Cervantes");

		Author autor2 = new Author();
		autor2.setName("Nombre autor 2");

		Author autor3 = new Author();
		autor3.setName("Nombre autor 3");

		autor1 = authorService.save(autor1);
		autor2 = authorService.save(autor2);
		autor3 = authorService.save(autor3);



		Calendar cal = Calendar.getInstance();

		Book book = new Book();
		book.setTitle("Don Quijote de la Mancha");
		book.setSubtitle("Un libro");
		book.setPublicationDate(new Date());
		book.getAuthors().add(autor1);
		book.getAuthors().add(autor2);
		bookService.save(book);


		book = new Book();
		book.setTitle("La Galatea");
		book.setSubtitle("Otro libro del mismo autor que el Quijote");
		book.setPublicationDate(new Date());
		book.getAuthors().add(autor1);
		book.getAuthors().add(autor2);
		bookService.save(book);


		BookFiltro bookFiltro = new BookFiltro();
		bookFiltro.setTextoLibre("Quijote");
		cal.set(2900, 0, 1);
		bookFiltro.setFechaPublicacionHasta(cal.getTime());

		for(Book libro : bookService.buscar(bookFiltro)) {
			System.out.println(libro);
		}

		((ConfigurableApplicationContext)context).close();
	}

}
