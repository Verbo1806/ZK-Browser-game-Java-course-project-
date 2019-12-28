package bg.verbo.project.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.verbo.project.db.dao.TeamDAO;
import bg.verbo.project.db.dao.UserDAO;
import bg.verbo.project.db.entity.Team;
import bg.verbo.project.db.entity.User;

@Service
public class TeamService {
	@Autowired private TeamDAO teamDAO;
	@Autowired private UserDAO userDAO;
	
	// Teams
	public List<Team> findAllUsersByRanking() {
		return teamDAO.findAllUsersByRanking();
	}

	public User getUser(int id) {
		return userDAO.findById((long) id);
	}

	@Transactional
	public void save(Team team) {
		teamDAO.saveOrUpdate(team);
	}

	public User isUserValid(String username, String password) {
		return userDAO.findByUsernameAndPass(username, password);
	}
}
