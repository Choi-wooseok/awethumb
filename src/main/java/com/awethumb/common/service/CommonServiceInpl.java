package com.awethumb.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.CommonDAO;
import com.awethumb.repository.vo.Category;

@Service
public class CommonServiceInpl implements CommonService {

	@Autowired
	CommonDAO dao;
	
	public List<Category> selectCategoryList() {
		return dao.selectCategoryList();
	}
	
}
