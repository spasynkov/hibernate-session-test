package stas;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateConnector {
	private static final SessionFactory SESSION_FACTORY;

	static {
		/*
		// create session factory from xml configs via configuration, hibernate 6 style
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Cat.class);

		SESSION_FACTORY = configuration.buildSessionFactory();*/

		/*
		// create session factory from xml configs via metadata, Hibernate in Action Second Edition book style
		SESSION_FACTORY = new MetadataSources(
				new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build()
		).buildMetadata().buildSessionFactory();*/

		// create session factory without xml configs, just with java code
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate_examples")
				.applySetting("hibernate.connection.username", "user")
				.applySetting("hibernate.connection.password", "pass")
				.applySetting("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")

				.applySetting("hibernate.hbm2ddl.auto", "create-drop")
				.applySetting("hibernate.show_sql", "true")
				.applySetting("hibernate.format_sql", "true")
				.applySetting("hibernate.use_sql_comments", "true")
				.build();

		SESSION_FACTORY = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(stas.entities.Cat.class)
				.getMetadataBuilder()
				.build()    // builds metadata
				.buildSessionFactory();

		//SESSION_FACTORY.createEntityManager();  // creates JPA's EntityManager from SessionFactory
	}

	public static void save(Object o) {
		Transaction transaction = null;
		try {
			Session session = SESSION_FACTORY.openSession();
			transaction = session.beginTransaction();
			session.persist(o);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();

			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public static <T> List<T> getAll(Class<T> type) {
		return SESSION_FACTORY.openSession()
				.createQuery("from " + type.getSimpleName(), type)
				.list();
	}
}
