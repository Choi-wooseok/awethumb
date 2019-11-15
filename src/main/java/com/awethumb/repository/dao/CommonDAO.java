package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Category;

public interface CommonDAO {
	List<Category> selectCategoryList();
}
