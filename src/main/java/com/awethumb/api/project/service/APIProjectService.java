package com.awethumb.api.project.service;

import java.util.List;

import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;

public interface APIProjectService {

	Project selectProject(int projectNo);
	ProjectFile selectProjectImg(int projectNo);
	List<Project> selectProgressProjects(Project p);
	List<Project> selectSharedProjects(Project p);
	List<Project> selectSavedProjects(Project p);
	void updateProjectImg(ProjectFile pf);

}
