package es.pruebas.hibernate.factura.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.hibernate.dao.CiudadDao;
import es.pruebas.hibernate.model.Ciudad;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml"})
@TransactionConfiguration(defaultRollback=true, transactionManager="transactionManager")
public class TestCiudadDao {

	@Autowired
	private CiudadDao ciudadDao;


	@Autowired
	private DataSource dataSource;


	@Before
	public void setup() throws DatabaseUnitException, FileNotFoundException, IOException, SQLException {
		Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con);

		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(true);
		IDataSet dataSet = builder.build(new FileInputStream("./src/test/resources/dbunit/todo.xml"));

		try {
			DatabaseOperation.REFRESH.execute(dbUnitCon, dataSet);
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}
	}

	@After
	public void tearDown() throws IOException, DatabaseUnitException, SQLException {
		Connection con = DataSourceUtils.getConnection(dataSource);
		DataSourceUtils.releaseConnection(con, dataSource);
	}






	@Test @Transactional
	public void testCiudadesConMasHabitantes() {
		List<Ciudad> ciudades = ciudadDao.getCiudadesConMasHabitantes(100000);
		assertNotNull(ciudades);
		assertEquals(3, ciudades.size());


		ciudades = ciudadDao.getCiudadesConMasHabitantes(9900000);
		assertNotNull(ciudades);
		assertEquals(0, ciudades.size());
	}


	@Test @Transactional
	public void testCiudadesMasAntiguas() {
		List<Ciudad> ciudades = ciudadDao.getCiudadesMasAntiguas(200);
		assertNotNull(ciudades);
		assertEquals(1, ciudades.size());


		ciudades = ciudadDao.getCiudadesMasAntiguas(20);
		assertNotNull(ciudades);
		assertEquals(7, ciudades.size());


		ciudades = ciudadDao.getCiudadesMasAntiguas(90);
		assertNotNull(ciudades);
		assertEquals(6, ciudades.size());
	}


	@Test @Transactional
	public void testCiudadesSinAlcaldeCorrupto() {
		List<Ciudad> ciudades = ciudadDao.getCiudadesSinAlcaldeCorrupto();
		assertNotNull(ciudades);
		assertEquals(4, ciudades.size());
	}


	@Test @Transactional
	public void testCiudadesConAlcaldeCorruptoReelegido() {
		List<Ciudad> ciudades = ciudadDao.getCiudadesConAlcaldeCorruptoReelegido();
		assertNotNull(ciudades);
		assertEquals(2, ciudades.size());
	}



	@Test @Transactional
	public void testCiudadesConMasMonumentos() {
		List<Ciudad> ciudades = ciudadDao.getCiudadesConMasMonumentos(0);
		assertNotNull(ciudades);
		assertEquals(3, ciudades.size());

		ciudades = ciudadDao.getCiudadesConMasMonumentos(1);
		assertNotNull(ciudades);
		assertEquals(2, ciudades.size());


		ciudades = ciudadDao.getCiudadesConMasMonumentos(2);
		assertNotNull(ciudades);
		assertEquals(1, ciudades.size());

		ciudades = ciudadDao.getCiudadesConMasMonumentos(3);
		assertNotNull(ciudades);
		assertEquals(0, ciudades.size());
	}


}
