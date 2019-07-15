package com.ebassauto.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebassauto.dao.ILoginDetailsDao;
import com.ebassauto.model.LoginDetails;

@Repository
public class LoginDetailsDaoImpl implements ILoginDetailsDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<LoginDetails> getLoginDetails(String uname, String pass) {
		Session session = sessionFactory.openSession();
		Query qr=session.createQuery("from LoginDetails where userName= :user and passwd= :pass");
		qr.setParameter("user", uname);
		qr.setParameter("pass", pass);
		List<LoginDetails> lgnDetails= qr.getResultList();
		return lgnDetails;
	}

}
