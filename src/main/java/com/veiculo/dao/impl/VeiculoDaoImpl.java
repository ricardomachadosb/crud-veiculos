package com.veiculo.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.veiculo.dao.VeiculoDao;
import com.veiculo.entity.Veiculo;
import com.veiculo.utils.HibernateUtil;

/**
 * @author ricardo
 * 
 */
@Transactional
@Repository
public class VeiculoDaoImpl implements VeiculoDao {
	
	/* (non-Javadoc)
	 * @see com.veiculo.dao.VeiculoDao#save(com.veiculo.entity.Veiculo)
	 */
	@Override
	public void save(Veiculo veiculo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(veiculo);
		session.getTransaction().commit();
		session.close();
	}

	/* (non-Javadoc)
	 * @see com.veiculo.dao.VeiculoDao#list()
	 */
	@Override
	public List<Veiculo> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Veiculo.class);
		List<Veiculo> veiculos = (List<Veiculo>) criteria.list();
		session.getTransaction().commit();
		return veiculos;
	}

	/* (non-Javadoc)
	 * @see com.veiculo.dao.VeiculoDao#get(java.lang.Integer)
	 */
	@Override
	public Veiculo get(Integer id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Veiculo veiculo = (Veiculo) session.get(Veiculo.class, id);
		session.getTransaction().commit();
		return veiculo;
	}

	/* (non-Javadoc)
	 * @see com.veiculo.dao.VeiculoDao#delete(com.veiculo.entity.Veiculo)
	 */
	@Override
	public void delete(Veiculo veiculo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(veiculo);
		session.getTransaction().commit();
	}

	/* (non-Javadoc)
	 * @see com.veiculo.dao.VeiculoDao#merge(com.veiculo.entity.Veiculo)
	 */
	@Override
	public void merge(Veiculo veiculo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(veiculo);
		session.getTransaction().commit();
	}

}
