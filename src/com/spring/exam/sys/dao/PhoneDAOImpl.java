package com.spring.exam.sys.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.exam.sys.model.Phone;

@Repository
public class PhoneDAOImpl implements PhoneDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Phone> selectPhones() {
		return sqlSession.selectList("PhoneMapper.selectPhones");
	}

	@Override
	public List<Phone> selectPhonesByManufacturer(String name) {
		return sqlSession.selectList("PhoneMapper.selectPhonesByManufacture", name);
	}

	@Override
	public List<Phone> selectPhonesByPage(int page) {
		return sqlSession.selectList("PhoneMapper.selectPhonesByPage", page);
	}

}
