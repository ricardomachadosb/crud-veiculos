package com.veiculo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author ricardo
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory ;
	
    static {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
    }
   
    /**
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
   }
}
