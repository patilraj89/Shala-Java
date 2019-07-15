package com.ebassauto.dao;

import java.util.List;

import com.ebassauto.model.LoginDetails;

public interface ILoginDetailsDao {
	
	public List<LoginDetails> getLoginDetails(String uname,String pass);

}
