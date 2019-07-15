package com.ebassauto.service;

import java.util.List;

import com.ebassauto.model.LoginDetails;

public interface ILoginDetailsService {
	
	public List<LoginDetails> getLoginDetails(String uname,String pass);

}
