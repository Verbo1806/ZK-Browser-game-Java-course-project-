package bg.verbo.project.db.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.verbo.project.db.entity.User;

@Repository
public class UserDAO extends GenericDaoImpl<User, Long> {

	public User findByUsernameAndPass(String username, String password) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		
		criteria.select(root);
		
		final List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get(User._name), username));
		predicates.add(builder.equal(root.get(User._password), password));
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		return createQuery(criteria).getResultList().stream().findAny().orElse(null);
	}

}
