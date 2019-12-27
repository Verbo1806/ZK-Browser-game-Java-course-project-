package bg.verbo.project.web.vm.players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import bg.verbo.project.db.entity.Player;
import bg.verbo.project.db.entity.Team;
import bg.verbo.project.web.service.PlayersService;
import bg.verbo.project.web.service.TeamService;

@VariableResolver(DelegatingVariableResolver.class)
public class PlayersVM {
	@WireVariable private PlayersService playersService;
	@WireVariable private TeamService teamService;
	
	private List<Player> players;
	private Team team;
	
	@Init
	public void init() {
		players = playersService.findAllFreePlayers();
		team = teamService.getDefaultUser().getTeam();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	@Command
	public void buyPlayer(@BindingParam("player") Player player) {
		if (team.getPlayers().size() >= 5) {
			Messagebox.show(Labels.getLabel("validation.maxPlayers"), 
				    Labels.getLabel("error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if (team.getFunds() < player.getPrice()) {
			Messagebox.show(Labels.getLabel("validation.noFunds"), 
				    Labels.getLabel("error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
		team.setFunds(team.getFunds() - player.getPrice());
		teamService.save(team);
		player.setTeam(team);
		playersService.save(player);
		
		Map<String,Object> args = new HashMap<String,Object>();
        args.put("zulName", "team");
		BindUtils.postGlobalCommand(null, null, "changeContent", args);
	}
}
