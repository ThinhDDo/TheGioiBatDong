package com.spring.exam.sys.dao;

import java.util.List;

import com.spring.exam.sys.model.Phone;

public interface PhoneDAO {
	List<Phone> selectPhones();
	List<Phone> selectPhonesByManufacturer(String name);
	List<Phone> selectPhonesByPage(int page);
}
