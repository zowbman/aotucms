package com.aotuspace.aotucms.web.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext/applicationContext.xml");
	@Test
	public void testBean() throws Exception{
		TestAction testAction = (TestAction) ac.getBean("testAction");
		System.out.println(testAction);
	}
	
	@Test
	//≤‚ ‘SessionFactory
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	//≤‚ ‘ ¬ŒÒ
	@Test
	public void testTractsaction() throws Exception{
		TestService testService = (TestService) ac.getBean("testService");
		testService.saveTwoUsers();
	}
}
