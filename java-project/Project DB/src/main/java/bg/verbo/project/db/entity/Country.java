package bg.verbo.project.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {
	@Id
	private String code;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	public Country() {}
	
}
