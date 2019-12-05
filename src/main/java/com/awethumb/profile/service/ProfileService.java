package com.awethumb.profile.service;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

public interface ProfileService {

	public List<Category> getCategories();
	public UserVO selectOneUser(String userNickname);
	public void updateUser(UserVO user);
	public int checkSub(Subscribe sub);
	public void deleteSub(Subscribe sub);
	public void insertSub(Subscribe sub);
	public List<UserVO> getFollowingList(Follow fol);
	public List<UserVO> getFollowerList(Follow fol);
	public List<UserVO> getSearchFollowerList(Follow fol);
	public List<UserVO> getSearchFollowingList(Follow fol);
	public void updateUserFile(UserFile uf);
	public UserFile getProfileImg(int userNo);
	public void insertProj(Project p, ProjectFile pf);
	public ProjectFile getProjectThumb(int projectNo);
	public List<Project> selectProgressProjects(Project p);
	public List<Project> selectSharedProjects(Project p);
	public List<Project> selectSavedProjects(Project p);
	public int selectProjectCount(int userNo);
	public List<String> selectTokenUsers(String userNickname);
}
