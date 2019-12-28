package bg.verbo.project.web.vm.team;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import bg.verbo.project.db.entity.Player;
import bg.verbo.project.db.entity.Team;
import bg.verbo.project.db.entity.User;
import bg.verbo.project.web._aux.Config;
import bg.verbo.project.web.service.PlayersService;
import bg.verbo.project.web.service.TeamService;

@VariableResolver(DelegatingVariableResolver.class)
public class TeamVM {
	@WireVariable private PlayersService playersService;
	@WireVariable private TeamService teamService;
	
	private Team team;
	
	@Init
	public void init() {
		team = ((User) Sessions.getCurrent().getAttribute(Config.USER)).getTeam();
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Command
	@NotifyChange("team")
	public void removePlayer(@BindingParam("player") Player player) {
		team.getPlayers().remove(player);
		teamService.save(team);
		player.setTeam(null);
		playersService.save(player);
	}
}
