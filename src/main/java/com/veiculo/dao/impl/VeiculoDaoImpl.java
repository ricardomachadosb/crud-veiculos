package com.veiculo.dao.impl;


import javax.transaction.Transactional;

import org.hibernate.Session;

import com.veiculo.dao.VeiculoDao;
import com.veiculo.entity.Veiculo;
import com.veiculo.utils.HibernateUtil;

/**
 * @author ricardo
 *
 */
@Transactional
public class VeiculoDaoImpl implements VeiculoDao{
	
	@Override
	public void save(Veiculo veiculo) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(veiculo);
		 session.getTransaction().commit();
		 session.close();
	}

}
