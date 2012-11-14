package persistence.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import persistence.ModelDao;

import model.Model;

public class ModelDaoHibernateImpl extends HibernateDaoSupport implements ModelDao {

	@Override
	public void save(Model model) {
		getHibernateTemplate().save(model);
	}

	@Override
	public void update(Model model) {
		getHibernateTemplate().update(model);
	}

	@Override
	public void delete(Model model) {
		getHibernateTemplate().delete(model);

	}

	/*
	 * This method use HQL
	 */
	@Override
	public List<Model> find (final Boolean deleted) {
		return getHibernateTemplate().execute(
				new HibernateCallback<List<Model>>() {
					@Override
					public List<Model> doInHibernate(Session session) throws HibernateException, SQLException {
						String hql = "select model from Model model where model.deleted=:deleted";
						Query query = session.createQuery(hql);
						query.setParameter("deleted", deleted);
						@SuppressWarnings("rawtypes")
						List resultRawType = query.list();
						List<Model> result = new ArrayList<Model>();
						for (Object obj : resultRawType) {
							result.add((Model) obj);
						}
						resultRawType = null;
						return result;
					}
				});
	}

	/*
	 * This method use SQL
	 */
	@Override
	public List<Model> find(final String name) {
		return getHibernateTemplate().execute(
				new HibernateCallback<List<Model>>() {
					@Override
					public List<Model> doInHibernate(Session session) throws HibernateException, SQLException {
						String sql = "select * from model where name like ?";
						Query query = session.createSQLQuery(sql).addEntity(Model.class);
						query.setString(0, "%" + name + "%");
						@SuppressWarnings("rawtypes")
						List resultRawType = query.list();
						List<Model> result = new ArrayList<Model>();
						for (Object obj : resultRawType) {
							result.add((Model) obj);
						}
						resultRawType = null;
						return result;
					}
				});
	}

	/*
	 * This method use Criteria
	 */
	@Override
	public List<Model> find(final String name, final Boolean deleted) {
		return getHibernateTemplate().execute(
				new HibernateCallback<List<Model>>() {
					@Override
					public List<Model> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(Model.class);
						criteria.add(Restrictions.ilike("name",
								'%' + name + '%'));
						criteria.add(Restrictions.eq("deleted", deleted));
						@SuppressWarnings("rawtypes")
						List resultRawType = criteria.list();
						List<Model> result = new ArrayList<Model>();
						for (Object obj : resultRawType) {
							result.add((Model) obj);
						}
						resultRawType = null;
						return result;
					}
				});
	}

}