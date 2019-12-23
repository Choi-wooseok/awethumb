package com.awethumb.profile.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.ProfileDAO;
import com.awethumb.repository.vo.Category;
import com.awethumb.repository.vo.Follow;
import com.awethumb.repository.vo.Project;
import com.awethumb.repository.vo.ProjectFile;
import com.awethumb.repository.vo.Subscribe;
import com.awethumb.repository.vo.TokenUser;
import com.awethumb.repository.vo.UserFile;
import com.awethumb.repository.vo.UserVO;

@Service
public class ProfileServiceImpl implements ProfileService{
	@Autowired
	private ProfileDAO dao;

	@Override
	public List<Category> getCategories() {
		return dao.selectCategories();
	}

	@Override
	public UserVO selectOneUser(String userNickname) {
		return dao.selectOneUser(userNickname);
	}

	@Override
	public void updateUser(UserVO user) {
		dao.updateUser(user);
	}

	@Override
	public void insertProj(Project p, ProjectFile pf) {
		dao.insertProject(p);			
		
		// 프로젝트 번호가 2일 경우 공유 유저 리스트를 넣어준다
		if(p.getProjectType() == 2) {
			// 초대 URL 생성
			p.setInvitationUrl(RandomStringUtils.randomAlphanumeric(10));

			dao.insertSharedUserList(p);
		}
		
		dao.insertProjectFile(pf);
	}

	@Override
	public int selectProjectCount(int userNo) {
		return dao.selectProjectCount(userNo);
	}

	@Override
	public int selectCurrentProjectNo(int userNo) {
		return dao.selectCurrentProjectNo(userNo);
	}

}
