package com.spring.exam.sys.service;

import java.util.List;

import com.spring.exam.sys.model.Phone;

public interface PhoneService {
	List<Phone> selectPhones();
	List<Phone> selectPhonesByManufacturer(String name);
	List<Phone> selectPhonesByPage(int page);
}
