package com.ebassauto.dao;

import java.util.List;

import com.ebassauto.model.LoginDetails;

public interface ILoginDetailsDao {
	
	public Object[] getLoginDetails(String uname,String pass);

}
