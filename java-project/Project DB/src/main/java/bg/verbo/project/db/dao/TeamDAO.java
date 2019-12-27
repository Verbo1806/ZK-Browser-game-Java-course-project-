package bg.verbo.project.db.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.verbo.project.db.entity.Team;

@Repository
public class TeamDAO extends GenericDaoImpl<Team, Integer> {

	public List<Team> findAllUsersByRanking() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Team> criteria = builder.createQuery(Team.class);
		Root<Team> root = criteria.from(Team.class);
		
		criteria.select(root);
		
		criteria.orderBy(builder.desc(root.get(Team._points)));
		
		return createQuery(criteria).getResultList();
	}
}
