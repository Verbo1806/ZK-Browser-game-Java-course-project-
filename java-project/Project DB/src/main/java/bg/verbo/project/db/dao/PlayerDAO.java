package bg.verbo.project.db.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.verbo.project.db.entity.Player;

@Repository
public class PlayerDAO extends GenericDaoImpl<Player, Integer> {

	public List<Player> findAllFreePlayers() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Player> criteria = builder.createQuery(Player.class);
		Root<Player> root = criteria.from(Player.class);
		
		criteria.select(root);
		
		criteria.where(builder.isNull(root.get(Player._team)));
		criteria.orderBy(builder.asc(root.get(Player._name)));
		
		return createQuery(criteria).getResultList();
	}

}
