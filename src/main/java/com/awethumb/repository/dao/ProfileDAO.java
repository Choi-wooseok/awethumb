package com.awethumb.repository.dao;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.TokenUser;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

public interface ProfileDAO {

	public List<Category> selectCategories();

	public UserVO selectOneUser(String userNickname);

	public void updateUser(UserVO user);

	public int selectUserFile(int userNo);

	public void insertProject(Project p);

	public void insertProjectFile(ProjectFile pf);

	public List<Project> selectSharedProjects(Project p);

	public List<Project> selectSavedProjects(Project p);

	public int selectProjectCount(int userNo);

	public void insertSharedUserList(Project p);

	public int selectCurrentSharedProjectNo(int userNo);
}
