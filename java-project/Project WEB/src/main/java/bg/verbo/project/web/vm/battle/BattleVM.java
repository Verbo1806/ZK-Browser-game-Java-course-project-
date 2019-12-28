package bg.verbo.project.web.vm.battle;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import bg.verbo.project.db.entity.Team;
import bg.verbo.project.db.entity.User;
import bg.verbo.project.web._aux.Config;
import bg.verbo.project.web.service.NomenclatureService;
import bg.verbo.project.web.service.PlayersService;
import bg.verbo.project.web.service.TeamService;
import bg.verbo.project.web.utils.BattleUtil;

@VariableResolver(DelegatingVariableResolver.class)
public class BattleVM {
	@WireVariable private PlayersService playersService;
	@WireVariable private TeamService teamService;
	@WireVariable private NomenclatureService nomenclatureService;
	
	private Team attacker;
	private Team defender;
	private List<String> battleLog;
	
	private boolean isStarted;
	
	@Init
	public void init() {
		defender = (Team) Executions.getCurrent().getArg().get(Config.PARAM);
		attacker = ((User) Sessions.getCurrent().getAttribute(Config.USER)).getTeam();
		
		battleLog = new ArrayList<>();
		isStarted = false;
	}

	public Team getAttacker() {
		return attacker;
	}

	public Team getDefender() {
		return defender;
	}

	public List<String> getBattleLog() {
		return battleLog;
	}

	public boolean getIsStarted() {
		return isStarted;
	}

	public void setIsStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	@Command
	@NotifyChange("isStarted")
	public void start() throws InterruptedException {
		BattleUtil.startBattle(attacker, defender, battleLog, 
				nomenclatureService.findRandomArena(), this);
		isStarted = true;
	}
}
