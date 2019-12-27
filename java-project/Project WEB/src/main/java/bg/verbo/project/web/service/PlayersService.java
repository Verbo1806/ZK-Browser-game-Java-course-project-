package bg.verbo.project.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.verbo.project.db.dao.PlayerDAO;
import bg.verbo.project.db.dao.PlayerTypeDAO;
import bg.verbo.project.db.entity.Player;
import bg.verbo.project.db.entity.PlayerType;

@Service
public class PlayersService {
	@Autowired private PlayerDAO playerDAO;
	@Autowired private PlayerTypeDAO playerTypeDAO;
	
	// Players
	public List<Player> findAllFreePlayers() {
		return playerDAO.findAllFreePlayers();
	}

	@Transactional
	public void save(Player player) {
		playerDAO.saveOrUpdate(player);
	}

	public PlayerType getTypeByCode(String code) {
		return playerTypeDAO.findByCode(code);
	}
}
