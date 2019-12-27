package bg.verbo.project.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill {
	@Id
	private Integer id;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	public Skill() {}
	
}
