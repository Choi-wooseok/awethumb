package com.awethumb.mainfeed.service;

import java.util.List;

import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.Hashtag;
import com.awethumb.repository.vo.MainFeed;
import com.awethumb.repository.vo.Search;

public interface MainFeedService {
//  메인피드 생성 ------------------------------
	List<MainFeed> listMainFeed(FeedPage pageCount);
//  디테일피드 생성 ------------------------------
	MainFeed detailMainFeed(int postNo);
//	댓글 ------------------------------------
	void insertComment(Comment comment);
	void updateComment(Comment comment);
	void delectComment(int cmtNo);
	int commentCount(int postNo);
//  해시태그 ----------------------------------
	void insertHashtag(List<Hashtag> hashtag);
//	void updateHashtag(List<Hashtag> hashtag);
	void deleteHashtag(Hashtag hashtag);
//  검색 ------------------------------------
	List<MainFeed> search(Search searchWord);
//	이미지
	List<BoardFile> imageListDown(int postNo);
}
