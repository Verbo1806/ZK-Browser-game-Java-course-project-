package bg.verbo.project.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.verbo.project.db.dao.ChristmasDAO;
import bg.verbo.project.db.entity.Christmas;

@Service
public class ChristmasService {
	@Autowired private ChristmasDAO christmasDAO;
	
	public List<Christmas> findAllPresents() {
		return christmasDAO.findAllPresents();
	}

	@Transactional
	public void save(Christmas element) {
		christmasDAO.saveOrUpdate(element);
	}
}
