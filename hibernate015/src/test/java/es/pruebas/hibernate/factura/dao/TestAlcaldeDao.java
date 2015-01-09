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

import es.pruebas.hibernate.dao.AlcaldeDao;
import es.pruebas.hibernate.model.Alcalde;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml"})
@TransactionConfiguration(defaultRollback=true, transactionManager="transactionManager")
public class TestAlcaldeDao {

	@Autowired
	private AlcaldeDao alcaldeDao;


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
		List<Alcalde> alcaldes = alcaldeDao.getAlcaldesMonumentoMasAntiguo(110);
		assertNotNull(alcaldes);
		assertEquals(1, alcaldes.size());


		alcaldes = alcaldeDao.getAlcaldesMonumentoMasAntiguo(100);
		assertNotNull(alcaldes);
		assertEquals(2, alcaldes.size());

	}
}
