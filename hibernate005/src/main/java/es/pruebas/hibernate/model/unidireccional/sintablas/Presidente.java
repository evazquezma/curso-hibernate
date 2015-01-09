package es.pruebas.hibernate.model.unidireccional.sintablas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UD_ST_PRESIDENTE")
public class Presidente {
	@Id @GeneratedValue
	private Long id;


}
