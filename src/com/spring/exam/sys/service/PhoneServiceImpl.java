package com.spring.exam.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exam.sys.dao.PhoneDAO;
import com.spring.exam.sys.model.Phone;

@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private PhoneDAO phoneDAO;

	@Override
	public List<Phone> selectPhones() {
		return phoneDAO.selectPhones();
	}

	@Override
	public List<Phone> selectPhonesByManufacturer(String name) {
		return phoneDAO.selectPhonesByManufacturer(name);
	}

	@Override
	public List<Phone> selectPhonesByPage(int page) {
		return phoneDAO.selectPhonesByPage(page);
	}
	


}
