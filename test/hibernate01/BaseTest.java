package hibernate01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTest {
	protected static SessionFactory sessionFactory;
	protected Session session;
	
	
	@BeforeClass
	public static void beforeClass(){
		Configuration configruation= new Configuration().configure();
		sessionFactory=configruation.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configruation.getProperties()).build());
	}

	@AfterClass
	public static void afterClass(){
		if(sessionFactory!=null){
			sessionFactory.close();
		}
	}
	
	/**
	 * 在被测试的方法执行前执行
	 */
	@Before
	public void beforeMethod(){
		//获取session对象
		session=sessionFactory.getCurrentSession();
		session.beginTransaction();
	}
	
	@After
	public void afterMethod(){
		//判断当前是否有存活的session如果有就提交
		if(session.getTransaction().isActive()){
			session.getTransaction().commit();
		}
	}
	
}
