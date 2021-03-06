package com.awethumb.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awethumb.repository.dao.AdminDAO;
import com.awethumb.repository.vo.Block;
import com.awethumb.repository.vo.BlockReason;
import com.awethumb.repository.vo.Board;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.Criteria;
import com.awethumb.repository.vo.Report;
import com.awethumb.repository.vo.UserVO;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDAO dao;

	@Override
	public List<Report> getReport() {
		return dao.selectReport();
	}

	@Override
	public String selectUserId(int userNo) {
		return dao.selectUserId(userNo);
	}

	@Override
	public String selectReportReason(int blockCode) {
		return dao.selectReportReason(blockCode);
	}

	@Override
	public String selectPostContent(int postNo) {
		return dao.selectPostContent(postNo);
	}
	@Override
	public Report selectOneReport(int reportNo) {
		return dao.selectOneReport(reportNo);
	}

	@Override
	public Comment selectOneComment(Report report) {
		return dao.selectOneComment(report);
	}

	@Override
	public UserVO selectOneUser(Report report) {
		return dao.selectOneUser(report);
	}

	@Override
	public Board selectOneBoard(Report report) {
		return dao.selectOneBoard(report);
	}

	@Override
	public UserVO selectOneUserByComment(Comment comment) {
		return dao.selectOneUserByComment(comment);
	}

	@Override
	public List<Report> insertBlock(Map<String, String> rmap) {
		dao.insertBlock(rmap);
		return dao.selectReport();
	}

	@Override
	public void updateReportStatus(Map<String, String> rmap) {
		dao.updateReportStatus(rmap);
	}

	@Override
	public Block selectBlock(int userNo) {
		return dao.selectBlock(userNo);
	}

	@Override
	public void denyReportStatus(int reportNo) {
		dao.denyReportStatus(reportNo);
	}

	@Override
	public List<Report> deleteBlock(int userNo) {
		dao.deleteBlock(userNo);
		return  dao.selectReport();
	}

	@Override
	public List<Report> selectReportPaging(Criteria cri) {
		return dao.selectReportPaging(cri);
	}

	@Override
	public int reportCount() {
		return dao.reportCount();
	}

	@Override
	public void deleteReport(int reportNo) {
		dao.deleteReport(reportNo);
	}

	@Override
	public Board selectOneBoardUsingNo(int postNo) {
		return dao.selectOneBoardUsingNo(postNo);
	}

	@Override
	public Comment selectOneCommentUsingNo(int commentNo) {
		return dao.selectOneCommentUsingNo(commentNo);
	}

	@Override
	public UserVO selectOneUserUsingPostNo(int postNo) {
		return dao.selectOneUserUsingPostNo(postNo);
	}

	@Override
	public UserVO selectOneUserUsingCommentNo(int commentNo) {
		return dao.selectOneUserUsingCommentNo(commentNo);
	}

	@Override
	public void insertReport(Report report) {
		dao.insertReport(report);
	}

	@Override
	public List<UserVO> selectUserPaging(Criteria cri) {
		return dao.selectUserPaging(cri);
	}
	@Override
	public int userCount() {
		return dao.userCount();
	}

	@Override
	public UserVO selectOneUserUsingUserNo(int userNo) {
		return dao.selectOneUserUsingUserNo(userNo);
	}

	@Override
	public void updateBlcok(Map<String, Object> rmap) {
		dao.updateBlcok(rmap);
	}

	@Override
	public void cancelBlockByUserNo(int userNo) {
		dao.cancelBlockByUserNo(userNo);
	}

	@Override
	public void insertBlockByAdmin(Map<String, Object> rmap) {
		dao.insertBlockByAdmin(rmap);
	}

	@Override
	public void deleteUser(int userNo) {
		dao.deleteUser(userNo);
	}

	@Override
	public BlockReason selectReportReasonTwo(int userNo) {
		return dao.selectReportReasonTwo(userNo);
	}

	

	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
}
