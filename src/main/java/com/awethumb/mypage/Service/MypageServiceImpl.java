package com.awethumb.mypage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.MypageDAO;
import com.awethumb.repository.vo.Category;

@Service
public class MypageServiceImpl implements MypageService{
	@Autowired
	private MypageDAO dao;

	@Override
	public List<Category> getCategories() {
		return dao.selectCategories();
	}
}
