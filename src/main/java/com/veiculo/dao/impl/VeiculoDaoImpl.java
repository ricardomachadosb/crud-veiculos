package com.veiculo.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.veiculo.dao.VeiculoDao;
import com.veiculo.entity.Veiculo;
import com.veiculo.utils.HibernateUtil;

/**
 * @author ricardo
 * 
 */
@Transactional
public class VeiculoDaoImpl implements VeiculoDao {

	@Override
	public void save(Veiculo veiculo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(veiculo);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Veiculo> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Veiculo.class);
		List<Veiculo> veiculos = (List<Veiculo>) criteria.list();
		session.getTransaction().commit();
		return veiculos;
	}

	@Override
	public Veiculo get(Integer id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Veiculo veiculo = (Veiculo) session.get(Veiculo.class, id);
		session.getTransaction().commit();
		return veiculo;
	}

	@Override
	public void delete(Veiculo veiculo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(veiculo);
		session.getTransaction().commit();
	}

}
