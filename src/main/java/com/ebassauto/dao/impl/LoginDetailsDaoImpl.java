package com.ebassauto.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebassauto.dao.ILoginDetailsDao;

@Repository
public class LoginDetailsDaoImpl implements ILoginDetailsDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Object[] getLoginDetails(String uname, String pass) {
		Session session = sessionFactory.openSession();
		//Query qr=session.createQuery("from LoginDetails where userName= :user and passwd= :pass");
		
		Query qr = session.createQuery("SELECT ur.roleName FROM LoginDetails lg\r\n" + 
				"		left join UserRoles ur on lg.roleId=ur.roleId\r\n" + 
				"		where lg.userName= :user and lg.passwd= :pass");
		qr.setParameter("user", uname);
		qr.setParameter("pass", pass);
		Object[] lgnDetails= qr.getResultList().toArray();
		return lgnDetails;
	}

}
