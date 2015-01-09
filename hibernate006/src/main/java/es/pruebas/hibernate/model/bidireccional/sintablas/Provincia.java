package es.pruebas.hibernate.model.bidireccional.sintablas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BD_ST_PROVINCIA")
public class Provincia {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne
	private Pais pais;


}
