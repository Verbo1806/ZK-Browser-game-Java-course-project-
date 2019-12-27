package bg.verbo.project.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.verbo.project.db.entity.Christmas;

@Repository
public class ChristmasDAO extends GenericDaoImpl<Christmas, Short> {

	public List<Christmas> findAllPresents() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Christmas> criteria = builder.createQuery(Christmas.class);
		Root<Christmas> root = criteria.from(Christmas.class);
		
		criteria.select(root);
		
		final List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(builder.notEqual(root.get(Christmas._priority), 1));
		predicates.add(builder.equal(root.get(Christmas._isValid), true));
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		return createQuery(criteria).getResultList();
	}

}
