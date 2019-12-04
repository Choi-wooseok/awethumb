package com.awethumb.feed.service;

import java.util.List;

import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedBoard;
import com.awethumb.repository.vo.FeedPage;

public interface FeedService {
	List<Integer> postNoList();
	List<FeedBoard> selectFeedBoardPage(FeedPage pageCount);
	List<Comment> selectFeedBoardComment(int postNo);
	List<Comment> insertBoardComment(Comment comment);
	List<Comment> deleteBoardComment(Comment comment);
	List<Comment> updateBoardComment(Comment comment);
	List<Integer> selectCommentNo();


}
