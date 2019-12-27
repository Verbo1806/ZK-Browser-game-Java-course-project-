package bg.verbo.project.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.verbo.project.db.dao.ArenaDAO;
import bg.verbo.project.db.entity.Arenas;

@Service
public class NomenclatureService {
	@Autowired private ArenaDAO arenaDAO;
	
	// Arenas
	public List<Arenas> findAllArenas() {
		return arenaDAO.findAll();
	}
	
	public Arenas findRandomArena() {
		return arenaDAO.findRandomArena();
	}
}
