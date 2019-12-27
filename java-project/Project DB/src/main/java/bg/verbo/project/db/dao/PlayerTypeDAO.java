package bg.verbo.project.db.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.verbo.project.db.entity.PlayerType;

@Repository
public class PlayerTypeDAO extends GenericDaoImpl<PlayerType, String> {

	public PlayerType findByCode(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<PlayerType> criteria = builder.createQuery(PlayerType.class);
		Root<PlayerType> root = criteria.from(PlayerType.class);
		
		criteria.select(root);
		
		criteria.where(builder.equal(root.get(PlayerType._code), code));
		
		return createQuery(criteria).getSingleResult();
	}

}
