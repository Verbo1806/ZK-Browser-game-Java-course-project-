package bg.verbo.project.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	@Column(name = "creation_date")
	private Date creationDate;
	public static final String _creationDate = "creationDate";
	
	@Column(name = "death_date")
	private Date deathDate;
	public static final String _deathDate = "deathDate";
	
	@Column(name = _price)
	private Integer price;
	public static final String _price = "price";
	
	@Column(name = _health)
	private Integer health;
	public static final String _health = "health";
	
	@Column(name = _power)
	private Integer power;
	public static final String _power = "power";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private PlayerType type;
	public static final String _type = "type";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	private Team team;
	public static final String _team = "team";
	
	public Player() {}
	
	/* Health: 0 - 100 
	 * Power:  0 - 300
	 * Price:  (Health + Power) * 0.75 */
	public Player(String name, Integer health, Integer power, PlayerType type) {
		this.name = name;
		this.creationDate = new Date();
		this.health = health;
		this.power = power;
		this.price = ((int)Math.round((health + power) * 0.75));
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getAbsoluteHealth() {
		return health > 0 ? health : 0;
	}
}
