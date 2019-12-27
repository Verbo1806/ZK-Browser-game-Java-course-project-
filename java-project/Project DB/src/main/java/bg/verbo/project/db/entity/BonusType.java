package bg.verbo.project.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bonus_types")
public class BonusType {
	@Id
	private Short id;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	public BonusType() {}
	
}
