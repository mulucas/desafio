package br.com.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

public static  EntityManagerFactory factory = null;
	
	static {
		init();
	}
	private static void init() {
		try {
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("esig");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	public static Object getPrimaryKey(Object object) {
		return factory.getPersistenceUnitUtil().getIdentifier(object);
	}
}
