package com.kodcu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.kodcu.dao.interfaces.ResultDao;
import com.kodcu.entity.Result;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kodcu
 *
 */
@Transactional
public class JpaResultDAO extends CommonDao<Result> implements ResultDao {

	/**
	 * @param clazz
	 */
	public JpaResultDAO() {
		super(Result.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.kodcu.dao.interfaces.Dao#countAll()
	 */
	public int countAll() {
		return (getJpaTemplate().execute(new JpaCallback<Long>() {

			public Long doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<Long> query = em.createQuery("select count(e) from Result e", Long.class);
				return query.getSingleResult();
			}
		})).intValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.kodcu.dao.interfaces.Dao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Result> findAll() {
		return getJpaTemplate().find("select e from Result e");
	}

}
