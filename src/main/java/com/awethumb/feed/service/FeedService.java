package com.awethumb.feed.service;

import java.util.List;

import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.CategoryList;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.FollowMeUser;
import com.awethumb.repository.vo.Like;

public interface FeedService {
	List<FeedBoard> selectFeedBoardPage(FeedPage pageCount);
	List<Comment> selectFeedBoardComment(int postNo);
	int insertBoardComment(Comment comment);
	List<Comment> deleteBoardComment(Comment comment);
	List<Comment> updateBoardComment(Comment comment);
	List<CategoryList> selectUserCategoryList(String userId);
	List<FollowMeUser> selectFollowMe(FollowMeUser followmeuser);
	int selectFollowMeCount(String userId);
	List<String> boardFile(int postNo);
	int boardFileCheck(int postNo);
	int boardImgState(int postNo);
}
