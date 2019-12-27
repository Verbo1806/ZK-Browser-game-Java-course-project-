package bg.verbo.project.db.dao;

import java.math.BigInteger;
import java.util.Random;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import bg.verbo.project.db.entity.Arenas;

@Repository
public class ArenaDAO extends GenericDaoImpl<Arenas, Short> {

	public Arenas findRandomArena() {
	  Query countQuery = getEntityManager().createNativeQuery("select count(*) from Arenas");
	  long count = ((BigInteger)countQuery.getSingleResult()).longValue();
	
	  Random random = new Random();
	  int number = random.nextInt((int)count);
	
	  Query selectQuery = getEntityManager().createQuery("select a from Arenas a");
	  selectQuery.setFirstResult(number);
	  selectQuery.setMaxResults(1);
	  return (Arenas)selectQuery.getSingleResult();
	}

}
