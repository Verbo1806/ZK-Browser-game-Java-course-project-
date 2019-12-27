package bg.verbo.project.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "christmas")
public class Christmas {
	@Id
	private Short id;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	@Column(name = "icon_code")
	private String code;
	public static final String _code = "code";
	
	@Column(name = _priority)
	private Short priority;
	public static final String _priority = "priority";
	
	@Column(name = "is_valid")
	private Boolean isValid;
	public static final String _isValid = "isValid";
	
	public Christmas() {}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Short getPriority() {
		return priority;
	}

	public void setPriority(Short priority) {
		this.priority = priority;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
}
