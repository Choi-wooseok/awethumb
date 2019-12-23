package com.awethumb.profile.service;

import java.util.List;

import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.TokenUser;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

public interface ProfileService {

	public List<Category> getCategories();
	public UserVO selectOneUser(String userNickname);
	public void updateUser(UserVO user);
	public void insertProj(Project p, ProjectFile pf);
	public int selectProjectCount(int userNo);
	public int selectCurrentProjectNo(int userNo);
}
