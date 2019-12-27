package bg.verbo.project.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "arenas")
public class Arenas {
	@Id
	private Short id;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	public Arenas() {}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
