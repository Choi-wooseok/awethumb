package com.awethumb.mainfeed.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awethumb.mainfeed.service.MainFeedService;
import com.awethumb.repository.vo.BoardFile;
import com.awethumb.repository.vo.Comment;
import com.awethumb.repository.vo.FeedPage;
import com.awethumb.repository.vo.Hashtag;
import com.awethumb.repository.vo.MainFeed;
import com.awethumb.repository.vo.Search;
import com.awethumb.stats.aop.insertSearchLog;

@Controller("com.awethumb.mainfeed.controller.mainfeed")
@RequestMapping("/mainfeed")
public class MainFeedController {
	@Autowired
	private MainFeedService service;
	@RequestMapping("/boardFileRead.do")
	@ResponseBody
	public List<String> boardFileRead(int postNo, HttpServletRequest req){
		List<String> name = new ArrayList<>();
		List<String> bbb = service.boardFile(postNo);
		for (String a : bbb) {
			String fileReadName= req.getContextPath() + "/image/";// awethumb/image/
			fileReadName = fileReadName + a;
			name.add(fileReadName);
		}
		return name;
	}
	@RequestMapping("/mainfeed.do")
	public void mainFeed(String hashtag, Model model) {
//		service.search(hashtag);
		model.addAttribute("hashtag", hashtag);
	}
	@RequestMapping("/mainfeedList.do")  // http://localhost:8000/awethumb/mainfeed/mainfeed.do
	@ResponseBody  // jsp를 호출하는게 아닌 데이터만 호출 : ajax를 호출할때 
	public List<MainFeed> mainFeedList(FeedPage pageCount) {
		return service.listMainFeed(pageCount);
	}
	@GetMapping("/detailmainfeed.do")
	@ResponseBody
	public MainFeed mainFeeddetail(int postNo, HttpServletRequest req) {
		MainFeed mf = service.detailMainFeed(postNo);
		List<BoardFile> image = service.imageListDown(postNo);
//		System.out.println(image);
//		System.out.println(image.size());
		List<String> bfl = new ArrayList<>();
		for(int i = 0; i < image.size(); i++) {
			String path = image.get(i).getBoardFilePath();
			String sysName = image.get(i).getBoardFileSysName();
			String url = req.getContextPath() + "/image/" + path + sysName;
			System.out.println(url);
			bfl.add(url);
		}
		mf.setBoardfileList(bfl);
		return mf;
	}
	@RequestMapping("/insertComment.do") 
	@ResponseBody
	public int commentRegistAjax(@RequestBody Comment comment) {
		System.out.println("인서트 들어옴");
		service.insertComment(comment);
		return comment.getCmtNo();
	}
	@RequestMapping("/insertHashtag.do")
	@ResponseBody
	public void hashtagRegistAjax(@RequestBody List<Hashtag> hashtag) {
		System.out.println("hashinsert 들어옴");
		System.out.println(hashtag);
		service.insertHashtag(hashtag);
	}
	@RequestMapping("/deleteHashtag.do")
	@ResponseBody
	public void hashtagDelete(@RequestBody Hashtag hashtag) {
		System.out.println("hashdelete 들어옴");
		System.out.println(hashtag);
		service.deleteHashtag(hashtag);
	}
	
	@RequestMapping("/updateComment.do")
	@ResponseBody
	public void commentUpdateAjax(Comment comment) {
		service.updateComment(comment);
		System.out.println("update 들어옴");
	}
	@RequestMapping("/deleteComment.do")
	@ResponseBody
	public int commentDelete(@RequestBody Comment comment) {
		service.delectComment(comment.getCmtNo());
		return comment.getCmtNo();
	}
	@RequestMapping(value="/search.do", method = RequestMethod.POST)
	@ResponseBody
	@insertSearchLog
	public List<MainFeed> search(@RequestBody Search searchWord) {
		System.out.println("search 들어옴");
		System.out.println(searchWord);
		return service.search(searchWord);
	}
	
	
	
}
