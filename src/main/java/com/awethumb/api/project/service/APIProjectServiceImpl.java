package com.awethumb.api.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.APIProjectDAO;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;

@Service
public class APIProjectServiceImpl implements APIProjectService{
	@Autowired 
	private APIProjectDAO dao;

	@Override
	public Project selectProject(int projectNo) {
		return dao.selectProject(projectNo);
	}

	@Override
	public ProjectFile selectProjectImg(int projectNo) {
		return dao.selectProjectImg(projectNo);
	}
	
	@Override
	public List<Project> selectProgressProjects(Project p) {
		return dao.selectProgressProjects(p);
	}
	
	@Override
	public List<Project> selectSharedProjects(Project p) {
		return dao.selectSharedProjects(p);
	}

	@Override
	public List<Project> selectSavedProjects(Project p) {
		return dao.selectSavedProjects(p);
	}
}
