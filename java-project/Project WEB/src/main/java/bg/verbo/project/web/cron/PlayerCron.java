package bg.verbo.project.web.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.verbo.project.db.entity.Player;
import bg.verbo.project.web._aux.Config;
import bg.verbo.project.web.service.PlayersService;
import bg.verbo.project.web.utils.PlayerUtil;

@Component
public class PlayerCron {
	@Autowired PlayersService playersService;
	
	public void addNewPlayers() {
		for (int i = 0; i < Config.NEW_PLAYERS_COUNT; i++) {
			Player newPlayer = new Player(PlayerUtil.generateName(), PlayerUtil.generateHealth(),
				PlayerUtil.generatePower(), playersService.getTypeByCode(PlayerUtil.generateTypeCode()));
			
			playersService.save(newPlayer);
		}
	}
	
}
