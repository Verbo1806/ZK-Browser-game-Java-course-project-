package bg.verbo.project.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player_types")
public class PlayerType {
	@Id
	private String code;
	public static final String _code = "code";

	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";

	public PlayerType() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
