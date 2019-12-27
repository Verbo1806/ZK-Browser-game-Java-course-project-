package bg.verbo.project.db.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {
	@Id
	private Integer id;

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	@Column(name = _points)
	private Short points;
	public static final String _points = "points";
	
	@Column(name = _funds)
	private Integer funds;
	public static final String _funds = "funds";
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "team")
	private List<Player> players;
	
	public Team() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getPoints() {
		return points;
	}

	public void setPoints(Short points) {
		this.points = points;
	}

	public Integer getFunds() {
		return funds;
	}

	public void setFunds(Integer funds) {
		this.funds = funds;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isBeaten() {
		int deadCount = 0;
		for (Player player : players) {
			if (player.getHealth() <= 0) {
				deadCount++;
			}
		}
		return deadCount == 5 ? true : false;
	}

	public List<Player> getAlivePlayers() {
		return players.stream().filter(p -> p.getHealth() > 0).collect(Collectors.toList());
	}
}
