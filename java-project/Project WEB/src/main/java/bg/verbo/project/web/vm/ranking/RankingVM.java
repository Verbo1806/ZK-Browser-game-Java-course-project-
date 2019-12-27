package bg.verbo.project.web.vm.ranking;

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

import bg.verbo.project.db.entity.Team;
import bg.verbo.project.web.service.TeamService;

@VariableResolver(DelegatingVariableResolver.class)
public class RankingVM {
	@WireVariable private TeamService teamService;
	
	private List<Team> teams;
	private Team userTeam;
	
	@Init
	public void init() {
		teams = teamService.findAllUsersByRanking();
		userTeam = teamService.getDefaultUser().getTeam();
	}

	public List<Team> getTeams() {
		return teams;
	}
	
	@Command
	public void attack(@BindingParam("param") Team team) {
		if (team.getId() == userTeam.getId()) {
			Messagebox.show(Labels.getLabel("validation.attackYourself"), 
				    Labels.getLabel("error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
		if (team.getPlayers().size() < 5 || userTeam.getPlayers().size() < 5) {
			Messagebox.show(Labels.getLabel("validation.notEnoughPlayers"), 
				    Labels.getLabel("error"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
		Map<String,Object> args = new HashMap<String,Object>();
        args.put("zulName", "battle");
        args.put("param", team);
		BindUtils.postGlobalCommand(null, null, "changeContent", args);
	}
	
}
