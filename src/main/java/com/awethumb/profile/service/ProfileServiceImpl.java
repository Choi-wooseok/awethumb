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
	public int checkSub(Subscribe sub) {
		return dao.selectSubscribe(sub);
	}

	@Override
	public void deleteSub(Subscribe sub) {
		dao.deleteSubscribe(sub);
	}

	@Override
	public void insertSub(Subscribe sub) {
		dao.insertSubscribe(sub);
	}

	@Override
	public List<UserVO> getFollowingList(Follow fol) {
		return dao.selectFollowingList(fol);
	}

	@Override
	public List<UserVO> getFollowerList(Follow fol) {
		return dao.selectFollowerList(fol);
	}

	@Override
	public List<UserVO> getSearchFollowerList(Follow fol) {
		return dao.selectSearchFollowerList(fol);
	}

	@Override
	public List<UserVO> getSearchFollowingList(Follow fol) {
		return dao.selectSearchFollowingList(fol);
	}

	@Override
	public void updateUserFile(UserFile uf) {
		if(dao.selectUserFile(uf.getUserNo()) == 0) dao.insertUserFile(uf);
		else dao.updateUserFile(uf);
		
	}

	@Override
	public UserFile getProfileImg(int userNo) {
		return dao.selectUserFileVO(userNo);
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
		
		// 유저에게 썸네일을 입력 받았을 경우에만 파일을 입력
		if(pf.getProjectFilePath() != null) {
			dao.insertProjectFile(pf);
		}
	}

	@Override
	public ProjectFile getProjectThumb(int projectNo) {
		return dao.selectProjectThumb(projectNo);
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

	@Override
	public int selectProjectCount(int userNo) {
		return dao.selectProjectCount(userNo);
	}

	@Override
	public List<TokenUser> selectTokenUsers(String userNickname) {
		return dao.selectTokenUsers(userNickname);
	}

	@Override
	public int selectCurrentSharedProjectNo(int userNo) {
		return dao.selectCurrentSharedProjectNo(userNo);
	}

	@Override
	public int selectFollowerCount(int userNo) {
		return dao.selectFollowerCount(userNo);
	}
}
