package bg.verbo.project.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "abilities")
public class Ability {
	@Id
	private Short id;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "skill_id")
	private Skill skill;
	public static final String _skill = "skill";
	
	@Column(name = _bonus)
	private Short bonus;
	public static final String _bonus = "bonus";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bonus_type")
	private BonusType bonusType;
	public static final String _bonusType = "bonusType";
	
	@Column(name = _type)
	private String type;
	public static final String _type = "type";

	public Ability() {}
	
}
