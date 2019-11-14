package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Report;

public interface AdminDAO {
	List<Report> selectReport();
}
