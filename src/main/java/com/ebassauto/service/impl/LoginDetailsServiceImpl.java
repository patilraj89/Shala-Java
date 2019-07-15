package com.ebassauto.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebassauto.dao.ILoginDetailsDao;
import com.ebassauto.model.LoginDetails;
import com.ebassauto.service.ILoginDetailsService;

@Service
@Transactional
public class LoginDetailsServiceImpl implements ILoginDetailsService {

	@Autowired
	ILoginDetailsDao loginDetail;
	
	@Override
	public List<LoginDetails> getLoginDetails(String uname, String pass) {
		// TODO Auto-generated method stub
		return loginDetail.getLoginDetails(uname, pass);
	}

}
