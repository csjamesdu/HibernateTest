package com.csjamesdu.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.csjamesdu.model.onetomany.Group;
import com.csjamesdu.model.onetomany.User;

@SuppressWarnings("unused")
public class RelationTest {
	private static SessionFactory sf = null;
	
	@BeforeClass
	public static void beforeClass() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
		sf = new AnnotationConfiguration().configure().buildSessionFactory();		
	}
	
	@Test
	public void testSechemaExport() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);	
	}
	
	@Test
	public void testOneToManyBiSaveUser(){
		User u = new User();
		u.setName("user1");
		Group g = new Group();
		g.setName("group1");
		u.setGroup(g);
		
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		//s.save(g);
		s.save(u);
		s.getTransaction().commit();
	}
	
	@Test
	public void testOneToManyBiSaveGroup() {
		User u1 = new User();
		User u2 = new User();
		Group g = new Group();
		
		u1.setName("user1");
		u1.setGroup(g);
		u2.setName("user2");
		u2.setGroup(g);
		g.setName("group1");
		
		g.getUsers().add(u1);
		g.getUsers().add(u2);
		
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		s.save(g);
		s.getTransaction().commit();		
	}
	
	@AfterClass
	public static void afterClass() {
		sf.close();
	}

}
