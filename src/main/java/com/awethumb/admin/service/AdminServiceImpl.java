package com.awethumb.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.AdminDAO;
import com.awethumb.repository.vo.Report;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDAO dao;

	@Override
	public List<Report> getReport() {
		return dao.selectReport();
	}
	
	
}
